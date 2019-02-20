package com.hcy308.transaction.model;

import java.io.Serializable;

public class MagicLampPk implements Serializable {

    private String lampId;

    public MagicLampPk() {
    }

    public String getLampId() {
        return lampId;
    }

    public void setLampId(String lampId) {
        this.lampId = lampId;
    }

    public int hashCode() {
        return lampId.hashCode();
    }


    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj == this) {
            return true;
        } else if (!(obj instanceof MagicLampPk)) {
            return false;
        }
        MagicLampPk pk = (MagicLampPk) obj;
        return pk.lampId.equals(lampId);
    }

}
