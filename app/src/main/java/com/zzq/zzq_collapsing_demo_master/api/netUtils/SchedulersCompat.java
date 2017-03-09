package com.zzq.zzq_collapsing_demo_master.api.netUtils;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cc on 16/6/13.
 */
public class SchedulersCompat {

    private final static Observable.Transformer ioTransformer = o -> ((Observable) o).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    public static <T> Observable.Transformer<T, T> applyIoSchedulers() {
        return (Observable.Transformer<T, T>) ioTransformer;
    }
}
