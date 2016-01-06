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
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 *
 * @author cambierr
 */
public class MoreTransformation {

    public static void main(String[] args) {

        /**
         * OSMCities.getCitiesAsList("Mons") return an Observable that will emit a list of cities matching your request
         */
        new OSMCities()
                .getCitiesAsList("Mons")
                .subscribe(new Action1<List<OSMCities.OSMCity>>() {

                    @Override
                    public void call(List<OSMCities.OSMCity> arg0) {
                        for (OSMCities.OSMCity city : arg0) {
                            System.out.println(city.getName());
                        }
                    }
                });
        System.out.println("------------------------------------------------------------");

        /**
         * Remember about not doing any work in the subscriber ?
         */
        new OSMCities()
                .getCitiesAsList("Mons")
                .flatMap(new Func1<List<OSMCities.OSMCity>, Observable<OSMCities.OSMCity>>() { //flatMap power ! flatMap can return 0 to n elements, or errors

                    @Override
                    public Observable<OSMCities.OSMCity> call(List<OSMCities.OSMCity> arg0) {
                        return Observable.from(arg0);
                    }
                })
                .map(new Func1<OSMCities.OSMCity, String>() {

                    @Override
                    public String call(OSMCities.OSMCity arg0) {
                        return arg0.getName();
                    }
                })
                .subscribe(new Action1<String>() {

                    @Override
                    public void call(String arg0) {
                        System.out.println(arg0);
                    }
                });
        System.out.println("------------------------------------------------------------");
        /**
         * flatMap, again
         */
        new OSMCities()
                .getCitiesAsList("Mons")
                .flatMap(new Func1<List<OSMCities.OSMCity>, Observable<OSMCities.OSMCity>>() { //flatMap power ! flatMap can return 0 to n elements, or errors

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
                .subscribe(new Action1<String>() {

                    @Override
                    public void call(String arg0) {
                        System.out.println(arg0);
                    }
                });
        System.out.println("------------------------------------------------------------");
        /**
         * what if I only want big cities ?
         */
        new OSMCities()
                .getCitiesAsList("Mons")
                .flatMap(new Func1<List<OSMCities.OSMCity>, Observable<OSMCities.OSMCity>>() { //flatMap power ! flatMap can return 0 to n elements, or errors

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
                .filter(new Func1<Integer, Boolean>() {

                    @Override
                    public Boolean call(Integer arg0) {
                        return arg0 > 5000;
                    }
                })
                .map(new Func1<Integer, String>() {

                    @Override
                    public String call(Integer arg0) {
                        return arg0.toString();
                    }
                })
                .subscribe(new Action1<String>() {

                    @Override
                    public void call(String arg0) {
                        System.out.println(arg0);
                    }
                });
        System.out.println("------------------------------------------------------------");
        /**
         * what if I only want big cities ?, but only first two ?
         */
        new OSMCities()
                .getCitiesAsList("Mons")
                .flatMap(new Func1<List<OSMCities.OSMCity>, Observable<OSMCities.OSMCity>>() { //flatMap power ! flatMap can return 0 to n elements, or errors

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
                .filter(new Func1<Integer, Boolean>() {

                    @Override
                    public Boolean call(Integer arg0) {
                        return arg0 > 5000;
                    }
                })
                .take(2)
                .map(new Func1<Integer, String>() {

                    @Override
                    public String call(Integer arg0) {
                        return arg0.toString();
                    }
                })
                .subscribe(new Action1<String>() {

                    @Override
                    public void call(String arg0) {
                        System.out.println(arg0);
                    }
                });

    }

}
