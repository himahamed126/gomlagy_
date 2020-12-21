package com.onoo.gomlgy.models;

import java.util.List;

public class collectionmodel {
    List<DAtanum>data;

    public List<DAtanum> getData() {
        return data;
    }
    public void setData(List<DAtanum> data) {
        this.data = data;
    }

    public collectionmodel(List<DAtanum> data) {
        this.data = data;
    }
}
