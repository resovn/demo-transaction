package com.hcy308.transaction.model;

import javax.persistence.*;

@Entity
@Table(name = "magic_lamp")
@IdClass(MagicLampPk.class)
public class MagicLamp {

    @Id
    @Column(name = "lamp_id", nullable = false)
    private String lampId;

    @Column(name = "name")
    private String name;

    public String getLampId() {
        return lampId;
    }

    public void setLampId(String lampId) {
        this.lampId = lampId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
