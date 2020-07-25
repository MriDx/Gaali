package com.mridx.gaali.data;

import java.io.Serializable;

public class Gaali implements Serializable {

    public String gaali, desc;

    public Gaali(String gaali, String desc) {
        this.gaali = gaali;
        this.desc = desc;
    }
}
