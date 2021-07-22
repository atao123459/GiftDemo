package com.example.dialogtest;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    private ViewPager viewPager;//页卡内容
    private ImageView imageView;// 动画图片
    private TextView textView1,textView2;
    private List<View> views;// Tab页面列表
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private View view1,view2;//各个页卡

    //设置样式
    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE,R.style.DialogFullScreen); //dialog全屏
    }

    //设置底部dialogfragment
    @Override
    public void onStart() {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = 1500;
        window.setAttributes(layoutParams);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //设置弹窗动画
        layoutParams.windowAnimations = R.style.DialogFullScreen;
        super.onStart();
    }

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId) == 0 ? 60 : resources.getDimensionPixelSize(resourceId);
    }

    //创建视图
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.viewpager_item_suit,container);
//        instance = this;
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        List<Fragment> fragments = getFragment();
        AcoesMuscularesAdapter acoesMuscularesAdapter = new AcoesMuscularesAdapter(getChildFragmentManager(),fragments);
        //想viewpager中加入fragment
        viewPager.setAdapter(acoesMuscularesAdapter);

        ImageView bg2 = view.findViewById(R.id.iv_tab_bg2);
        bg2.setVisibility(View.INVISIBLE);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ImageView bg = view.findViewById(R.id.iv_tab_bg);
//                ImageView bg2 = view.findViewById(R.id.iv_tab_bg2);
//                bg2.setVisibility(View.INVISIBLE);
                //参数position是滑动的目标页面，当滑到目标页面后手指抬起时会调用该方法
                    // 设置标题
//                tv_gift
//                tv_gift_suit
                if(position==1) {
                    bg.setVisibility(View.INVISIBLE);
                    bg2.setVisibility(View.VISIBLE);
                }else if(position==0){
                    bg2.setVisibility(View.INVISIBLE);
                    bg.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        this.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        return view;
    }

//    private void InitImageView() {
//        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.select).getWidth();// 获取图片宽度
//        DisplayMetrics dm = new DisplayMetrics();
////        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int screenW = dm.widthPixels;// 获取分辨率宽度
//        offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
//        Matrix matrix = new Matrix();
//        matrix.postTranslate(offset, 0);
//        imageView.setImageMatrix(matrix);// 设置动画初始位置
//    }


    private List<Fragment> getFragment() {
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(MypageFragment.newInstance(this,"",1));
        list.add(GiftSuitFragment.newInstance(this,"",1));
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
