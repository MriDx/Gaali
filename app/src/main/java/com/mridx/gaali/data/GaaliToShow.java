package com.mridx.gaali.data;

import java.util.ArrayList;

public class GaaliToShow {

    private String gaali, desc, creator;

    public GaaliToShow(String gaali, String desc, String creator) {
        this.gaali = gaali;
        this.desc = desc;
        this.creator = creator;
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
}
