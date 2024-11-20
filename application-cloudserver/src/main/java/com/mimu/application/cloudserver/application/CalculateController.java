package com.mimu.application.cloudserver.application;


import com.alibaba.fastjson.JSONObject;
import com.mimu.application.cloudserver.api.CalculateParam;
import com.mimu.application.cloudserver.api.CalculateResult;
import com.mimu.application.cloudserver.api.RpcResult;
import com.mimu.application.cloudserver.api.RpcResultUtil;
import com.sohu.mrd.framework.server.transport.http.annotation.CloudController;
import com.sohu.mrd.framework.server.transport.http.annotation.CloudRequestMapping;
import com.sohu.mrd.framework.server.transport.http.dto.SimpleHttpRequest;
import com.sohu.mrd.framework.server.transport.http.dto.SimpleHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

@CloudController(value = "api")
public class CalculateController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculateController.class);

    @CloudRequestMapping(method = "/first/num/add")
    public String getMethod(SimpleHttpRequest request, SimpleHttpResponse response) {
        CalculateParam calculateParam = build(request, response);
        LOGGER.info("get param:{}", JSONObject.toJSONString(calculateParam));
        CalculateResult calculateResult = new CalculateResult(calculateParam.getFirst() + calculateParam.getSecond(),
                calculateParam.getDescription());
        RpcResult<CalculateResult> success = RpcResultUtil.success(calculateResult);
        return JSONObject.toJSONString(success);
    }


    @CloudRequestMapping(method = "/first/num/multi")
    public String postMethod(SimpleHttpRequest request, SimpleHttpResponse response) {
        Set<String> keySet = request.getParams().keySet();
        String param = keySet.iterator().next();
        CalculateParam calculateParam = JSONObject.parseObject(param, CalculateParam.class);
        LOGGER.info("post param:{}", JSONObject.toJSONString(calculateParam));
        CalculateResult calculateResult = new CalculateResult(calculateParam.getFirst() * calculateParam.getSecond(),
                calculateParam.getDescription());
        RpcResult<CalculateResult> success = RpcResultUtil.success(calculateResult);
        return JSONObject.toJSONString(success);
    }

    private CalculateParam build(SimpleHttpRequest request, SimpleHttpResponse response) {
        CalculateParam addParam = new CalculateParam();
        addParam.setFirst(request.getInt("first"));
        addParam.setSecond(request.getInt("second"));
        addParam.setDescription(request.getString("description"));
        return addParam;
    }

}
