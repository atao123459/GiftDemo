package com.example.dialogtest;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetDialog;


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

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE,R.style.DialogFullScreen); //dialog全屏
    }

    @Override
    public void onStart() {

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = 1500;
        window.setAttributes(layoutParams);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
       layoutParams.windowAnimations = R.style.DialogFullScreen;
        super.onStart();
    }

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



        this.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);


        return view;
    }

    //使用BottomSheetDialog方式实现底部弹窗
    void showBottomSheetDialog(){
        BottomSheetDialog bottomSheet = new BottomSheetDialog(getContext());//实例化BottomSheetDialog
        bottomSheet.setCancelable(true);//设置点击外部是否可以取消
        bottomSheet.setContentView(R.layout.viewpager_item);//设置对框框中的布局
        bottomSheet.show();//显示弹窗
    }

    private List<Fragment> getFragment() {
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(MypageFragment.newInstance(this,"",1));
        list.add(MypageFragment.newInstance(this,"",2));
        list.add(MypageFragment.newInstance(this,"",3));
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
