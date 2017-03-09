package com.zzq.zzq_collapsing_demo_master.api;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by cc on 16/7/20.
 */
public class RxResultHelper {


    public static <T> Observable.Transformer<T, T> handleResult() {


        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> apiResponseObservable) {

                return apiResponseObservable.flatMap(new Func1<T, Observable<T>>() {
                                                         @Override
                                                         public Observable<T> call(T tApiResponse) {
                                                             return createData(tApiResponse);
                                                         }
                                                     }
                );
            }
        };


    }


    public static <T> Observable<T> createData(T t) {

        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {

                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

    }
}

