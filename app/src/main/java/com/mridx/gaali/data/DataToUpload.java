package com.mridx.gaali.data;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;

public class DataToUpload implements Serializable {

    public String gaali, desc, creator;
    public FieldValue date;

    public DataToUpload(String gaali, String desc, String creator, FieldValue date) {
        this.gaali = gaali;
        this.desc = desc;
        this.creator = creator;
        this.date = date;
    }

    public String getGaali() {
        return gaali;
    }

    public String getDesc() {
        return desc;
    }

    public String getCreator() {
        return creator;
    }

    public FieldValue getDate() {
        return date;
    }
}

