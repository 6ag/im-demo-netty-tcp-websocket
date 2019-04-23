package com.kefu.netty.util;

import java.util.UUID;

/**
 * @author feng
 * @date 2019-04-21
 */
public class IDUtil {

    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
