package com.example.dialogtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dialogtest.adapter.MyAdapter;
import com.example.dialogtest.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
//    MyDialogFragment dialog;
//    private List<Item> data = new ArrayList<Item>();
//    private RecyclerView recyclerView;
//    private LinearLayoutManager layoutManager;
//    private MyAdapter adapter;
    private Button button;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        dialog = new MyDialogFragment();

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        MyDialogFragment myDialogFragment = new MyDialogFragment();
//        Bundle args = new Bundle();
//        args.putString("first","");
//        args.putString("second","");
//        args.putString("from","one");
//        myDialogFragment.setArguments(args);
        myDialogFragment.show(getSupportFragmentManager(),"");
    }
}
