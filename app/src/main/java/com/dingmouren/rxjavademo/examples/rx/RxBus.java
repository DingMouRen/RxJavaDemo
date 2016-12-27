package com.dingmouren.rxjavademo.examples.rx;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

/**
 * Created by dingmouren on 2016/12/26.
 */

public class RxBus {
    private static volatile RxBus instance;
    private final SerializedSubject<Object,Object> subject;

    private RxBus(){
        subject = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getInstance(){
        if (instance == null){
            synchronized (RxBus.class){
                if (instance == null){
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    public void post(Object object){
        subject.onNext(object);
    }

    public  <T>Observable<T> toObservable(final Class<T> type){
        return subject.ofType(type);
    }

    public boolean hasObservers(){
        return subject.hasObservers();
    }


}
