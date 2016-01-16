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
package com.github.cambierr.rxjavaexercises.slides;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 *
 * @author cambierr
 */
public class B {

    public static void main(String[] args) {

        final Observable<Integer> myObservable = Observable.empty();

        /**
         * This Operator will transform the received value an emit the new one:
         * In this case, it will emit the square root of the input integer
         */
        myObservable.map(new Func1<Integer, Double>() {

            @Override
            public Double call(Integer input) {
                return Math.pow(input, 0.5);
            }
        });

        /**
         * This Operator can emit between 0 and several items, or even errors:
         * In this case, it will emit [input] integers starting at 0
         */
        myObservable.flatMap(new Func1<Integer, Observable<Integer>>() {

            @Override
            public Observable<Integer> call(Integer input) {
                return Observable.range(0, input);
            }
        });

        /**
         * This Operator can emit between 0 and several items, or even errors:
         * In this case, it will emit an error
         */
        myObservable.flatMap(new Func1<Integer, Observable<Integer>>() {

            @Override
            public Observable<Integer> call(Integer input) {
                return Observable.error(new Exception());
            }
        });

        /**
         * This Operator can emit between 0 and several items, or even errors:
         * In this case, it will emit nothing
         */
        myObservable.flatMap(new Func1<Integer, Observable<Integer>>() {

            @Override
            public Observable<Integer> call(Integer input) {
                return Observable.empty();
            }
        });

        /**
         * This Operator will put all received items in a list, and emit this list as soon as the source stop emitting items
         */
        myObservable.toList();

        /**
         * This Operator will filter received items using your condition (only items matching it will be emitted)
         * In this case, only even numbers will go through this operator
         */
        myObservable.filter(new Func1<Integer, Boolean>() {

            @Override
            public Boolean call(Integer arg0) {
                return arg0 % 2 == 0;
            }
        });

        /**
         * This Operator will group received items in several lists using your condition
         * In this case, two lists will be emitted: one with even numbers, one with the others
         */
        myObservable.groupBy(new Func1<Integer, Boolean>() {

            @Override
            public Boolean call(Integer arg0) {
                return arg0 % 2 == 0;
            }
        });

        /**
         * This Operator will only emit the 7 first received items
         */
        myObservable.take(7);

        /**
         * This Operator will skip 3 items then emit all other
         */
        myObservable.skip(3);

        /**
         * This Operator will emit all items but the 4 last
         */
        myObservable.skipLast(4);

        /**
         * This Operator will only emit the 2 last received items
         */
        myObservable.takeLast(2);

        /**
         * same as .take(1)
         */
        myObservable.first();

        /**
         * same as .takeLast(1)
         */
        myObservable.last();

        /**
         * This Operator will count received items and emit the count as soon as the source will stop emitting items
         */
        myObservable.count();

        /**
         * This Operator will only emit distinct items
         * Ex: the sequence "a", "b", "a", "c" will become "a", "b", "c"
         */
        myObservable.distinct();

        /**
         * This Operator won't emit any items
         */
        myObservable.ignoreElements();

        /**
         * This Operator will only emit the first item and fire an error if less than or more than one items are received
         */
        myObservable.single();
    }

}
