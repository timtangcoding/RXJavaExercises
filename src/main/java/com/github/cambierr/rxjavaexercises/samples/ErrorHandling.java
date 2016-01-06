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

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 *
 * @author cambierr
 */
public class ErrorHandling {

    public static void main(String[] args) {
        /**
         * Comment this function once you have seen the stack trace of a non-handled error in RX
         */
        createRXError().subscribe();
        System.out.println("------------------------------------------------------------");
        /**
         * Lets handle the error in the subscriber:
         */
        createRXError().subscribe(new Subscriber<String>() {

            @Override
            public void onCompleted() {
                
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String t) {
                
            }
        });
    }

    public static Observable<String> createRXError() {
        return Observable
                .create(new Observable.OnSubscribe<String>() {

                    @Override
                    public void call(Subscriber<? super String> arg0) {
                        arg0.onError(new Exception("find me"));
                    }
                })
                .map(new Func1<String, String>() {

                    @Override
                    public String call(String arg0) {
                        return arg0;
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {

                    @Override
                    public Observable<String> call(String arg0) {
                        return Observable.just(arg0);
                    }
                })
                .map(new Func1<String, String>() {

                    @Override
                    public String call(String arg0) {
                        return arg0;
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {

                    @Override
                    public Observable<String> call(String arg0) {
                        return Observable.just(arg0);
                    }
                })
                .map(new Func1<String, String>() {

                    @Override
                    public String call(String arg0) {
                        return arg0;
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {

                    @Override
                    public Observable<String> call(String arg0) {
                        return Observable.just(arg0);
                    }
                })
                .map(new Func1<String, String>() {

                    @Override
                    public String call(String arg0) {
                        return arg0;
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {

                    @Override
                    public Observable<String> call(String arg0) {
                        return Observable.just(arg0);
                    }
                })
                .map(new Func1<String, String>() {

                    @Override
                    public String call(String arg0) {
                        return arg0;
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {

                    @Override
                    public Observable<String> call(String arg0) {
                        return Observable.just(arg0);
                    }
                })
                .map(new Func1<String, String>() {

                    @Override
                    public String call(String arg0) {
                        return arg0;
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {

                    @Override
                    public Observable<String> call(String arg0) {
                        return Observable.just(arg0);
                    }
                })
                .map(new Func1<String, String>() {

                    @Override
                    public String call(String arg0) {
                        return arg0;
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {

                    @Override
                    public Observable<String> call(String arg0) {
                        return Observable.just(arg0);
                    }
                });
    }

}
