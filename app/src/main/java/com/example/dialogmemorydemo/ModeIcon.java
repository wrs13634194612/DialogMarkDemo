package com.example.dialogmemorydemo;


import java.io.Serializable;

public class ModeIcon implements Serializable {
    public String equipmentModelIcon;
    public boolean isCheck;
    public int day;
    public String week;


    public ModeIcon(String equipmentModelIcon, String week, boolean isCheck, int day) {
        this.equipmentModelIcon = equipmentModelIcon;
        this.isCheck = isCheck;
        this.day = day;
        this.week = week;
    }

    public ModeIcon(String equipmentModelIcon, boolean isCheck) {
        this.equipmentModelIcon = equipmentModelIcon;
        this.isCheck = isCheck;
    }

}
