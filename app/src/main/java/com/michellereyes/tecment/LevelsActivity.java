package com.michellereyes.tecment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LevelsActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    public static class Level1Fragment extends android.support.v4.app.Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public Level1Fragment() {

        }

        public static Level1Fragment newInstance(int sectionNumber) {
            Level1Fragment fragment = new Level1Fragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_level1, container, false);
            Button button = (Button) rootView.findViewById(R.id.btn_do_level1);
            Button buttonAns = (Button)rootView.findViewById(R.id.btn_check_level1);

            SharedPreferences pref = getActivity().getSharedPreferences("Usuario", 0);
            String done = pref.getString("nivelUnoHecho",null);

            if(done.equals("true")){
                button.setEnabled(false);
                buttonAns.setEnabled(true);
            }else{
                buttonAns.setEnabled(false);
            }

            buttonAns.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Context context;
                    context = getActivity();
                    Intent intent = new Intent(context, TotalActivity.class);
                    startActivity(intent);
                }
            });

            button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Context context;
                    context = getActivity();
                    Intent intent = new Intent(context, Level1Activity.class);
                    startActivity(intent);
                }
            });

            return rootView;
        }
    }

    public static class Level2Fragment extends android.support.v4.app.Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public Level2Fragment() {
        }

        public static Level2Fragment newInstance(int sectionNumber) {
            Level2Fragment fragment = new Level2Fragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_level2, container, false);
            Button button = (Button) rootView.findViewById(R.id.btn_do_level2);
            Button buttonAns = (Button)rootView.findViewById(R.id.btn_check_level2);

            SharedPreferences pref = getActivity().getSharedPreferences("Usuario", 0);
            String done = pref.getString("nivelDosHecho",null);

            if(done.equals("true")){
                button.setEnabled(false);
                buttonAns.setEnabled(true);
            }else{
                buttonAns.setEnabled(false);
            }

            buttonAns.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Context context;
                    context = getActivity();
                    Intent intent = new Intent(context, TotalLevel2Activity.class);
                    startActivity(intent);
                }
            });

            button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Context context;
                    context = getActivity();
                    Intent intent = new Intent(context, Level2Activity.class);
                    startActivity(intent);
                }
            });
            return rootView;
        }
    }

    public static class Level3Fragment extends android.support.v4.app.Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public Level3Fragment() {
        }

        public static Level3Fragment newInstance(int sectionNumber) {
            Level3Fragment fragment = new Level3Fragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_level3, container, false);
            Button button = (Button) rootView.findViewById(R.id.btn_do_level3);
            Button buttonAns = (Button)rootView.findViewById(R.id.btn_check_level3);

            SharedPreferences pref = getActivity().getSharedPreferences("Usuario", 0);
            String done = pref.getString("nivelTresHecho",null);

            if(done.equals("true")){
                button.setEnabled(false);
                buttonAns.setEnabled(true);
            }else{
                buttonAns.setEnabled(false);
            }

            buttonAns.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Context context;
                    context = getActivity();
                    Intent intent = new Intent(context, TotalLevel3Activity.class);
                    startActivity(intent);
                }
            });
            button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Context context;
                    context = getActivity();
                    Intent intent = new Intent(context, Level3Activity.class);
                    startActivity(intent);
                }
            });
            return rootView;
        }
    }

    public static class Level4Fragment extends android.support.v4.app.Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public Level4Fragment() {
        }

        public static Level4Fragment newInstance(int sectionNumber) {
            Level4Fragment fragment = new Level4Fragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_level4, container, false);
            Button button = (Button) rootView.findViewById(R.id.btn_do_level4);
            Button buttonAns = (Button)rootView.findViewById(R.id.btn_check_level4);

            SharedPreferences pref = getActivity().getSharedPreferences("Usuario", 0);
            String done = pref.getString("nivelCuatroHecho",null);

            if(done.equals("true")){
                button.setEnabled(false);
                buttonAns.setEnabled(true);
            }else{
                buttonAns.setEnabled(false);
            }

            buttonAns.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Context context;
                    context = getActivity();
                    Intent intent = new Intent(context, TotalLevel4Activity.class);
                    startActivity(intent);
                }
            });
            button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Context context;
                    context = getActivity();
                    Intent intent = new Intent(context, Level4Activity.class);
                    startActivity(intent);
                }
            });
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            if(position == 1) {
                return Level2Fragment.newInstance(position + 1);
            }else if(position == 2){
                return Level3Fragment.newInstance(position + 1);
            }else if(position == 3){
                return Level4Fragment.newInstance(position + 1);
            }else{
                return Level1Fragment.newInstance(position+1);
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
            }
            return null;
        }
    }

}
