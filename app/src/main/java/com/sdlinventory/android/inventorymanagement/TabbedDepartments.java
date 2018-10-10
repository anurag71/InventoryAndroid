package com.sdlinventory.android.inventorymanagement;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.widget.Button;

import java.util.List;
import java.util.Locale;

public class TabbedDepartments extends Fragment {

    View v;
    FloatingActionButton fab;
    String frag_position;


    //DataPassListener mCallback;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.department_layout, container, false);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        mViewPager = v.findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        fab=v.findViewById(R.id.nav_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set up dialog
                final Dialog dialog = new Dialog(getContext());
                if (frag_position == "meat"){
                    dialog.setContentView(R.layout.gif_layout_dairy);
                }
                else if (frag_position == "staples"){
                    dialog.setContentView(R.layout.gif_layout_meat);
                }
                else {
                    dialog.setContentView(R.layout.gif_layout_staples);
                }
                dialog.setCancelable(true);

                //set up button
                Button button = (Button) dialog.findViewById(R.id.CancelButton);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                //now that the dialog is set up, it's time to show it
                dialog.show();
            }
        });

        return v;
    }



    //Custom PagerAdaptor class to implement ViewPager (swiping tabs)
    //We use FragmentPagerAdaptor because we are displaying the ViewPager inside another fragment

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        dairy_dept dairydept = new dairy_dept();
        meat_dept meat_dept = new meat_dept();
        staples_dept staples_dept = new staples_dept();
        fruits_dept fruits_dept = new fruits_dept();
        kitchen_dept kitchen_dept = new kitchen_dept();
        beauty_dept beauty_dept = new beauty_dept();
        cleaning_dept cleaning_dept = new cleaning_dept();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return dairydept;
            else if(position == 1)
                return  meat_dept;
            else if(position == 2)
                return staples_dept;
            else if(position == 3)
                return fruits_dept;
            else if(position == 4)
                return kitchen_dept;
            else if(position == 5)
                return beauty_dept;
            else
                return cleaning_dept;
        }

        @Override
        public int getCount() {
            // Shows total pages.
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0: {
                    return "Dairy";
                }
                case 1: {
                    frag_position = "meat";
                    return "Meat";
                }
                case 2:{
                    frag_position = "staples";
                    return "Staples";
                }
                case 3:{
                    frag_position = "fruits";
                    return "Fruits & Vegetables";
                }
                case 4:{
                    frag_position = "kitchen";
                    return "Kitchen";
                }
                case 5:{
                    frag_position = "beauty";
                    return "Beauty & Hygiene";
                }
                case 6:{
                    frag_position = "cleaning";
                    return "Cleaning & Household";
                }

            }
            return null;
        }
    }
}