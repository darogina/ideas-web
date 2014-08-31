package com.github.darogina.ideas.model.api.v1;

import com.github.darogina.ideas.model.api.ApiModel;

import java.io.Serializable;

public class HealthCheck implements ApiModel, Serializable {

    private static final long serialVersionUID = -2821558224561626388L;

    private boolean status = true;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String getApiVersion() {
        return BaseModel.API_VERSION;
    }
}
