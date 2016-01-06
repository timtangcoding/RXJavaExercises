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
import com.couchbase.client.java.view.AsyncViewResult;
import com.couchbase.client.java.view.AsyncViewRow;
import com.couchbase.client.java.view.ViewQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import rx.Observable;
import rx.functions.Func1;

/**
 *
 * @author cambierr
 */
public class Database {

    private static final Cluster cluster;
    private static final AsyncBucket bucket;

    static {
        cluster = CouchbaseCluster.create("127.0.0.1");
        bucket = cluster.openBucket().async();
    }

    public Observable<User> listUsers() {
        return bucket.query(ViewQuery.from("all", "user"))
                .flatMap(new Func1<AsyncViewResult, Observable<AsyncViewRow>>() {

                    @Override
                    public Observable<AsyncViewRow> call(AsyncViewResult arg0) {
                        return arg0.rows();
                    }
                })
                .flatMap(new Func1<AsyncViewRow, Observable<JsonDocument>>() {

                    @Override
                    public Observable<JsonDocument> call(AsyncViewRow arg0) {
                        return arg0.document();
                    }
                })
                .map(new Func1<JsonDocument, User>() {

                    @Override
                    public User call(JsonDocument arg0) {
                        return new User(arg0);
                    }
                });
    }

    public Observable<User> findUser(String _pseudo) {
        return bucket.query(ViewQuery.from("all", "user").key(_pseudo))
                .flatMap(new Func1<AsyncViewResult, Observable<AsyncViewRow>>() {

                    @Override
                    public Observable<AsyncViewRow> call(AsyncViewResult arg0) {
                        return arg0.rows();
                    }
                })
                .flatMap(new Func1<AsyncViewRow, Observable<JsonDocument>>() {

                    @Override
                    public Observable<JsonDocument> call(AsyncViewRow arg0) {
                        return arg0.document();
                    }
                })
                .map(new Func1<JsonDocument, User>() {

                    @Override
                    public User call(JsonDocument arg0) {
                        return new User(arg0);
                    }
                });
    }

    public Observable<Product> listProducts() {
        return bucket.query(ViewQuery.from("all", "product"))
                .flatMap(new Func1<AsyncViewResult, Observable<AsyncViewRow>>() {

                    @Override
                    public Observable<AsyncViewRow> call(AsyncViewResult arg0) {
                        return arg0.rows();
                    }
                })
                .flatMap(new Func1<AsyncViewRow, Observable<JsonDocument>>() {

                    @Override
                    public Observable<JsonDocument> call(AsyncViewRow arg0) {
                        return arg0.document();
                    }
                })
                .map(new Func1<JsonDocument, Product>() {

                    @Override
                    public Product call(JsonDocument arg0) {
                        return new Product(arg0);
                    }
                });
    }

    public Observable<Product> listProductByName(String _name) {
        return bucket.query(ViewQuery.from("all", "product").key(_name))
                .flatMap(new Func1<AsyncViewResult, Observable<AsyncViewRow>>() {

                    @Override
                    public Observable<AsyncViewRow> call(AsyncViewResult arg0) {
                        return arg0.rows();
                    }
                })
                .flatMap(new Func1<AsyncViewRow, Observable<JsonDocument>>() {

                    @Override
                    public Observable<JsonDocument> call(AsyncViewRow arg0) {
                        return arg0.document();
                    }
                })
                .map(new Func1<JsonDocument, Product>() {

                    @Override
                    public Product call(JsonDocument arg0) {
                        return new Product(arg0);
                    }
                });
    }

    public Observable<Product> findProduct(String _name) {
        return bucket.query(ViewQuery.from("all", "product").key(_name))
                .flatMap(new Func1<AsyncViewResult, Observable<AsyncViewRow>>() {

                    @Override
                    public Observable<AsyncViewRow> call(AsyncViewResult arg0) {
                        return arg0.rows();
                    }
                })
                .flatMap(new Func1<AsyncViewRow, Observable<JsonDocument>>() {

                    @Override
                    public Observable<JsonDocument> call(AsyncViewRow arg0) {
                        return arg0.document();
                    }
                })
                .map(new Func1<JsonDocument, Product>() {

                    @Override
                    public Product call(JsonDocument arg0) {
                        return new Product(arg0);
                    }
                });
    }

    public Observable<Order> listOrders() {
        return bucket.query(ViewQuery.from("all", "order"))
                .flatMap(new Func1<AsyncViewResult, Observable<AsyncViewRow>>() {

                    @Override
                    public Observable<AsyncViewRow> call(AsyncViewResult arg0) {
                        return arg0.rows();
                    }
                })
                .flatMap(new Func1<AsyncViewRow, Observable<JsonDocument>>() {

                    @Override
                    public Observable<JsonDocument> call(AsyncViewRow arg0) {
                        return arg0.document();
                    }
                })
                .map(new Func1<JsonDocument, Order>() {

                    @Override
                    public Order call(JsonDocument arg0) {
                        return new Order(arg0);
                    }
                });
    }

    public Observable<Order> listOrdersBetween(long _t1, long _t2) {
        return bucket.query(ViewQuery.from("all", "order").startKey(_t1).endKey(_t2))
                .flatMap(new Func1<AsyncViewResult, Observable<AsyncViewRow>>() {

                    @Override
                    public Observable<AsyncViewRow> call(AsyncViewResult arg0) {
                        return arg0.rows();
                    }
                })
                .flatMap(new Func1<AsyncViewRow, Observable<JsonDocument>>() {

                    @Override
                    public Observable<JsonDocument> call(AsyncViewRow arg0) {
                        return arg0.document();
                    }
                })
                .map(new Func1<JsonDocument, Order>() {

                    @Override
                    public Order call(JsonDocument arg0) {
                        return new Order(arg0);
                    }
                });
    }

    public Observable<Order> getOrder(String _id) {
        return bucket.get(_id)
                .filter(new Func1<JsonDocument, Boolean>() {

                    @Override
                    public Boolean call(JsonDocument arg0) {
                        return arg0.content().getString("type").equals("order");
                    }
                })
                .map(new Func1<JsonDocument, Order>() {

                    @Override
                    public Order call(JsonDocument arg0) {
                        return new Order(arg0);
                    }
                });
    }

    public Observable<User> getUser(String _id) {
        return bucket.get(_id)
                .filter(new Func1<JsonDocument, Boolean>() {

                    @Override
                    public Boolean call(JsonDocument arg0) {
                        return arg0.content().getString("type").equals("user");
                    }
                })
                .map(new Func1<JsonDocument, User>() {

                    @Override
                    public User call(JsonDocument arg0) {
                        return new User(arg0);
                    }
                });
    }

    public Observable<Product> getProduct(String _id) {
        return bucket.get(_id)
                .filter(new Func1<JsonDocument, Boolean>() {

                    @Override
                    public Boolean call(JsonDocument arg0) {
                        return arg0.content().getString("type").equals("product");
                    }
                })
                .map(new Func1<JsonDocument, Product>() {

                    @Override
                    public Product call(JsonDocument arg0) {
                        return new Product(arg0);
                    }
                });
    }

    public class User {

        private final JsonDocument me;

        public String getId() {
            return me.id();
        }

        private User(JsonDocument _data) {
            me = _data;
        }

        public String getPseudo() {
            return me.content().getString("pseudo");
        }

        public List<String> getOrderIds() {
            final ArrayList al = new ArrayList();
            me.content().getArray("orders").toList().forEach(new Consumer<Object>() {

                @Override
                public void accept(Object t) {
                    al.add(t.toString());
                }
            });
            return al;
        }

    }

    public class Order {

        private final JsonDocument me;

        public String getId() {
            return me.id();
        }

        private Order(JsonDocument _data) {
            me = _data;
        }

        public long getTime() {
            return me.content().getLong("time");
        }

        public String getUserId() {
            return me.content().getString("user");
        }

        public Map<String, Integer> getOrderContent() {
            final HashMap<String, Integer> al = new HashMap();
            me.content().getObject("content").toMap().forEach(new BiConsumer<String, Object>() {

                @Override
                public void accept(String t, Object u) {
                    al.put(t, (int) u);
                }
            });
            return al;
        }

    }

    public class Product {

        private final JsonDocument me;

        public String getId() {
            return me.id();
        }

        private Product(JsonDocument _data) {
            me = _data;
        }

        public String getName() {
            return me.content().getString("name");
        }

        public double getPrice() {
            return me.content().getDouble("price");
        }

        public List<String> getOrderIds() {
            final ArrayList al = new ArrayList();
            me.content().getArray("orders").toList().forEach(new Consumer<Object>() {

                @Override
                public void accept(Object t) {
                    al.add(t.toString());
                }
            });
            return al;
        }

    }

}
