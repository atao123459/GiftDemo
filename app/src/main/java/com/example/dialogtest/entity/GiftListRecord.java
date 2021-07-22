package com.example.dialogtest.entity;

import java.util.List;

public class GiftListRecord {
    private int id;
    private String background;
    private List<Gift_list> gift_list;
    private int is_lighted;
    private String name;
    private int num;
    private String origin_background;

    public String getBackround() {
        return background;
    }

    public void setBackround(String backround) {
        this.background = backround;
    }

    public List<Gift_list> getGift_list() {
        return gift_list;
    }

    public void setGift_list(List<Gift_list> gift_list) {
        this.gift_list = gift_list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_lighted() {
        return is_lighted;
    }

    public void setIs_lighted(int is_lighted) {
        this.is_lighted = is_lighted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getOrigin_background() {
        return origin_background;
    }

    public void setOrigin_background(String origin_background) {
        this.origin_background = origin_background;
    }
}
