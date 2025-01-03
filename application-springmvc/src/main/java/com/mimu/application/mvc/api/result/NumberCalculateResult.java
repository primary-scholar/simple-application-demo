package com.mimu.application.mvc.api.result;

import com.mimu.application.mvc.api.param.BaseParam;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class NumberCalculateResult extends BaseParam implements Serializable {
    /**
     *
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
    /**
     *
     */
    private Integer result;
}
