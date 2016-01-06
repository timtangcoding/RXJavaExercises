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
import rx.schedulers.Schedulers;

/**
 *
 * @author cambierr
 */
public class BasicScheduler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        /**
         * List cities population again, and look at the time:
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
                .map(new Func1<Integer, String>() {

                    @Override
                    public String call(Integer arg0) {
                        return arg0.toString();
                    }
                })
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                        System.out.println("1st listing cities population took " + (System.currentTimeMillis() - start) + " ms");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String t) {
                        System.out.println(t);
                    }
                });
        /**
         * Comment the next line before next example
         */
        System.exit(0);
        System.out.println("------------------------------------------------------------");
        /**
         * List cities population again, and look at the time, but add an IO Scheduler
         */
        final long start2 = System.currentTimeMillis();
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
                .map(new Func1<Integer, String>() {

                    @Override
                    public String call(Integer arg0) {
                        return arg0.toString();
                    }
                })
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                        System.out.println("2nd listing cities population took " + (System.currentTimeMillis() - start2) + " ms");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String t) {
                        System.out.println(t);
                    }
                });
        /**
         * WTF ? nothing has been done !!!
         */
        /**
         * Comment the next line before next example
         */
        System.exit(0);
        System.out.println("------------------------------------------------------------");
        /**
         * List cities population again, and look at the time, but add an IO Scheduler, but waiting for the end
         */
        final long start3 = System.currentTimeMillis();
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
        System.out.println("3rd listing cities population took " + (System.currentTimeMillis() - start3) + " ms");
        /**
         * Comment the next line before next example
         */
        System.exit(0);
        System.out.println("------------------------------------------------------------");
        /**
         * List cities population again, and look at the time, but add an computation Scheduler and still waiting for the end
         */
        final long start4 = System.currentTimeMillis();
        final CountDownLatch cdl2 = new CountDownLatch(1);
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
                        return arg0.getPopulation().subscribeOn(Schedulers.computation());
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
                        cdl2.countDown();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String t) {
                        System.out.println(t);
                    }
                });
        cdl2.await();
        System.out.println("4th listing cities population took " + (System.currentTimeMillis() - start4) + " ms");
        /**
         * Comment the next line before next example
         */
        System.exit(0);
        System.out.println("------------------------------------------------------------");
        /**
         * List cities population again, and look at the time, but add an computation Scheduler and still waiting for the end
         * Look at the thread printing the result !
         */
        final long start5 = System.currentTimeMillis();
        final CountDownLatch cdl3 = new CountDownLatch(1);
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
                .map(new Func1<Integer, String>() {

                    @Override
                    public String call(Integer arg0) {
                        return arg0.toString();
                    }
                })
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                        cdl3.countDown();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String t) {
                        System.out.println(t);
                        System.out.println(Thread.currentThread().getName());
                    }
                });
        cdl3.await();
        System.out.println("5th listing cities population took " + (System.currentTimeMillis() - start5) + " ms");
        /**
         * Comment the next line before next example
         */
        System.exit(0);
        System.out.println("------------------------------------------------------------");
        /**
         * List cities population again, and look at the time, but add an computation Scheduler and still waiting for the end
         * Look at the thread printing the result !
         */
        final long start6 = System.currentTimeMillis();
        final CountDownLatch cdl4 = new CountDownLatch(1);
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
                .map(new Func1<Integer, String>() {

                    @Override
                    public String call(Integer arg0) {
                        return arg0.toString();
                    }
                })
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                        cdl4.countDown();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String t) {
                        System.out.println(t);
                        System.out.println(Thread.currentThread().getName());
                    }
                });
        cdl4.await();
        System.out.println("5th listing cities population took " + (System.currentTimeMillis() - start6) + " ms");
        /**
         * Comment the next line before next example
         */
        System.exit(0);

    }

}
