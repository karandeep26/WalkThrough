package com.oculus.walkthrough;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by karan on 13/7/17.
 */

public class FirstPager extends Fragment  {
    ButtonProgressBar right, left;
    TextView textView;
    ImageView imageView;
    View rootView;
    CompositeDisposable compositeDisposable;
    Disposable disposable2;
    private Boolean isStarted = false;
    static Boolean firstTime=true;
    private Boolean isVisible = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onStart() {
        super.onStart();
        isStarted = true;
        if (isVisible && isStarted&&firstTime){
            firstTime=false;
            animate();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_firstpage, container, false);
        right = (ButtonProgressBar) rootView.findViewById(R.id.right);
        left = (ButtonProgressBar) rootView.findViewById(R.id.left);
        right.setLoaderType(github.ishaan.buttonprogressbar.ButtonProgressBar.Type.DETERMINATE);
        left.setLoaderType(github.ishaan.buttonprogressbar.ButtonProgressBar.Type.DETERMINATE);
        right.setProgressColor(Color.parseColor("#4D3F51B5"));
        left.setProgressColor(Color.parseColor("#4D3F51B5"));
        compositeDisposable = new CompositeDisposable();
        textView = (TextView) rootView.findViewById(R.id.textView);
        imageView = (ImageView) rootView.findViewById(R.id.image);
        left.setRotation(180);
        left.startLoader();
        right.startLoader();
        Log.i("visibility-1",getUserVisibleHint()+"");
        return rootView;
    }

    Observable<Integer> get(int first, int last) {
        return Observable.zip(Observable.range(first, last),
                Observable.interval(5, TimeUnit.MILLISECONDS), (integer, aLong) -> integer)
                .observeOn(AndroidSchedulers.mainThread());
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible=isVisibleToUser;

        if (rootView != null) {
            imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_add_light_blue_400_24dp));
            textView.setText("Connect");
            left.setProgress(0);
            right.setProgress(0);

        }
        if (isVisibleToUser && rootView != null&&compositeDisposable.size()==0) {
            animate();
        }
        else{
            if(compositeDisposable!=null){
                compositeDisposable.dispose();
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
    private void animate(){
        compositeDisposable=new CompositeDisposable();
        left.startLoader();
        right.startLoader();
        compositeDisposable.add(get(0, 100).subscribe(integer -> {
            right.setProgress(integer);
            left.setProgress(integer);

        }, throwable -> {

        }, () -> {
            imageView.setImageDrawable(ContextCompat.
                    getDrawable(getContext(), R.drawable.ic_done_light_blue_400_24dp));
            textView.setText("Connected");
            compositeDisposable.add(get(0, 101).subscribe(integer -> {
                integer = 100 - integer;
                left.setProgress(integer);
                right.setProgress(integer);
            }, throwable -> {
            }, () -> {

            }));

        }));
    }


}