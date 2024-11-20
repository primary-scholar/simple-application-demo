package com.mimu.application.cloudserver.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class CalculateParam implements Serializable {
    /**
     * 两个数 算术第一个
     */
    private Integer first;
    /**
     * 两个数 算术第二个
     */
    private Integer second;
    /**
     * 描述
     */
    private String description;
}
