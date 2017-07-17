package com.oculus.walkthrough;

import android.animation.TimeInterpolator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by karan on 15/7/17.
 */

public class Thirdpager extends Fragment {
    View rootView;
    View view,upperView;
    CompositeDisposable compositeDisposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(false);
        compositeDisposable=new CompositeDisposable();

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_third,container,false);
        view=rootView.findViewById(R.id.view1);
        upperView=rootView.findViewById(R.id.view2);
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(rootView!=null){
            view.clearAnimation();
            if (view.getY() != upperView.getY()) {
                Log.d("vurr",view.getTranslationY()+"");
                view.animate().setDuration(0).translationYBy(-view.getTranslationY());
//           view.translationYBy(-view.getHeight()).start();
            }
        }
        if(isVisibleToUser){

            TimeInterpolator interpolator=new AccelerateInterpolator();
            view.animate().setInterpolator(interpolator).setDuration(500)
                    .translationY(view.getHeight()).start();
        }



    }


}
