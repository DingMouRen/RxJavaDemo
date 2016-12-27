package com.dingmouren.rxjavademo.examples.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dingmouren.rxjavademo.R;
import com.dingmouren.rxjavademo.examples.ExampleActivity;
import com.dingmouren.rxjavademo.examples.event.ClickEvent;
import com.dingmouren.rxjavademo.examples.rx.RxBus;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dingmouren on 2016/12/26.
 */

public class FragmentLeft extends Fragment {
    private RxBus mRxBus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_left_layout,container,false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRxBus = ((ExampleActivity)getActivity()).getRxBus();
    }

    @OnClick(R.id.btn_rxbus)
    public void onTabButtonClick(){
        if (mRxBus.hasObservers())
        mRxBus.post(new ClickEvent());
    }
}
