package com.mimu.application.mvc.api.operation;

import com.mimu.application.mvc.api.result.NumberSeedResult;
import org.apache.commons.lang3.math.NumberUtils;

public class NumberAddOperation implements NumberOperation {

    @Override
    public Integer getOperation() {
        return NumberUtils.INTEGER_ONE;
    }

    @Override
    public String getOperationDesc() {
        return "加法";
    }

    @Override
    public Integer operate(NumberSeedResult first, NumberSeedResult second) {
        return first.getNumber() + second.getNumber();
    }
}
