package com.hcy308.transaction.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "wish")
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id = null;

    @Column(name = "wipe_id")
    private Long wipeId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "wish_date")
    private Timestamp wishDate;

    @Column(name = "sign")
    private String sign;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWipeId() {
        return wipeId;
    }

    public void setWipeId(Long wipeId) {
        this.wipeId = wipeId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Timestamp getWishDate() {
        return wishDate;
    }

    public void setWishDate(Timestamp wishDate) {
        this.wishDate = wishDate;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
