package com.example.dialogtest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dialogtest.adapter.MyAdapter;
import com.example.dialogtest.entity.Gift;
import com.example.dialogtest.entity.GiftListRecord;
import com.example.dialogtest.entity.GiftSuit;
import com.example.dialogtest.entity.Gift_list;
import com.example.dialogtest.entity.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GiftSuitFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    private int item;
    private DialogFragment dialogFragment;

//    private List<Item> data = new ArrayList<Item>();
    private List<GiftListRecord> giftListRecords;
    private GiftSuitAdapter adapter;

    //获取fragment,给fragment设置各种属性之后返回
    public static final GiftSuitFragment newInstance(DialogFragment dialogFragment, String message,
                                                     int item) {
        GiftSuitFragment fragment = new GiftSuitFragment();
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

//        View view = inflater.inflate(R.layout.gift_suit, container, false);

        return inflater.inflate(R.layout.gift_suit, container, false);
    }


    //获取和设置控件,初始化数据,在onCreateView执行完成之后执行
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_gift_suit);

        giftListRecords = init(item);


        LinearLayoutManager  layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        adapter = new GiftSuitAdapter();

        adapter.setGiftSuitList(giftListRecords);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

//        adapter.setGiftSuitList();
    }

    //礼物套装adapter
    class GiftSuitAdapter extends RecyclerView.Adapter<GiftSuitAdapter.ViewHolder> {
        private List<GiftListRecord> giftSuitList;

        public void setGiftSuitList(List<GiftListRecord> giftSuitList) {
            this.giftSuitList = giftListRecords;
            notifyDataSetChanged();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            TextView count;
            TextView suit;
            RecyclerView gift_suit;
            ConstraintLayout gift_suit_layout;
            ImageView bg;

            public ViewHolder(@NonNull @NotNull View itemView) {
                super(itemView);
//                name = itemView.findViewById(R.id.tv_suit_name);
//                count = itemView.findViewById(R.id.tv_suit_coount);
//                suit = itemView.findViewById(R.id.tv_suit);
                gift_suit = itemView.findViewById(R.id.recycler_gift_suit);
                gift_suit_layout = itemView.findViewById(R.id.cl_gift_suit_layout);
                bg = itemView.findViewById(R.id.iv_suit_bg);
            }
        }

        @NonNull
        @NotNull
        @Override
        public GiftSuitAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.gift_suit, parent, false);
            return new GiftSuitAdapter.ViewHolder(inflater);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
            GiftListRecord giftSuit = giftSuitList.get(position);
            if (giftSuit.getGift_list() != null ) {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                holder.gift_suit.setLayoutManager(layoutManager);
                Glide.with(getContext()).load(giftSuit.getOrigin_background()).into(holder.bg);
                GiftSuitItemAdapter giftSuitItemAdapter = new GiftSuitItemAdapter(giftSuit.getGift_list());
                holder.gift_suit.setAdapter(giftSuitItemAdapter);
            }
        }


        @Override
        public int getItemCount() {
            return giftSuitList.size();
        }
    }


    //套装内单个礼物适配器
    class GiftSuitItemAdapter extends RecyclerView.Adapter<GiftSuitItemAdapter.ViewHolder> {
        private List<Gift_list> giftList;

        public GiftSuitItemAdapter(List<Gift_list> giftList) {
            this.giftList = giftList;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            ImageView img;
            TextView count;
            TextView corner;

            public ViewHolder(@NonNull @NotNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.tv_gift_name);
                img = itemView.findViewById(R.id.iv_gift_image);
                count = itemView.findViewById(R.id.tv_gift_count);
                corner = itemView.findViewById(R.id.tv_tag);
            }
        }

        @NonNull
        @NotNull
        @Override
        public GiftSuitItemAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.gift_suit_item, parent, false);
            return new ViewHolder(inflater);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull GiftSuitItemAdapter.ViewHolder holder, int position) {
            Gift_list gift = giftList.get(position);
            holder.name.setText(gift.getName());
            holder.count.setText(String.valueOf(gift.getNum()+"个"));
            Glide.with(getContext()).load(gift.getOrigin_url()).into(holder.img);
            if(gift.getCorner().equals("")){
                holder.corner.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public int getItemCount() {
            return giftList.size();
        }


    }

    private List<GiftListRecord> init(int item) {
        if (item == 1) {
            Gson gson = new Gson();
            String jsonString = "[{\"background\":\"https://mqimg.meequ.cn/guonei_gray.png\",\"gift_list\":[{\"color\":\"#AEAEAE\",\"corner\":\"\",\"is_lighted\":0,\"name\":\"迷你游艇\",\"num\":0,\"origin_url\":\"https://mqimg.meequ.cn/153362138148843\",\"url\":\"https://mqimg.meequ.cn/gq_banner_1@2x.png\"},{\"color\":\"#AEAEAE\",\"corner\":\"\",\"is_lighted\":0,\"name\":\"冰皮月饼\",\"num\":0,\"origin_url\":\"https://mqimg.meequ.cn/153724997125435\",\"url\":\"https://mqimg.meequ.cn/yuyin_gift_dm_tzlw_yght_gray.png\"},{\"color\":\"#AEAEAE\",\"corner\":\"\",\"is_lighted\":0,\"name\":\"年糕\",\"num\":0,\"origin_url\":\"https://mqimg.meequ.cn/niangaogift_dm_v1.png\",\"url\":\"https://mqimg.meequ.cn/yuyin_gift_dm_tzlw_yght_gray.png\"}],\"id\":1,\"is_lighted\":0,\"name\":\"一见钟情\",\"num\":0,\"origin_background\":\"https://mqimg.meequ.cn/lwtz_kuang_guonei.png\"},{\"background\":\"https://mqimg.meequ.cn/下载.jpg\",\"gift_list\":[{\"color\":\"#AEAEAE\",\"corner\":\"\",\"is_lighted\":0,\"name\":\"F1方程式赛车\",\"num\":0,\"origin_url\":\"https://mqimg.meequ.cn/160040962954344\",\"url\":\"https://mqimg.meequ.cn/企业微信截图_20210510144706.png\"}],\"id\":22,\"is_lighted\":0,\"name\":\"mingke\",\"num\":0,\"origin_background\":\"https://mqimg.meequ.cn/企业微信截图_20210510144741.png\"},{\"background\":\"https://mqimg.meequ.cn/企业微信截图_20210510144741.png\",\"gift_list\":[{\"color\":\"#AEAEAE\",\"corner\":\"\",\"is_lighted\":0,\"name\":\"甜蜜旅行\",\"num\":0,\"origin_url\":\"https://mqimg.meequ.cn/160040351599236\",\"url\":\"https://mqimg.meequ.cn/企业微信截图_20210510144706.png\"}],\"id\":23,\"is_lighted\":0,\"name\":\"mingke2\",\"num\":0,\"origin_background\":\"https://mqimg.meequ.cn/下载.jpg\"},{\"background\":\"https://mqimg.meequ.cn/gq@2x.png\",\"gift_list\":[],\"id\":13,\"is_lighted\":0,\"name\":\"1\",\"num\":0,\"origin_background\":\"https://mqimg.meequ.cn/gq_bann7.png\"},{\"background\":\"https://mqimg.meequ.cn/liwudi.png\",\"gift_list\":[{\"color\":\"#AEAEAE\",\"corner\":\"\",\"is_lighted\":0,\"name\":\"一生所爱\",\"num\":0,\"origin_url\":\"https://mqimg.meequ.cn/160102978043983\",\"url\":\"https://mqimg.meequ.cn/yuyin_gift_dm_227_mfs.png\"}],\"id\":15,\"is_lighted\":0,\"name\":\"婚礼套\",\"num\":0,\"origin_background\":\"https://mqimg.meequ.cn/liwutao.png\"},{\"background\":\"\",\"gift_list\":[{\"color\":\"#AEAEAE\",\"corner\":\"\",\"is_lighted\":0,\"name\":\"热气球\",\"num\":0,\"origin_url\":\"https://mqimg.meequ.cn/yuyin_gift_dm_12_v3.png\",\"url\":\"\"}],\"id\":24,\"is_lighted\":0,\"name\":\"测试\",\"num\":0,\"origin_background\":\"https://mqimg.meequ.cn/十二生肖礼物套.png\"},{\"background\":\"\",\"gift_list\":[{\"color\":\"#AEAEAE\",\"corner\":\"\",\"is_lighted\":0,\"name\":\"F1方程式赛车\",\"num\":0,\"origin_url\":\"https://mqimg.meequ.cn/160040962954344\",\"url\":\"\"}],\"id\":25,\"is_lighted\":0,\"name\":\"测试2\",\"num\":0,\"origin_background\":\"https://mqimg.meequ.cn/十二生肖礼物套副本.png礼物套亮的.png\"},{\"background\":\"https://mqimg.meequ.cn/lwtz_kuang_guoji_gray.png\",\"gift_list\":[{\"color\":\"#AEAEAE\",\"corner\":\"\",\"is_lighted\":0,\"name\":\"家有矿\",\"num\":0,\"origin_url\":\"https://mqimg.meequ.cn/160310563381975\",\"url\":\"https://mqimg.meequ.cn/hjkc_gray.png\"},{\"color\":\"#AEAEAE\",\"corner\":\"\",\"is_lighted\":0,\"name\":\"浪漫欧洲\",\"num\":0,\"origin_url\":\"https://mqimg.meequ.cn/160310581035594\",\"url\":\"https://mqimg.meequ.cn/lmoz_gray.png\"},{\"color\":\"#AEAEAE\",\"corner\":\"\",\"is_lighted\":0,\"name\":\"梦幻海洋\",\"num\":0,\"origin_url\":\"https://mqimg.meequ.cn/160310585631503\",\"url\":\"https://mqimg.meequ.cn/mhhy_gray.png\"}],\"id\":17,\"is_lighted\":0,\"name\":\"国际旅行\",\"num\":0,\"origin_background\":\"https://mqimg.meequ.cn/lwtz_kuang_guoji.png\"},{\"background\":\"https://mqimg.meequ.cn/lwtz_kuang_xinxi_gray.png\",\"gift_list\":[{\"color\":\"#AEAEAE\",\"corner\":\"\",\"is_lighted\":0,\"name\":\"星辰漫游\",\"num\":0,\"origin_url\":\"https://mqimg.meequ.cn/160333227846224\",\"url\":\"https://mqimg.meequ.cn/yuyin_gift_dm_tzlw_xj_tyx_gray.p\"},{\"color\":\"#AEAEAE\",\"corner\":\"\",\"is_lighted\":0,\"name\":\"银河穿梭\",\"num\":0,\"origin_url\":\"https://mqimg.meequ.cn/160333234418624\",\"url\":\"https://mqimg.meequ.cn/yuyin_gift_dm_tzlw_xj_yhx_gray.p\"},{\"color\":\"#AEAEAE\",\"corner\":\"\",\"is_lighted\":0,\"name\":\"奇异空间\",\"num\":0,\"origin_url\":\"https://mqimg.meequ.cn/160333240341351\",\"url\":\"https://mqimg.meequ.cn/yuyin_gift_dm_tzlw_xj_cxx_gray.p\"}],\"id\":18,\"is_lighted\":0,\"name\":\"星际旅行\",\"num\":0,\"origin_background\":\"https://mqimg.meequ.cn/lwtz_kuang_xinxi.png\"},{\"background\":\"https://mqimg.meequ.cn/ (2).png\",\"gift_list\":[{\"color\":\"fd3f51\",\"corner\":\"特效\",\"is_lighted\":1,\"name\":\"金钱树\",\"num\":1,\"origin_url\":\"https://mqimg.meequ.cn/161154616972212\",\"url\":\"https://mqimg.meequ.cn/161154616972212\"},{\"color\":\"#AEAEAE\",\"corner\":\"\",\"is_lighted\":0,\"name\":\"财神爷驾到\",\"num\":0,\"origin_url\":\"https://mqimg.meequ.cn/161190436799149\",\"url\":\"https://mqimg.meequ.cn/2345_image_file_copy_9.jpg\"},{\"color\":\"fd3f51\",\"corner\":\"特效\",\"is_lighted\":1,\"name\":\"招财进宝\",\"num\":4,\"origin_url\":\"https://mqimg.meequ.cn/157663750696683\",\"url\":\"https://mqimg.meequ.cn/157663750696683\"}],\"id\":21,\"is_lighted\":0,\"name\":\"财神礼物套装\",\"num\":0,\"origin_background\":\"https://mqimg.meequ.cn/kuang (2).png\"}]";
            List<GiftListRecord> giftListRecords = gson.fromJson(jsonString,new TypeToken<List<GiftListRecord>>(){}.getType());
            return giftListRecords;
        }
        return null;
    }
}
