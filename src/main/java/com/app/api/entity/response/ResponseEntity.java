package com.app.api.entity.response;

import java.util.List;

public class ResponseEntity {
    private long id;
    private List<String> msg;

    public ResponseEntity(long id, List<String> msg) {
        this.id = id;
        this.msg = msg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getMsg() {
        return msg;
    }

    public void setMsg(List<String> msg) {
        this.msg = msg;
    }
}
