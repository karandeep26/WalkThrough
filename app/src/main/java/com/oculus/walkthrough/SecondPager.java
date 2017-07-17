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
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


/**
 * Created by karan on 14/7/17.
 */

public class SecondPager extends Fragment  {

    ImageView firstImage,secondImage,thirdImage;
    View view1,view2,view3,rootView;
    int count;
    private Animation zoomout,zoomin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(false);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_second,container,false);
        firstImage = (ImageView) rootView.findViewById(R.id.image);
        secondImage= (ImageView) rootView.findViewById(R.id.image2);
        thirdImage= (ImageView) rootView.findViewById(R.id.image3);
        view1 =rootView.findViewById(R.id.view);
        view2=rootView.findViewById(R.id.view2);
        view3=rootView.findViewById(R.id.view3);
        return rootView;
    }




    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(rootView!=null){
            resetViews();

        }
        if(isVisibleToUser) {
            count=0;
            resetViews();
            zoomin = AnimationUtils.loadAnimation(getContext(), R.anim.zoomin);
            zoomout = AnimationUtils.loadAnimation(getContext(), R.anim.zoomout);

            zoomout.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    count++;
                    if(count==1) {
                        view1.clearAnimation();
                        view2.startAnimation(zoomin);
                    }
                    else if(count==2) {
                        view2.clearAnimation();
                        view3.startAnimation(zoomin);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            zoomin.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                    switch (count){
                        case 0:
                            startAnimation(view1,firstImage);
                            break;
                        case 1:
                            startAnimation(view2,secondImage);
                            break;
                        case 2:
                            startAnimation(view3,thirdImage);
                            break;
                    }
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    switch (count){
                        case 0:
                            view1.startAnimation(zoomout);
                            break;
                        case 1:
                            view2.startAnimation(zoomout);
                            break;
                        case 2:
                            view3.startAnimation(zoomout);
                            break;
                    }

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            view1.startAnimation(zoomin);

        }
        else {
            if(rootView!=null){
                resetViews();
            }
        }

        super.setUserVisibleHint(isVisibleToUser);

    }
    void resetViews(){

        firstImage.setImageDrawable(null);
        secondImage.setImageDrawable(null);
        thirdImage.setImageDrawable(null);
        view1.setVisibility(View.INVISIBLE);
        view2.setVisibility(View.INVISIBLE);
        view3.setVisibility(View.INVISIBLE);
        view2.clearAnimation();
        view1.clearAnimation();
        view3.clearAnimation();
        if(zoomin!=null){
            zoomin.setAnimationListener(null);
            zoomout.setAnimationListener(null);
        }
    }
    void startAnimation(View view,ImageView imageView){
        view.setScaleY(0);
        view.setScaleX(0);
        imageView.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.a));
        view.setVisibility(View.VISIBLE);
        view.setScaleY(1);
        view.setScaleX(1);
    }




}
