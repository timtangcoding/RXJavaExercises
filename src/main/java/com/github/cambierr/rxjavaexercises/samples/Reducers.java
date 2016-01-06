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
package com.github.cambierr.rxjavaexercises.samples;

import com.github.cambierr.rxjavaexercises.samples.extra.OSMCities;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 *
 * @author cambierr
 */
public class Reducers {

    public static void main(String[] args) throws InterruptedException {
        /**
         * Sum all cities population
         */
        final long start = System.currentTimeMillis();
        new OSMCities()
                .getCitiesAsList("Mons")
                .flatMap(new Func1<List<OSMCities.OSMCity>, Observable<OSMCities.OSMCity>>() {

                    @Override
                    public Observable<OSMCities.OSMCity> call(List<OSMCities.OSMCity> arg0) {
                        return Observable.from(arg0);
                    }
                })
                .flatMap(new Func1<OSMCities.OSMCity, Observable<Integer>>() {

                    @Override
                    public Observable<Integer> call(OSMCities.OSMCity arg0) {
                        return arg0.getPopulation();
                    }
                })
                .subscribe(new Subscriber<Integer>() {

                    int sum = 0;

                    @Override
                    public void onCompleted() {
                        System.out.println(sum);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer t) {
                        sum += t;
                    }
                });
        System.out.println("1st sum of cities population took " + (System.currentTimeMillis() - start) + " ms");
        System.out.println("------------------------------------------------------------");
        /**
         * Sum all cities population...without doing work in the subscriber !!!
         */
        final long start1 = System.currentTimeMillis();
        new OSMCities()
                .getCitiesAsList("Mons")
                .flatMap(new Func1<List<OSMCities.OSMCity>, Observable<OSMCities.OSMCity>>() {

                    @Override
                    public Observable<OSMCities.OSMCity> call(List<OSMCities.OSMCity> arg0) {
                        return Observable.from(arg0);
                    }
                })
                .flatMap(new Func1<OSMCities.OSMCity, Observable<Integer>>() {

                    @Override
                    public Observable<Integer> call(OSMCities.OSMCity arg0) {
                        return arg0.getPopulation();
                    }
                })
                .reduce(new Func2<Integer, Integer, Integer>() {

                    @Override
                    public Integer call(Integer arg0, Integer arg1) {
                        return arg0 + arg1;
                    }
                })
                .map(new Func1<Integer, String>() {

                    @Override
                    public String call(Integer arg0) {
                        return arg0.toString();
                    }
                })
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String t) {
                        System.out.println(t);
                    }
                });
        System.out.println("2nd sum of cities population took " + (System.currentTimeMillis() - start1) + " ms");
        System.out.println("------------------------------------------------------------");
        /**
         * Sum all cities population...without doing work in the subscriber, but faster..
         */
        final long start2 = System.currentTimeMillis();
        final CountDownLatch cdl = new CountDownLatch(1);
        new OSMCities()
                .getCitiesAsList("Mons")
                .flatMap(new Func1<List<OSMCities.OSMCity>, Observable<OSMCities.OSMCity>>() {

                    @Override
                    public Observable<OSMCities.OSMCity> call(List<OSMCities.OSMCity> arg0) {
                        return Observable.from(arg0);
                    }
                })
                .flatMap(new Func1<OSMCities.OSMCity, Observable<Integer>>() {

                    @Override
                    public Observable<Integer> call(OSMCities.OSMCity arg0) {
                        return arg0.getPopulation().subscribeOn(Schedulers.io());
                    }
                })
                .reduce(new Func2<Integer, Integer, Integer>() {

                    @Override
                    public Integer call(Integer arg0, Integer arg1) {
                        return arg0 + arg1;
                    }
                })
                .map(new Func1<Integer, String>() {

                    @Override
                    public String call(Integer arg0) {
                        return arg0.toString();
                    }
                })
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                        cdl.countDown();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String t) {
                        System.out.println(t);
                    }
                });
        cdl.await();
        System.out.println("3rd sum of cities population took " + (System.currentTimeMillis() - start2) + " ms");
    }

}
