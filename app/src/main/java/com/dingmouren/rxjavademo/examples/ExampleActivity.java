package com.dingmouren.rxjavademo.examples;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dingmouren.rxjavademo.MainActivity;
import com.dingmouren.rxjavademo.R;
import com.dingmouren.rxjavademo.examples.fragment.FragmentLeft;
import com.dingmouren.rxjavademo.examples.fragment.FragmentRight;
import com.dingmouren.rxjavademo.examples.rx.RxBus;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subscribers.DisposableSubscriber;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

/**
 * Created by dingmouren on 2016/12/26.
 * 1.PublishSubject与普通的Subject不同，在订阅时并不会立即触发订阅事件，允许我们在任意时刻手动调用onNext(),onError(),onCompleted()来触发事件。
 * 2.SerializedSubject在并发的情况下，只允许一个线程调用onNext()方法（源码中使用了同步锁）
 */

public class ExampleActivity extends AppCompatActivity {
    //sum
    @BindView(R.id.edit1) EditText edit1Sum;
    @BindView(R.id.edit2)  EditText edit2Sum;
    @BindView(R.id.sum) TextView textSum;
    private PublishSubject<Float> publishSubject;
    private Subscription subscription;
    //rxbus
    public RxBus mRxBus;
    //搜索
    @BindView(R.id.edit_search) EditText editSearch;
    @BindView(R.id.img_delete)  ImageButton imgDeleteSearch;
    @BindView(R.id.viewstub_search) ViewStub viewStubSearch;
    private View viewGroup;
    private TextView searchTvContent;
    DisposableObserver observer;
    //验证表单
    @BindView(R.id.edit_form_email) EditText editFormEmail;
    @BindView(R.id.edit_form_password) EditText editFormPassword;
    @BindView(R.id.edit_form_number) EditText editFormNumber;
    @BindView(R.id.btn_form_submit)  Button btnFormSubmit;
    private Flowable<CharSequence> emailChangeObservable;
    private Flowable<CharSequence> passwordChangeObservable;
    private Flowable<CharSequence> numberChangeObservable;
    private DisposableSubscriber<Boolean> disposableSubscriber = null;
    private boolean isValid = false;
    public static void newInstance(Context context){
        context.startActivity(new Intent(context,ExampleActivity.class));
        ((MainActivity)context).finish();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        ButterKnife.bind(this);
        sum();//加法运算
        rxBus();//rxbus的使用
        search();//搜索
        form();//验证表单
    }

    /**
     * 1.加法运算：监听两个EditText文本的变化，一旦变化PublishSubject就发射两个EditText中数字的和，没有变化就不发射。
     */
    private void sum() {
        publishSubject = PublishSubject.create();
        subscription = publishSubject.subscribe(new Action1<Float>() {
            @Override
            public void call(Float aFloat) {
                textSum.setText(String.valueOf(aFloat));
            }
        });
        onNumberChanged();//初始化的时候调用一次，进行首次运算
    }

    /**
     * 2.rxbus的使用：两个Fragment实现通信，点击左边的Fragment,右边的Fragment中文字开始动画
     */
    private void rxBus() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment1,new FragmentLeft())
                .replace(R.id.fragment2,new FragmentRight())
                .commit();

    }

    /**
     * 3.搜索：当输入输入框时，它不会再每一个输入字符的变化中记录日志消息，而是在400毫秒内没有发射新的数据就使用此数据，如果有新的数据，
     * 也就是edittext的文本发生了变化，就舍弃前面的数据，记录这个数据，如果在400毫秒中没有数据变化，就把这个新数据提交给订阅者
     */
    private void search(){
        viewGroup = viewStubSearch.inflate();
        searchTvContent = (TextView) viewGroup.findViewById(R.id.tv_search_content);
        observer = RxJavaInterop.toV2Observable(RxTextView.textChangeEvents(editSearch))
                .debounce(400, TimeUnit.MILLISECONDS)//默认在computation调度器上运行
                .filter(new Predicate<TextViewTextChangeEvent>() {
                    @Override
                    public boolean test(TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                        return !TextUtils.isEmpty(editSearch.getText().toString());
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<TextViewTextChangeEvent>() {
                    @Override
                    public void onNext(TextViewTextChangeEvent value) {
                        searchTvContent.setText(value.text().toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //点击就清除搜索框和下面的TextView
        imgDeleteSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editSearch.setText("");
                searchTvContent.setText("");
            }
        });
    }

    /**
     * 4.验证表单
     */
    private void form(){
        emailChangeObservable = RxJavaInterop.toV2Flowable(RxTextView.textChanges(editFormEmail).skip(1));
        passwordChangeObservable = RxJavaInterop.toV2Flowable(RxTextView.textChanges(editFormPassword).skip(1));
        numberChangeObservable = RxJavaInterop.toV2Flowable(RxTextView.textChanges(editFormNumber).skip(1));
        //订阅者
        disposableSubscriber = new DisposableSubscriber<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                isValid = aBoolean;
                if (aBoolean){
                    btnFormSubmit.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                }else {
                    btnFormSubmit.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                }
            }
            @Override
            public void onError(Throwable t) {
            }
            @Override
            public void onComplete() {
            }
        };
        //验证过程
        Flowable.combineLatest(emailChangeObservable, passwordChangeObservable, numberChangeObservable, new Function3<CharSequence, CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence email, CharSequence password, CharSequence number) throws Exception {
                //验证邮箱是否合法
                boolean emailValid = !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS .matcher(email).matches();
                if (!emailValid) editFormEmail.setError("邮箱不合法");
                //验证密码是否合法
                boolean passwordValid = !TextUtils.isEmpty(password) && password.length() > 8;
                if (!passwordValid) editFormPassword.setError("密码不合法");
                //验证数字是否合法
                boolean numberValid = !TextUtils.isEmpty(number);
                if (numberValid){
                    int num = Integer.parseInt(number.toString());
                    numberValid = num > 1 && num < 100;
                }
                if (!numberValid) editFormNumber.setError("数字不在正确的范围内");
                return emailValid && passwordValid && numberValid;
            }
        }).subscribe(disposableSubscriber);
        //提交按钮的点击监听
        btnFormSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid) {
                    Snackbar.make(btnFormSubmit, "提交成功", Snackbar.LENGTH_SHORT).show();
                }else {
                    Snackbar.make(btnFormSubmit, "提交内容不合法", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
   }

    //获取RxBus对象
    public RxBus getRxBus(){
        if (mRxBus == null){
            mRxBus = RxBus.getInstance();
        }
        return mRxBus;
    }

    //EditText在文本变化时调用
    @OnTextChanged({R.id.edit1,R.id.edit2})
    public void onNumberChanged(){
        float num1 = 0;
        float num2 = 0;
        if (!TextUtils.isEmpty(edit1Sum.getText().toString())){
            num1 = Float.parseFloat(edit1Sum.getText().toString());
        }
        if (!TextUtils.isEmpty(edit2Sum.getText().toString())){
            num2 = Float.parseFloat(edit2Sum.getText().toString());
        }

        publishSubject.onNext(num1 + num2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //sum
        if (!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        //搜索
        if (!observer.isDisposed()){
            observer.dispose();
        }
        //表单验证
        if (!disposableSubscriber.isDisposed()){
            disposableSubscriber.dispose();
        }
    }
}