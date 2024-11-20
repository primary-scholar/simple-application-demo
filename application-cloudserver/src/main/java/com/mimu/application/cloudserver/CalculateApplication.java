package com.mimu.application.cloudserver;

import com.sohu.mrd.framework.server.transport.http.SohuCloudHttpServer;

import java.util.Arrays;

public class CalculateApplication {

    public static void main(String[] args) {
        SohuCloudHttpServer server = SohuCloudHttpServer.create().scanPackageList(Arrays.asList("com.mimu.application" +
                ".cloudserver")).port(8082).supportSpring().build();
        server.start();
    }
}
