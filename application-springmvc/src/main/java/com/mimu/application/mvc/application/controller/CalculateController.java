package com.mimu.application.mvc.application.controller;

import com.alibaba.fastjson.JSONObject;
import com.mimu.application.mvc.api.param.NumberCalculateParam;
import com.mimu.application.mvc.api.param.NumberOperationParam;
import com.mimu.application.mvc.api.result.NumberCalculateResult;
import com.mimu.application.mvc.api.result.NumberSeedResult;
import com.mimu.application.mvc.api.result.RpcResult;
import com.mimu.application.mvc.api.result.RpcResultUtil;
import com.mimu.application.mvc.application.service.NumberOperationHttpService;
import com.mimu.application.mvc.application.utils.NumberOperationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class CalculateController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculateController.class);

    @Autowired
    private NumberOperationHttpService operationHttpService;

    @RequestMapping(value = "/api/num/cal", method = RequestMethod.POST)
    public RpcResult calculateNumber(@RequestBody NumberCalculateParam param) {
        LOGGER.info("get param:{}", JSONObject.toJSONString(param));
        NumberCalculateParam operationParam = NumberOperationBuilder.build(param.getFirst(), param.getSecond(),
                param.getOperation());
        NumberCalculateResult operationResult = operationHttpService.numberOperationPost(operationParam);
        return Objects.isNull(operationResult) ? RpcResultUtil.fail() : RpcResultUtil.success(operationResult);
    }

    @RequestMapping(value = "/api/num/cal/mvc", method = RequestMethod.GET)
    public RpcResult calculateNumberInMvc(NumberOperationParam param) {
        LOGGER.info("get param:{}", JSONObject.toJSONString(param));
        NumberSeedResult first = operationHttpService.numberSeedGet(param);
        NumberSeedResult second = operationHttpService.numberSeedGet(param);
        NumberCalculateParam operationParam = NumberOperationBuilder.build(first, second, param.getOperation());
        NumberCalculateResult operationResult = operationHttpService.numberOperationPostInMvc(operationParam);
        return Objects.isNull(operationResult) ? RpcResultUtil.fail() : RpcResultUtil.success(operationResult);
    }

    @RequestMapping(value = "/api/num/cal/mvc/local", method = RequestMethod.POST)
    public RpcResult calculateNumberInMvcLocal(@RequestBody NumberCalculateParam param) {
        LOGGER.info("get param:{}", JSONObject.toJSONString(param));
        NumberCalculateResult build = NumberOperationBuilder.build(param);
        return  RpcResultUtil.success(build);
    }
}
