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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

@RestController
public class OperationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationController.class);
    private static final Integer waitSeconds = 1000;

    @Autowired
    private ExecutorService executorService;
    @Autowired
    private NumberOperationHttpService operationHttpService;

    @RequestMapping(value = "/api/num/cal/get", method = RequestMethod.GET)
    public RpcResult calculateWithGet(NumberOperationParam param) {
        LOGGER.info("get param:{}", JSONObject.toJSONString(param));
        NumberSeedResult first = operationHttpService.numberSeedGet(param);
        NumberSeedResult second = operationHttpService.numberSeedGet(param);
        NumberCalculateParam operationParam = NumberOperationBuilder.build(first, second, param.getOperation());
        NumberCalculateResult operationResult = operationHttpService.numberOperationPost(operationParam);
        return Objects.isNull(operationResult) ? RpcResultUtil.fail() : RpcResultUtil.success(operationResult);
    }


    @RequestMapping(value = "/api/cal/num/post", method = RequestMethod.POST)
    public RpcResult calculateWithPostBody(@RequestBody NumberOperationParam param) {
        LOGGER.info("post param:{}", JSONObject.toJSONString(param));
        NumberSeedResult first = operationHttpService.numberSeedGet(param);
        NumberSeedResult second = operationHttpService.numberSeedGet(param);
        NumberCalculateParam operationParam = NumberOperationBuilder.build(first, second, param.getOperation());
        NumberCalculateResult operationResult = operationHttpService.numberOperationPost(operationParam);
        return Objects.isNull(operationResult) ? RpcResultUtil.fail() : RpcResultUtil.success(operationResult);
    }


    @RequestMapping(value = "/api/cal/num/post/form", method = RequestMethod.POST)
    public RpcResult calculateWithPostForm(NumberOperationParam param) {
        LOGGER.info("post param:{}", JSONObject.toJSONString(param));
        NumberSeedResult first = operationHttpService.numberSeedGet(param);
        NumberSeedResult second = operationHttpService.numberSeedGet(param);
        NumberCalculateParam operationParam = NumberOperationBuilder.build(first, second, param.getOperation());
        NumberCalculateResult operationResult = operationHttpService.numberOperationPost(operationParam);
        return Objects.isNull(operationResult) ? RpcResultUtil.fail() : RpcResultUtil.success(operationResult);
    }

    @RequestMapping(value = "/api/cal/remote/num/add/thread", method = RequestMethod.GET)
    public RpcResult calculateWithGetThread(NumberOperationParam param) {
        LOGGER.info("get param:{}", JSONObject.toJSONString(param));
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicReference<NumberCalculateResult> atomicReference = new AtomicReference<>();
        Thread thread = new Thread(() -> {
            atomicReference.set(null);
            countDownLatch.countDown();
        });
        thread.start();
        try {
            countDownLatch.await(waitSeconds, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }
        NumberCalculateResult operationResult = atomicReference.get();
        return Objects.isNull(operationResult) ? RpcResultUtil.fail() : RpcResultUtil.success(operationResult);
    }

    @RequestMapping(value = "/api/cal/remote/num/add/execute", method = RequestMethod.GET)
    public RpcResult calculateWithGetExecute(NumberOperationParam param) {
        LOGGER.info("get param:{}", JSONObject.toJSONString(param));
        NumberCalculateResult operationResult = null;
        Future<NumberCalculateResult> submit = executorService.submit(() -> null);
        try {
            operationResult = submit.get(waitSeconds, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        } catch (TimeoutException e) {
        }
        return Objects.isNull(operationResult) ? RpcResultUtil.fail() : RpcResultUtil.success(operationResult);
    }

    @RequestMapping(value = "/api/cal/remote/num/add/completefuture", method = RequestMethod.GET)
    public RpcResult calculateWithGetCompletefuture(NumberOperationParam param) {
        LOGGER.info("get param:{}", JSONObject.toJSONString(param));
        NumberCalculateResult operationResult = null;
        AtomicReference<NumberCalculateResult> atomicReference = new AtomicReference<>();
        CompletableFuture<NumberCalculateResult> completableFuture =
                CompletableFuture.supplyAsync((Supplier<NumberCalculateResult>) () -> null).whenComplete((numberCalculateResult, throwable) -> atomicReference.set(numberCalculateResult));
        try {
            operationResult = completableFuture.get(waitSeconds, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        } catch (TimeoutException e) {
        }
        return Objects.isNull(operationResult) ? RpcResultUtil.fail() : RpcResultUtil.success(operationResult);
    }

}
