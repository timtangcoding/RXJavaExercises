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
import rx.functions.Action1;

/**
 *
 * @author cambierr
 */
public class BasicObservable {

    public static void main(String[] args) {

        Observable<String> myFirstObservable = Observable
                .create(new Observable.OnSubscribe<String>() {

                    @Override
                    public void call(Subscriber<? super String> arg0) {
                        arg0.onNext("Hello, world!");
                        arg0.onCompleted();
                    }
                });

        Subscriber<String> myFirstSubscriber = new Subscriber<String>() {

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("something went wrong: " + e.getMessage());
            }

            @Override
            public void onNext(String t) {
                System.out.println("onNext: " + t);
            }
        };

        myFirstObservable.subscribe(myFirstSubscriber);
        System.out.println("------------------------------------------------------------");

        /**
         * Shorter version:s
         */
        Observable<String> myFirstShorterObservable = Observable.just("Hello, world!");

        myFirstShorterObservable.subscribe(myFirstSubscriber);
        System.out.println("------------------------------------------------------------");

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("onNext: " + s);
            }
        };

        myFirstShorterObservable.subscribe(onNextAction);

    }

}
