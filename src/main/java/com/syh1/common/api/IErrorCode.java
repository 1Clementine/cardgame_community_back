package com.syh1.common.api;


public interface IErrorCode {
    /**
     * 错误编码: -1失败;200成功
     *
     * 返回错误码
     *
     * @return 错误编码
     */
    Integer getCode();

    /**
     * 返回错误描述
     *
     * @return 错误描述
     */
    String getMessage();
}
