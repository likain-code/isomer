package com.isomer.common.enums;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/28 09:20
 */
public enum Status {
    SUCCEED(1000, "Succeed"),
    ILLEGAL_ORIGIN_EXCEPTION(1001, "Illegal request origin")
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
