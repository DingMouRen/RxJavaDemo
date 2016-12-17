package com.dingmouren.rxjavademo;

import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

        timerDemo();
    }

    /**
     * 创建操作符timer:创建一个Observable,它在一个给定的延迟后发射一个特殊的值0
     * 默认在computation调度器上运行，可以通过参数指定运行的线程
     */
    private void timerDemo(){
        Observable.timer(5, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("onNext:" + aLong +" 所在线程:"+ Thread.currentThread().getName());
            }
        });
    }

    /**
     * 创建操作符interval:创建一个按固定时间间隔发射整数序列的Observable，有多个构造函数，
     * 第一个参数：第一次延时多久发射数据 ，第二个参数：发射数据的时间间隔（从第二次开始），第三个参数：时间单位，第四个参数：指定发射数据所在的线程
     * interval默认的线程是在computation  调度器上，
     * 使用场景一：网络重连，没有连接上网络就一直去连接，直到连接上。
     *
     */
    private void intervalDemo(){
        Observable.interval(3,2,TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted:");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:");
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("onNext:"+aLong+" 所在线程："+ Thread.currentThread().getName());
            }
        });
    }

}
