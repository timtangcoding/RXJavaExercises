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

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;
import rx.Observable;
import rx.Subscriber;

/**
 *
 * @author cambierr
 */
public class OSMCities {

    private Observable<OSMCity> getCities(final String _search) {
        return Observable.create(new Observable.OnSubscribe<OSMCity>() {

            @Override
            public void call(Subscriber<? super OSMCity> arg0) {
                try {
                    arg0.onStart();
                    JsonNode json = Unirest
                            .get("https://nominatim.openstreetmap.org/search.php?format=json&q=" + _search)
                            .asJson()
                            .getBody();

                    JSONArray array = json.getArray();

                    for (int i = 0; i < array.length(); i++) {
                        arg0.onNext(new OSMCity(array.getJSONObject(i)));
                    }
                    arg0.onCompleted();

                } catch (UnirestException ex) {
                    arg0.onError(ex);
                }
            }
        });
    }

    public Observable<List<OSMCity>> getCitiesAsList(final String _search) {
        return getCities(_search).toList();
    }

    public class OSMCity {

        final JSONObject data;

        public OSMCity(JSONObject _data) {
            data = _data;
        }

        public double getLon() {
            return data.getDouble("lon");
        }

        public double getLat() {
            return data.getDouble("lat");
        }

        public String getName() {
            return data.getString("display_name");
        }

        public Observable<Integer> getPopulation() {
            return Observable.create(new Observable.OnSubscribe<Integer>() {

                @Override
                public void call(Subscriber<? super Integer> arg0) {
                    arg0.onStart();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        arg0.onNext(Math.abs(getName().hashCode() % 10000));
                        arg0.onCompleted();
                    } catch (InterruptedException ex) {
                        arg0.onError(ex);
                    }
                }
            });
        }

    }
}
