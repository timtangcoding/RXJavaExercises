/*
 * The MIT License
 *
 * Copyright 2016 cambierr.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.cambierr.rxjavaexercises.samples.extra;

import com.couchbase.client.java.AsyncBucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 *
 * @author cambierr
 */
public class PopulateDatabase {

    public static void main(String[] args) throws InterruptedException {
        Cluster cluster = CouchbaseCluster.create();
        final AsyncBucket defaultBucket = cluster.openBucket().async();

        final ConcurrentHashMap<String, JsonDocument> users = new ConcurrentHashMap<>();
        final ConcurrentHashMap<String, JsonDocument> products = new ConcurrentHashMap<>();
        final ConcurrentHashMap<String, JsonDocument> orders = new ConcurrentHashMap<>();

        final int userToGenerate = 10000;
        final int productToGenerate = 10000;
        final int orderPerUserToGenerate = 10;
        final int productPerORder = 10;

        final CountDownLatch cdl = new CountDownLatch(1);

        Observable
                .merge(
                        Observable
                        .range(1, userToGenerate)
                        .map(new Func1<Integer, JsonDocument>() {

                            @Override
                            public JsonDocument call(Integer arg0) {
                                return JsonDocument
                                .create("user::" + arg0, JsonObject
                                        .create()
                                        .put("type", "user")
                                        .put("pseudo", "firstname-" + arg0)
                                        .put("orders", JsonArray.empty())
                                );
                            }
                        })
                        .doOnNext(new Action1<JsonDocument>() {

                            @Override
                            public void call(JsonDocument arg0) {
                                users.put(arg0.id(), arg0);
                            }
                        })
                        .ignoreElements(),
                        Observable
                        .range(1, productToGenerate)
                        .map(new Func1<Integer, JsonDocument>() {

                            @Override
                            public JsonDocument call(Integer arg0) {
                                return JsonDocument
                                .create("product::" + arg0, JsonObject
                                        .create()
                                        .put("type", "product")
                                        .put("name", "product-" + arg0)
                                        .put("price", 100 * Math.random())
                                        .put("orders", JsonArray.empty())
                                );
                            }
                        })
                        .doOnNext(new Action1<JsonDocument>() {

                            @Override
                            public void call(JsonDocument arg0) {
                                products.put(arg0.id(), arg0);
                            }
                        })
                        .ignoreElements()
                )
                .subscribeOn(Schedulers.computation())
                .subscribe(new Subscriber<JsonDocument>() {

                    @Override
                    public void onCompleted() {
                        cdl.countDown();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(JsonDocument t) {
                    }
                });

        cdl.await();
        System.out.println("generated user and products");

        final CountDownLatch cdl2 = new CountDownLatch(1);

        Observable
                .range(1, userToGenerate, Schedulers.computation())
                .map(new Func1<Integer, JsonDocument>() {

                    @Override
                    public JsonDocument call(Integer arg0) {
                        return users.get("user::" + arg0);
                    }
                })
                .flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {

                    @Override
                    public Observable<JsonDocument> call(final JsonDocument user) {

                        return Observable
                        .range(1, orderPerUserToGenerate)
                        .map(new Func1<Integer, JsonDocument>() {

                            @Override
                            public JsonDocument call(Integer orderSubKey) {
                                return JsonDocument.create(
                                        "order::" + user.id() + "::" + orderSubKey,
                                        JsonObject
                                        .create()
                                        .put("type", "order")
                                        .put("time", System.currentTimeMillis())
                                        .put("user", user.id())
                                        .put("content", JsonObject.create())
                                );
                            }
                        })
                        .doOnNext(new Action1<JsonDocument>() {

                            @Override
                            public void call(JsonDocument arg0) {
                                orders.put(arg0.id(), arg0);
                            }
                        })
                        .flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {

                            @Override
                            public Observable<JsonDocument> call(final JsonDocument order) {
                                return Observable
                                .range(1, productPerORder)
                                .map(new Func1<Integer, JsonDocument>() {

                                    @Override
                                    public JsonDocument call(Integer arg0) {
                                        return products.get("product::" + ((int) Math.max(1, (productToGenerate * Math.random()))));
                                    }
                                })
                                .doOnNext(new Action1<JsonDocument>() {

                                    @Override
                                    public void call(JsonDocument arg0) {
                                        order.content().getObject("content").put(arg0.id(), (int) (100 * Math.random()));
                                        arg0.content().getArray("orders").add(order.id());
                                    }
                                })
                                .last();
                            }
                        })
                        .doOnNext(new Action1<JsonDocument>() {

                            @Override
                            public void call(JsonDocument order) {
                                user.content().getArray("orders").add(order.id());
                            }
                        });
                    }
                })
                .subscribe(new Subscriber<JsonDocument>() {

                    @Override
                    public void onCompleted() {
                        cdl2.countDown();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(JsonDocument t) {

                    }
                });

        cdl2.await();

        final CountDownLatch cdl3 = new CountDownLatch(1);

        Observable
                .just(users.values(), products.values(), orders.values())
                .flatMap(new Func1<Collection<JsonDocument>, Observable<JsonDocument>>() {

                    @Override
                    public Observable<JsonDocument> call(Collection<JsonDocument> arg0) {
                        return Observable.from(arg0);
                    }
                })
                .flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {

                    @Override
                    public Observable<JsonDocument> call(JsonDocument arg0) {
                        return defaultBucket.upsert(arg0);
                    }
                })
                .subscribe(new Subscriber<JsonDocument>() {

                    @Override
                    public void onCompleted() {
                        cdl3.countDown();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(JsonDocument t) {
                        
                    }
                });
        
        cdl3.await();

        System.out.println("over");
    }

}
