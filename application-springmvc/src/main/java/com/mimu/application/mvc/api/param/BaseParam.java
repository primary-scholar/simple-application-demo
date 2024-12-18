package com.mimu.application.mvc.api.param;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BaseParam implements Serializable {
    /**
     * 无关的参数
     */
    private String p1;

}
