package com.example.dialogtest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dialogtest.adapter.MyAdapter;
import com.example.dialogtest.entity.Item;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 负责接收和展示数据的fragment
 */
public class MypageFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    private int item;
    private TextView mTvMsg;
//    private Button mBtnCancle;
    private DialogFragment dialogFragment;

    private List<Item> data = new ArrayList<Item>();
    private LinearLayoutManager layoutManager;
    private MyAdapter adapter;

    //获取fragment,给fragment设置各种属性之后返回
    public static final MypageFragment newInstance(DialogFragment dialogFragment, String message,
                                                   int item) {
        MypageFragment fragment = new MypageFragment();
        Bundle bdl = new Bundle(item);
        //用于区分不同的fragment
        fragment.item = item;
        fragment.dialogFragment = dialogFragment;
        bdl.putString(EXTRA_MESSAGE, message);
        fragment.setArguments(bdl);
        return fragment;
    }


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
                             @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                             @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.viewpager_item,container,false);

        Button mBtnCancle = (Button) view.findViewById(R.id.btnCancle);
        mBtnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.dismiss();
            }
        });

        return view;
    }



    //获取和设置控件,初始化数据
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycleview);

        init(item);

        layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new MyAdapter(view.getContext(),data);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        String msg = getArguments().getString(EXTRA_MESSAGE);
//        mTvMsg = (TextView) view.findViewById(R.id.text);
//        mTvMsg.setText(msg);
    }

    private void init(int item) {
        if (item==1){
            for(int i=0;i<2;i++){
                data.add(new Item("苹果",5.5,R.drawable.apple_pic));
                data.add(new Item("香蕉",3.8,R.drawable.banana_pic));
                data.add(new Item("樱桃",10.2,R.drawable.cherry_pic));
                data.add(new Item("葡萄",7.0,R.drawable.grape_pic));
                data.add(new Item("芒果",8.0,R.drawable.mango_pic));
                data.add(new Item("橙子",4.5,R.drawable.orange_pic));
            }
        }else if(item==2){
            for(int i=0;i<1;i++){
                data.add(new Item("苹果",5.5,R.drawable.apple_pic));

            }
        }
        else {
            for(int i=0;i<1;i++){
                data.add(new Item("苹果",5.5,R.drawable.apple_pic));
                data.add(new Item("香蕉",3.8,R.drawable.banana_pic));
                data.add(new Item("樱桃",10.2,R.drawable.cherry_pic));
            }
        }

    }


}
