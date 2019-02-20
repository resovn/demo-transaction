package com.hcy308.transaction.input;

public class WishInputDto {

    private long wipeId;
    private String subject;

    public long getWipeId() {
        return wipeId;
    }

    public void setWipeId(long wipeId) {
        this.wipeId = wipeId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
