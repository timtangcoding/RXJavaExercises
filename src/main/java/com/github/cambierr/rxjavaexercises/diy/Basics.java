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
package com.github.cambierr.rxjavaexercises.diy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 *
 * @author cambierr
 */
public class Basics {

    public static Observable<String> f1() {
        /**
         * Return an Observable that will only emit one String: "Hello, World!"
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<String> f2(final List<String> _strings) {
        /**
         * Return an Observable that will emit all Strings from the List, separately
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<List<String>> f3(final List<String> _strings) {
        /**
         * Return an Observable that will emit the list of String
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<Integer> f4() {
        /**
         * Return an Observable that will emit integers from 0 to 10 inclusive
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<Integer> f5() {
        /**
         * Return an Observable that will emit integers from 10 to 0 inclusive
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<String> f6(final Observable<String> _input, final String _postfix) {
        /**
         * Return an Observable that will add _postfix at the end of all _input String
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<Integer> f7(final Observable<Integer> _input) {
        /**
         * Return an Observable that will only emit even numbers from _input
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<Object> f8() {
        /**
         * Return an Observable that won't emit anything
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<Object> f9() {
        /**
         * Return an Observable that contains an error different from the default one
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<String> f10(final Observable<Map.Entry<String, String>> _input) {
        /**
         * Return an Observable that will emit all _input entries Keys one-by-one
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<List<String>> f11(final Observable<Map.Entry<String, String>> _input) {
        /**
         * Return an Observable that will emit all _input entries Keys in a single list
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<String> f12(final Observable<Map.Entry<String, String>> _input) {
        /**
         * Return an Observable that will emit all _input entries Keys one-by-one, using flatMap! Just in case we would like to have concurrency
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<List<String>> f13(final Observable<Map.Entry<String, String>> _input) {
        /**
         * Return an Observable that will emit all _input entries Keys in a single list, using flatMap! Just in case we would like to have concurrency
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<Integer> f14(final Observable<Integer> _input) {
        /**
         * Return an Observable that will emit for each _input entry, the entry and the squared entry
         * Ex: input: 1, 3, 4, 5 output: 1, 1, 3, 9, 4, 16, 5, 25
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<Integer> f15(final Observable<Integer> _input) {
        /**
         * Return an Observable that will emit for each _input entry, the entry and the squared entry; Use Observable.create() this time!
         * Ex: input: 1, 3, 4, 5 output: 1, 1, 3, 9, 4, 16, 5, 25
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<Integer> f16(final Observable<Integer> _input) {
        /**
         * Return an Observable that will only emit the fisrt 5 entries of _input
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<Integer> f17(final Observable<Integer> _input) {
        /**
         * Return an Observable that will only emit the last 5 entries of _input
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<Integer> f18(final Observable<Integer> _input) {
        /**
         * Return an Observable that will skip the fisrt 5 entries of _input
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<Integer> f19(final Observable<Integer> _input) {
        /**
         * Return an Observable that will skip the last 5 entries of _input
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<String> f20(final Observable<String> _input) {
        /**
         * Return an Observable that won't emit anything (do not use any of the previous methods)
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<Integer> f21(final Observable<Integer> _input, final int _pos) {
        /**
         * Return an Observable that will only emit the _pos th item emitted by _input
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<String> f22(final Observable<String> _input) {
        /**
         * Return an Observable that will emit the items from _input, removing duplicates
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<Integer> f23(final Observable<Integer> _input1, final Observable<Integer> _input2) {
        /**
         * Return an Observable that will emit the items from both input Observables
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<Integer> f24(final Observable<Integer> _input) {
        /**
         * Return an Observable that will only emit the number of elements emitted in _input
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<Integer> f25(final Observable<Integer> _input) {
        /**
         * Return an Observable that will emit the sum of the squared items from _input
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<String> f26(final Observable<String> _input) {
        /**
         * The input stream contains an error. Handle it and emit "default" as a value for that error
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    public static Observable<String> f27(final Observable<String> _input) {
        /**
         * The input stream sometimes contains an error. Retry until it completes without error
         */
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    /**
     * DO NOT TOUCH ANYTHING BEYOND THIS POINT OTHERWISE I WILL BE SUPER ANGRY!
     */
    private static double score = 0.0, fail = 0.0;

    public static void main(String[] args) {
        final List<String> l1 = Arrays.asList("Hi", "my", "name", "is", "Romain");
        /**
         * f1
         */
        try {
            if (f1().toBlocking().single().equals("Hello, World!")) {
                success("f1");
            } else {
                fail("f1");
            }
        } catch (Throwable t) {
            fail("f1");
        }
        /**
         * f2
         */
        try {
            if (doListsMatches(l1, f2(l1).toList().toBlocking().single())) {
                success("f2");
            } else {
                fail("f2");
            }
        } catch (Throwable t) {
            fail("f2");
        }
        /**
         * f3
         */
        try {
            if (doListsMatches(l1, f3(l1).toBlocking().single())) {
                success("f3");
            } else {
                fail("f3");
            }
        } catch (Throwable t) {
            fail("f3");
        }
        /**
         * f4
         */
        try {
            List<Integer> l = f4().toList().toBlocking().single();
            boolean ok = true;
            for (int i = 0; i <= 10; i++) {
                if (l.get(i) != i) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                success("f4");
            } else {
                fail("f4");
            }
        } catch (Throwable t) {
            fail("f4");
        }
        /**
         * f5
         */
        try {
            List<Integer> l = f5().toList().toBlocking().single();
            boolean ok = true;
            for (int i = 0; i <= 10; i++) {
                if (l.get(i) != (10 - i)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                success("f5");
            } else {
                fail("f5");
            }
        } catch (Throwable t) {
            fail("f5");
        }
        /**
         * f6
         */
        try {
            List<String> l = f6(Observable.from(l1), "fpms").toList().toBlocking().single();
            final List<String> ll = new ArrayList<>();
            l1.stream().forEach(new Consumer<String>() {
                @Override
                public void accept(String t) {
                    ll.add(t + "fpms");
                }
            });
            if (doListsMatches(ll, l)) {
                success("f6");
            } else {
                fail("f6");
            }
        } catch (Throwable t) {
            fail("f6");
        }
        /**
         * f7
         */
        try {
            List<Integer> l = f7(Observable.range(0, 20)).toList().toBlocking().single();
            List<Integer> ll = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                ll.add(2 * i);
            }
            if (doListsMatches(l, ll)) {
                success("f7");
            } else {
                fail("f7");
            }
        } catch (Throwable t) {
            fail("f7");
        }
        /**
         * f8
         */
        try {
            if (f8().count().toBlocking().single() == 0) {
                success("f8");
            } else {
                fail("f8");
            }
        } catch (Throwable t) {
            fail("f8");
        }
        /**
         * f9
         */
        try {
            f9().onErrorResumeNext(new Func1<Throwable, Observable<? extends Object>>() {

                @Override
                public Observable<? extends Object> call(Throwable t) {
                    if (!((t instanceof RuntimeException) && t.getMessage().equals("Not Implemented"))) {
                        success("f9");
                        return Observable.just(null);
                    } else {
                        return Observable.error(t);
                    }
                }
            }).toBlocking().single();
        } catch (Throwable t) {
            fail("f9");
        }
        /**
         * f10
         */
        try {
            final List<Map.Entry<String, String>> in = new ArrayList<>();
            l1.forEach(new Consumer<String>() {
                @Override
                public void accept(final String t) {
                    in.add(new Map.Entry<String, String>() {

                        @Override
                        public String getKey() {
                            return t;
                        }

                        @Override
                        public String getValue() {
                            return t + " - " + t;
                        }

                        @Override
                        public String setValue(String v) {
                            throw new UnsupportedOperationException("Not supported");
                        }
                    });
                }
            });

            if (doListsMatches(f10(Observable.from(in)).toList().toBlocking().single(), l1)) {
                success("f10");
            } else {
                fail("f10");
            }
        } catch (Throwable t) {
            fail("f10");
        }
        /**
         * f11
         */
        try {
            final List<Map.Entry<String, String>> in = new ArrayList<>();
            l1.forEach(new Consumer<String>() {
                @Override
                public void accept(final String t) {
                    in.add(new Map.Entry<String, String>() {

                        @Override
                        public String getKey() {
                            return t;
                        }

                        @Override
                        public String getValue() {
                            return t + " - " + t;
                        }

                        @Override
                        public String setValue(String v) {
                            throw new UnsupportedOperationException("Not supported");
                        }
                    });
                }
            });

            if (doListsMatches(f11(Observable.from(in)).toBlocking().single(), l1)) {
                success("f11");
            } else {
                fail("f11");
            }
        } catch (Throwable t) {
            fail("f11");
        }
        /**
         * f12
         */
        try {
            final List<Map.Entry<String, String>> in = new ArrayList<>();
            l1.forEach(new Consumer<String>() {
                @Override
                public void accept(final String t) {
                    in.add(new Map.Entry<String, String>() {

                        @Override
                        public String getKey() {
                            return t;
                        }

                        @Override
                        public String getValue() {
                            return t + " - " + t;
                        }

                        @Override
                        public String setValue(String v) {
                            throw new UnsupportedOperationException("Not supported");
                        }
                    });
                }
            });

            if (doListsMatches(f12(Observable.from(in)).toList().toBlocking().single(), l1)) {
                success("f12");
            } else {
                fail("f12");
            }
        } catch (Throwable t) {
            fail("f12");
        }
        /**
         * f13
         */
        try {
            final List<Map.Entry<String, String>> in = new ArrayList<>();
            l1.forEach(new Consumer<String>() {
                @Override
                public void accept(final String t) {
                    in.add(new Map.Entry<String, String>() {

                        @Override
                        public String getKey() {
                            return t;
                        }

                        @Override
                        public String getValue() {
                            return t + " - " + t;
                        }

                        @Override
                        public String setValue(String v) {
                            throw new UnsupportedOperationException("Not supported");
                        }
                    });
                }
            });

            if (doListsMatches(f13(Observable.from(in)).toBlocking().single(), l1)) {
                success("f13");
            } else {
                fail("f13");
            }
        } catch (Throwable t) {
            fail("f13");
        }
        /**
         * f14
         */
        try {
            final List<Integer> in = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                in.add(i);
                in.add((int) Math.pow(i, 2));
            }

            if (doListsMatches(f14(Observable.range(0, 10)).toList().toBlocking().single(), in)) {
                success("f14");
            } else {
                fail("f14");
            }
        } catch (Throwable t) {
            fail("f14");
        }
        /**
         * f15
         */
        try {
            final List<Integer> in = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                in.add(i);
                in.add((int) Math.pow(i, 2));
            }

            if (doListsMatches(f15(Observable.range(0, 10)).toList().toBlocking().single(), in)) {
                success("f15");
            } else {
                fail("f15");
            }
        } catch (Throwable t) {
            fail("f15");
        }
        /**
         * f16
         */
        try {
            final List<Integer> in = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                in.add(i);
            }

            if (doListsMatches(f16(Observable.range(0, 20)).toList().toBlocking().single(), in)) {
                success("f16");
            } else {
                fail("f16");
            }
        } catch (Throwable t) {
            fail("f16");
        }
        /**
         * f17
         */
        try {
            final List<Integer> in = new ArrayList<>();
            for (int i = 15; i < 20; i++) {
                in.add(i);
            }

            if (doListsMatches(f17(Observable.range(0, 20)).toList().toBlocking().single(), in)) {
                success("f17");
            } else {
                fail("f17");
            }
        } catch (Throwable t) {
            fail("f17");
        }
        /**
         * f18
         */
        try {
            final List<Integer> in = new ArrayList<>();
            for (int i = 5; i < 20; i++) {
                in.add(i);
            }

            if (doListsMatches(f18(Observable.range(0, 20)).toList().toBlocking().single(), in)) {
                success("f18");
            } else {
                fail("f18");
            }
        } catch (Throwable t) {
            fail("f18");
        }
        /**
         * f19
         */
        try {
            final List<Integer> in = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                in.add(i);
            }

            if (doListsMatches(f19(Observable.range(0, 20)).toList().toBlocking().single(), in)) {
                success("f19");
            } else {
                fail("f19");
            }
        } catch (Throwable t) {
            fail("f19");
        }
        /**
         * f20
         */
        try {
            if (f20(Observable.from(l1)).count().toBlocking().single() == 0) {
                success("f20");
            } else {
                fail("f20");
            }
        } catch (Throwable t) {
            fail("f20");
        }
        /**
         * f21
         */
        try {
            if (f21(Observable.range(0, 20), 8).toBlocking().single() == 8) {
                success("f21");
            } else {
                fail("f21");
            }
        } catch (Throwable t) {
            fail("f21");
        }
        /**
         * f22
         */
        try {
            final List<String> ll = new ArrayList<>();
            l1.forEach(new Consumer<String>() {

                @Override
                public void accept(String t) {
                    ll.add(t);
                    ll.add(t);
                }
            });
            if (doListsMatches(l1, f22(Observable.from(ll)).toList().toBlocking().single())) {
                success("f22");
            } else {
                fail("f22");
            }
        } catch (Throwable t) {
            fail("f22");
        }
        /**
         * f23
         */
        try {
            final List<Integer> ll = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                ll.add(i);
            }
            if (doListsMatches(ll, f23(Observable.range(0, 10), Observable.create(new Observable.OnSubscribe<Integer>() {

                @Override
                public void call(Subscriber<? super Integer> t) {
                    try {
                        TimeUnit.SECONDS.sleep(1);

                        for (int i = 0; i < 10; i++) {
                            t.onNext(10 + i);
                        }
                        t.onCompleted();
                    } catch (InterruptedException ex) {
                        t.onError(ex);
                    }
                }
            })).toList().toBlocking().single())) {
                success("f23");
            } else {
                fail("f23");
            }
        } catch (Throwable t) {
            fail("f23");
        }
        /**
         * f24
         */
        try {

            if (f24(Observable.range(0, 35)).toBlocking().single() == 35) {
                success("f24");
            } else {
                fail("f24");
            }
        } catch (Throwable t) {
            fail("f24");
        }
        /**
         * f25
         */
        try {
            final List<Integer> ll = new ArrayList<>();
            int sum = 0;
            for (int i = 0; i < 20; i++) {
                ll.add(i);
                sum += Math.pow(i, 2);
            }
            if (f25(Observable.from(ll)).toBlocking().single() == sum) {
                success("f25");
            } else {
                fail("f25");
            }
        } catch (Throwable t) {
            fail("f25");
        }
        /**
         * f26
         */
        try {
            final List<String> ll = new ArrayList<>();
            int i = 0;
            for (String l11 : l1) {
                ll.add(l11);
            }
            ll.add("default");

            if (doListsMatches(f26(Observable.create(new Observable.OnSubscribe<String>() {

                @Override
                public void call(Subscriber<? super String> t) {
                    for (String l11 : l1) {
                        t.onNext(l11);
                    }
                    t.onError(new Exception("you're not a good guy"));
                }
            })).toList().toBlocking().single(), ll)) {
                success("f26");
            } else {
                fail("f26");
            }
        } catch (Throwable t) {
            fail("f26");
        }
        /**
         * f27
         */
        try {
            if (doListsMatches(f27(Observable.create(new Observable.OnSubscribe<String>() {

                @Override
                public void call(Subscriber<? super String> t) {
                    boolean shouldCrash = (Math.random() > 0.5);
                    for (String l11 : l1) {
                        t.onNext(l11);
                        if (shouldCrash) {
                            t.onError(new Exception("you're not a good guy"));
                            return;
                        }
                    }
                    t.onCompleted();
                }
            })).toList().toBlocking().single(), l1)) {
                success("f27");
            } else {
                fail("f27");
            }
        } catch (Throwable t) {
            fail("f27");
        }
        System.out.println("+---------------------------+");
        String sc = Integer.toString((int) Math.round(100 * (score / (score + fail))));
        switch (sc.length()) {
            case 1:
                System.out.println("|      Your score: " + sc + " %      |");
                break;
            case 2:
                System.out.println("|     Your score: " + sc + " %      |");
                break;
            case 3:
                System.out.println("|     Your score: " + sc + " %     |");
                break;
        }
        System.out.println("+---------------------------+");
    }

    public static void success(String _method) {
        score += 1.0;
        System.out.println(_method + "\tOK");
    }

    public static void fail(String _method) {
        fail += 1.0;
        System.err.println(_method + "\tFAIL");
    }

    public static <T extends Object> boolean doListsMatches(final List<T> _l1, final List<T> _l2) {
        if (_l1.size() != _l2.size()) {
            return false;
        }
        for (int i = 0; i < _l1.size(); i++) {
            if (!_l1.get(i).equals(_l2.get(i))) {
                return false;
            }
        }
        return true;
    }

}
