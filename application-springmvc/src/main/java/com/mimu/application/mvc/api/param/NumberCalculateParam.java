package com.mimu.application.mvc.api.param;


import com.mimu.application.mvc.api.result.NumberSeedResult;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class NumberCalculateParam extends BaseParam implements Serializable {

    /**
     * 进行的操作
     */
    private Integer operation;
    /**
     *
     */
    private NumberSeedResult first;
    /**
     *
     */
    private NumberSeedResult second;
}
