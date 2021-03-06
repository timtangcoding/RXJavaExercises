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
public class B {

    public static void main(String[] args) {

        /**
         * wrong !
         */
        Observable
                .just("Hello, world")
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String t) {
                        System.out.println(t + " from Mons!");
                    }
                });
        System.out.println("------------------------------------------------------------");

        /**
         * good !
         */
        Observable
                .just("Hello, world")
                .map(new Func1<String, String>() {

                    @Override
                    public String call(String arg0) {
                        return arg0 + " from Mons!";
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
        System.out.println("------------------------------------------------------------");

        /**
         * more: map does not have to return the same type as the input !
         */
        Observable
                .just("Hello, world from Mons!")
                .map(new Func1<String, Integer>() {

                    @Override
                    public Integer call(String arg0) {
                        return arg0.split(" ").length;
                    }
                })
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer t) {
                        System.out.println(t);
                    }
                });
        System.out.println("------------------------------------------------------------");

        /**
         * better: let's not do any computation in the Subscriber
         */
        Observable
                .just("Hello, world from Mons!")
                .map(new Func1<String, Integer>() {

                    @Override
                    public Integer call(String arg0) {
                        return arg0.split(" ").length;
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

    }

}
