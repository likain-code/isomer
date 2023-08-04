package com.isomer.common.enums;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/28 09:20
 */
public enum Status {
    SUCCEED(1000, "Succeed"),

    ILLEGAL_ORIGIN_EXCEPTION(1001, "Illegal request origin"),
    ILLEGAL_PARAM_EXCEPTION(1002, "Illegal param"),
    DEVICE_UNREGISTER(1003, "Device unregister"),
    SECRET_NOT_MATCH(1004, "Secret not match"),
    REPEATABLE_ENABLE(1005, "Device already enabled"),

    SERVER_FAILED(2000, "Operation failed, please retry later")
    ;

    private final Integer code;
    private final String detail;

    Status(Integer code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    public Integer getCode() {
        return code;
    }

    public String getDetail() {
        return detail;
    }
}
