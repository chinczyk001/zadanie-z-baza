package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Zwierzeta")
public class Zwierze {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String gatunek;

    @ColumnInfo(name = "srodowisko_zycia")
    public String srodowiskoZycia;

    public boolean czyRoslinozerne;

    public int sredniaZycia;

    public Zwierze(String gatunek, String srodowiskoZycia, boolean czyRoslinozerne, int sredniaZycia) {
        this.gatunek = gatunek;
        this.srodowiskoZycia = srodowiskoZycia;
        this.czyRoslinozerne = czyRoslinozerne;
        this.sredniaZycia = sredniaZycia;
    }

    public Zwierze() {
    }

    @Override
    public String toString() {
        return
                "gatunek :" + gatunek + "\n" +
                "srodowiskoZycia: " + srodowiskoZycia ;
    }
}



