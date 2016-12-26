package com.dingmouren.rxjavademo.examples.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingmouren.rxjavademo.R;
import com.dingmouren.rxjavademo.examples.ExampleActivity;
import com.dingmouren.rxjavademo.examples.event.ClickEvent;
import com.dingmouren.rxjavademo.rx.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/26.
 */

public class FragmentRight extends Fragment {
    @BindView(R.id.tv_rxbus) TextView tvRxBus;
    private AnimatorSet animatorSet;
    private RxBus rxBus;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_right_layout,container,false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rxBus = ((ExampleActivity)getActivity()).getRxBus();
        rxBus.toObservable(ClickEvent.class).subscribe(new Action1<ClickEvent>() {
            @Override
            public void call(ClickEvent clickEvent) {
                sartAnimation();
            }
        });
    }

    private void sartAnimation(){
        animatorSet = new AnimatorSet();
        animatorSet.setDuration(1500);
        animatorSet.playTogether(ObjectAnimator.ofFloat(tvRxBus, "scaleX", new float[]{1.0f, 1.25f, 0.75f, 1.15f, 1.0f}),
                ObjectAnimator.ofFloat(tvRxBus, "scaleY", new float[]{1.0f, 0.75f, 1.25f, 0.85f, 1.0f}));
        animatorSet.start();
    }

}
