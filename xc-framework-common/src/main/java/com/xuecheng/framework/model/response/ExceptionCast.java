package com.xuecheng.framework.model.response;

import com.xuecheng.framework.exception.CustomException;

public class ExceptionCast {
    //使用此静态方法抛出自定义异常
    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
