package com.abhi0i710.learn.rxjava.observer;

import io.reactivex.subjects.BehaviorSubject;

public class ObserverTest {

    public static void main(String[] args) {
        ConcreteObserverA a = new ConcreteObserverA();
        BehaviorSubject<Object> objectBehaviorSubject = BehaviorSubject.create();

        objectBehaviorSubject.safeSubscribe(a);

        objectBehaviorSubject.onNext(5);

        objectBehaviorSubject.onNext(10);



    }
}
