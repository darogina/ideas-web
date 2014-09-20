package com.github.darogina.ideas.model.api.v1;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(parent = BaseModel.class)
public class Book extends BaseModel {

    @ApiModelProperty(required = true)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
