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

import com.couchbase.client.java.document.json.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import rx.Observable;
import rx.Subscriber;

/**
 *
 * @author cambierr
 */
public class C {

    public static void main(String[] args) {

        /**
         * This Operator gives you the power of creating any Observable
         * In this case, it will emit integers between 0 and 10
         */
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> arg0) {
                for (int i = 0; i <= 10; i++) {
                    arg0.onNext(i);
                }
                arg0.onCompleted();
            }
        });

        /**
         * This custom Operator will return 3 jokes about Chuck Norris:
         */
        Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> arg0) {
                try {
                    URLConnection link = new URL("http://api.icndb.com/jokes/random/3").openConnection();
                    link.setDoOutput(true);
                    BufferedReader br = new BufferedReader(new InputStreamReader(link.getInputStream()));
                    String json = "";
                    String l = null;
                    while ((l = br.readLine()) != null) {
                        json += l;
                    }
                    br.close();
                    JsonObject response = JsonObject.fromJson(json);
                    for (int i = 0; i < response.getArray("value").size(); i++) {
                        arg0.onNext(response.getArray("value").getObject(i).getString("joke"));
                    }
                    arg0.onCompleted();
                } catch (Exception ex) {
                    arg0.onError(ex);
                }
            }
        });

    }

}
