package com.example.castle.csite.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.castle.csite.network.api.ApiService;
import com.example.castle.csite.view.BindLayout;

import java.lang.annotation.Annotation;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment extends Fragment {
    //Fragment Id
    private static final AtomicInteger lastFragmentId = new AtomicInteger(0);
    private final int fragmentId;
    public BaseFragment() {
        fragmentId = lastFragmentId.incrementAndGet();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Class cls = getClass();
        if (!cls.isAnnotationPresent(BindLayout.class)) return null;
        Annotation annotation = cls.getAnnotation(BindLayout.class);
        BindLayout bindLayout = (BindLayout) annotation;
        View view = inflater.inflate(bindLayout.id(), container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        getInteractor().unsubscribe();
    }

    public String getFragmentName() {
        return Long.toString(fragmentId);
    }


    protected abstract void initData();
    protected abstract Interactor getInteractor();



    protected  ApiService getApi(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient())
                .baseUrl(baseUrl)
                .build();
        return retrofit.create(ApiService.class);
    }

    /**
     * modle层与数据层之间的层，用于数据获取
     *
     * @param <ResultType>
     * @param <ParameterType>
     */
    public static abstract class Interactor<ResultType, ParameterType> {
        //这个类会集合所有的没有取消订阅的subscription, 可以利用它统一取消
        private final CompositeSubscription subscription = new CompositeSubscription();
        //线程调度
        //工作线程
        Scheduler jobScheduler = Schedulers.io();
        //ui线程
        Scheduler uiScheduler = AndroidSchedulers.mainThread();

        public Interactor() {

        }

        /**
         * 得到结果观察对象
         *
         * @param parameter 参数类型
         * @return 结果类型的观察对象
         */
        protected abstract Observable<ResultType> buildObservable(ParameterType[] parameter);

        /**
         * 进行观察
         *
         * @param parameter  参数类型,使用可变参数加强灵活性
         * @param subscriber 观察者
         */
        public void execute(Subscriber<ResultType> subscriber, ParameterType[] parameter) {
            subscription.add(buildObservable(parameter).subscribeOn(jobScheduler)
                    .observeOn(uiScheduler)
                    .subscribe(subscriber));
        }

        /**
         * 进行一次空观察
         *
         * @param subscriber 观察者
         */
        public void execute(Subscriber<ResultType> subscriber) {
            execute(subscriber, null);
        }

        /**
         * 释放所有观察者
         */
        public void unsubscribe() {
            subscription.clear();
        }
    }

}
