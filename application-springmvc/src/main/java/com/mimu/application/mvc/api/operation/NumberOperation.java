package com.mimu.application.mvc.api.operation;


import com.mimu.application.mvc.api.result.NumberSeedResult;

public interface NumberOperation {

    Integer getOperation();

    String getOperationDesc();

    Integer operate(NumberSeedResult first, NumberSeedResult second);

}
