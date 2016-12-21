package com.dingmouren.rxjavademo;

import android.os.SystemClock;
import android.support.annotation.IntegerRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

import rx.Notification;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;



public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

//        timerDemo();
//        intervalDemo();
//        switchMapDemo();
//        groupByDemo();
//        windowDemo();
//        debounceDemo();
//        mergeDemo();
//        switchOnNextDemo();
//        delayDemo();
//        takeUntilDemo();
//        skipUntilDemo();
        takeWhileDemo();
    }

    /**
     * 创建操作符timer:创建一个Observable,它在一个给定的延迟后发射一个特殊的值0
     * 默认在computation调度器上运行，可以通过参数指定运行的线程
     */
    private void timerDemo(){
        Observable.timer(5, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("timer onCompleted:");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("timer onError:");
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("timer onNext:" + aLong +" 所在线程:"+ Thread.currentThread().getName());
            }
        });
    }

    /**
     * 创建操作符interval:创建一个按固定时间间隔发射整数序列的Observable，有多个重载方法，
     * 第一个参数：第一次延时多久发射数据 ，第二个参数：发射数据的时间间隔（从第二次开始），第三个参数：时间单位，第四个参数：指定发射数据所在的线程
     * interval默认的线程是在computation  调度器上，
     * 使用场景一：网络重连，没有连接上网络就一直去连接，直到连接上。
     *
     */
    private void intervalDemo(){
        Observable.interval(3,2,TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("interval onCompleted:");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("interval onError:");
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("interval onNext:"+aLong+" 所在线程："+ Thread.currentThread().getName());
            }
        });
    }

    /**
     * switchMap操作符:类似于flatMap(),有一点不同，只监视当前Observable，或者是最后发射的数据。需要延时执行，当是延时是0 的时候回发射第一个数据，延时是大于0的任何值都是发射最后一个值
     * 有一个使用场景：搜索的时候，在输入完毕之后再去请求数据，大可不必没每输入一个都要发送请求。
     */
    private void switchMapDemo(){
        Observable.just(10, 20, 30).switchMap(new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {

                return Observable.just(integer).delay(0, TimeUnit.MILLISECONDS);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("switchMap onNext:" + integer);
            }
        });
    }

    /**
     * groupBy操作符:对原来Observable发射的数据进行分组，形成一个GroupedObservable的结果集，GroupedObservable.getKey()可以获取键,
     * 注意：由于结果集中的GroupedObservable是把分组结果缓存起来，如果对每一个GroupedObservable不进行处理（既不订阅也不对其进行别的操作符运算）
     * 就有可能出现内存泄漏，所以如果不对GroupedObservable进行处理，最好对其使用操作符take(0)处理
     *
     */
    private void groupByDemo(){
        Observable.just(1,2,3,4).take(4).groupBy(new Func1<Integer, String>() {
            @Override
            public String call(Integer value) {
                return value < 3 ? "第一组":"第二组"; //这里执行分组的函数
            }
        }).subscribe(new Action1<GroupedObservable<String, Integer>>() {
            @Override
            public void call(final GroupedObservable<String, Integer> result) {
                result.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer value) {
                        System.out.println("GroupBy  onNext " + result.getKey() +":"+ value);
                    }
                });
            }
        });
    }

    /**
     * window操作符:类似于buffer()操作符，区别在于buffer操作符产生的结果是List缓存，而window()操作符产生的是一个Observable对象
     * 订阅者可以对这个产生的Observable对象重新进行订阅处理
     * window操作符有很多重载方法
     */
    private void windowDemo(){
        Observable.interval(1, TimeUnit.SECONDS).take(6)
                .window(3,TimeUnit.SECONDS).subscribe(new Action1<Observable<Long>>() {
            @Override
            public void call(Observable<Long> observable) {
                observable.subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("window onNext " + aLong);
                    }
                });
            }
        });
    }

    /**
     *  debounce操作符：源Observable每发射一个数据项，如果在debounce规定的间隔时间内Observable没有发射新的数据项，debounce就把这个结果提交给订阅者处理，如果在规定的间隔时间内产生了其他结果
     *  ，就忽略掉发射的这个数据,通过制定的时间间隔来限流，可以过滤掉发射速率过快的数据项，默认在computatiion调度器上执行，可以指定执行线程。
     *  注意：如果源Observable发射最后一个数据后，在debounce规定的时间间隔内调用了onCompleted，那么通过debounce操作符就把这个结果提交给订阅者
     *  throttleWithTimeOut使用也是调用了debounce操作符来实现
     */
    private void debounceDemo(){
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;//如果没有订阅者就直接返回
                try {
                    //发射数据的时间间隔：100~900毫秒，
                    for (int i = 0; i < 10; i++) {
                        subscriber.onNext(i);
                        Thread.sleep(i * 100);
                    }
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .debounce(400, TimeUnit.MILLISECONDS)//超时时间为400毫秒，预期结果：时间间隔在400毫秒以上的数据都会提交给订阅者，其他的不会。
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("debounce onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
                    }
                });
    }

    /**
     *  merge操作符将多个Observalbe发射的数据项，合并到一个Observable中再发射出去，可能会让合并的Observable发射的数据交错（concat是连接不会出现交错），
     *  如果在合并的途中出现错误，就会立即将错误提交给订阅者，将终止合并后的Observable
     *  mergeDelayError操作符类似于merge操作符，唯一不同就是如果在合并途中出现错误，不会立即发射错误通知，而是保留错误直到合并后的Observable将所有的数据发射完成，
     *  此时才会将onError提交给订阅者。
     *  合并多个Observable也可以通过传递一个Observalbe列表List、数组。
     */
    private void mergeDemo(){
        Observable<Integer> ob1 = Observable.just(1,2,3).delay(100, TimeUnit.MILLISECONDS);
        Observable<Integer> ob2 = Observable.just(4,5,6)/*.delay(100, TimeUnit.MILLISECONDS)*/;

        Observable.merge(ob1,ob2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("merge(ob1,ob2) onNext:" + integer);
            }
        });
    }

    /**
     *
     */
    private void switchOnNextDemo(){
        //每隔500毫秒产生一个observable
        Observable<Observable<Long>> observable = Observable.timer(0, 500, TimeUnit.MILLISECONDS).map(new Func1<Long, Observable<Long>>() {
            @Override
            public Observable<Long> call(Long aLong) {
                //每隔200毫秒产生一组数据（0,10,20,30,40)
                return Observable.timer(0, 200, TimeUnit.MILLISECONDS).map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 10;
                    }
                }).take(5);
            }
        }).take(2);

        Observable.switchOnNext(observable).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("Next:" + aLong);
            }
        });
    }

    /**
     * delay操作符将源Observable对象延时指定时间后再发射数据，可以指定执行的线程，delay不会延迟onError通知，会延时onComplete通知
     */
    private void delayDemo(){
        Observable.just(1,2,3).delay(5,TimeUnit.SECONDS,AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("delay(long,TimeUnit) onNext : " + integer + " 所在线程："+ Thread.currentThread().getName());
            }
        });
        System.out.println("- - - - - - - - - -");
        Observable.just(1,2,3).delay(new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                System.out.println("delay(Func1) integet:" + integer);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Observable.just(integer);
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("delay(Func1) onNext:" +  integer);
            }
        });
    }

    /**
     * takeUntil操作符：假设源Observable在一直发射数据，在第二个Observable发射数据或者终止了，操作符就丢弃源Observable发射的
     * 任何数据，时间点就是第二个Observable发射数据或者直接终止了。
     * 还有一个变体takeUntil(Func1) 通过一个函数来判断是佛需要终止发射数据，跟takeWhile相似
     */
    private void takeUntilDemo() {
        Observable<String> ob1 = Observable.just("一", "二", "三").delay(3, TimeUnit.SECONDS);
        Observable.interval(0, 1, TimeUnit.SECONDS).takeUntil(ob1).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("takeUntil(ob) onNext:" + aLong);
            }
        });
    }

    /**
     * skipUntil操作符监听源Observable，但是不会提交数据项给订阅者，直到第二个Observable开始发射数据，才会将源Observable发射的数据项提交给订阅者
     */
    private void skipUntilDemo(){
        Observable<String> ob1 = Observable.just("一", "二", "三").delay(3, TimeUnit.SECONDS);
        Observable.interval(0, 1, TimeUnit.SECONDS).skipUntil(ob1).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("skipUntil(ob) onNext:" + aLong);
            }
        });
    }

    /**
     * takeWhile操作符：假设源Observable一直在发射数据，takeWhile(Func1)直到函数中的条件不成立时，就会丢弃源Observable发射的数据、
     * 时间点就是函数中的条件不成立
     */
    private void takeWhileDemo(){
        Observable.interval(0,1,TimeUnit.SECONDS).takeWhile(new Func1<Long, Boolean>() {
            @Override
            public Boolean call(Long aLong) {
                return aLong < 6;
            }
        }).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("takeWhile(Func1) onNext:" + aLong);
            }
        });
    }
}



