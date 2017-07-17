package com.oculus.walkthrough;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private int mCurrentFragmentPosition;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager= (ViewPager) findViewById(R.id.viewPager);
        SwipePager pagerAdapter=new SwipePager(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        CircleIndicator indicator= (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
        float lastPosition=.50f;

    }
    static class SwipePager extends FragmentStatePagerAdapter{


        public SwipePager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new FirstPager();
                case 1:
                    return new SecondPager();
                case 2:
                    return new Thirdpager();
            }
            return new FirstPager();
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }

}
