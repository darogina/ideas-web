package com.github.darogina.ideas.model.api;

import java.util.Map;

public class ExtendedError extends Error {
    private Map<String, Object> info;

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }
}
