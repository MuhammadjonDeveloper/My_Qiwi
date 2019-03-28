package com.example.muhammadjon.myproject.dbase;

import android.arch.persistence.room.Entity;

@Entity
public class Event {
    private Categories[] cateories;
    private Merchants[] merchants;
    private Fields[] fields;
    private Values[] values;
    private Deleted[] deleteds;

    public Categories[] getCateories() {
        return cateories;
    }

    public Merchants[] getMerchants() {
        return merchants;
    }

    public Fields[] getFields() {
        return fields;
    }

    public Values[] getValues() {
        return values;
    }

    public Deleted[] getDeleteds() {
        return deleteds;
    }
}
