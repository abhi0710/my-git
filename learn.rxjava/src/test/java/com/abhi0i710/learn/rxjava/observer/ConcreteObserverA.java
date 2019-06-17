package com.abhi0i710.learn.rxjava.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ConcreteObserverA<String> implements Observer<String> {

    @Override
    public void onSubscribe(Disposable d) {
        System.out.println("Subscribed");
    }

    @Override
    public void onNext(String s) {
        System.out.println("A Recevied " + s + " " + Thread.currentThread().getName());
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
