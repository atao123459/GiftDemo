package com.example.dialogtest;

import android.app.AlertDialog;
import android.app.AppComponentFactory;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.dialogtest.adapter.MyAdapter;
import com.example.dialogtest.entity.Item;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 负责显示页面的fragment
 *
 * main_activity点击按钮进入到MyDialogFragment，MyDialogFragment中有一个viewpager，viewpager中加入了Fragment，fragment中加入了recyclerview,如果要获取recyclerview就要到MyDialogFragment或者MypageFragment中获取，而不是在main_activity中获取
 */
public class MyDialogFragment  extends DialogFragment {
//    public static MyDialogFragment instance;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.viewpager_item,container);
//        instance = this;
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        List<Fragment> fragments = getFragment();

        AcoesMuscularesAdapter acoesMuscularesAdapter = new AcoesMuscularesAdapter(getChildFragmentManager(),fragments);
        //想viewpager中加入fragment
        viewPager.setAdapter(acoesMuscularesAdapter);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return view;
    }



    private List<Fragment> getFragment() {
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(MypageFragment.newInstance(this,"",1));
        list.add(MypageFragment.newInstance(this,"",2));
        return list;
    }

    class AcoesMuscularesAdapter extends FragmentPagerAdapter{
        private List<Fragment> fragments;

        public AcoesMuscularesAdapter(@NonNull @NotNull FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @NonNull
        @NotNull
        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }
}
