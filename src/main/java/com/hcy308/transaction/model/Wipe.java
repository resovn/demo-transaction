package com.hcy308.transaction.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "wipe")
public class Wipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id = null;

    @Column(name = "lamp_id", nullable = false)
    private String lampId;

    @Column(name = "wiper", nullable = false)
    private String wiper;

    @Column(name = "used")
    private Integer used;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "wipe_date")
    private Timestamp wipeDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLampId() {
        return lampId;
    }

    public void setLampId(String lampId) {
        this.lampId = lampId;
    }

    public String getWiper() {
        return wiper;
    }

    public void setWiper(String wiper) {
        this.wiper = wiper;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Timestamp getWipeDate() {
        return wipeDate;
    }

    public void setWipeDate(Timestamp wipeDate) {
        this.wipeDate = wipeDate;
    }
}
