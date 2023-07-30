package com.isomer.common.pojo;

import com.isomer.common.enums.Status;

import java.io.Serializable;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/27 23:36
 */
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = 4233425456213289789L;

    private Integer code;
    private String detail;
    private T entity;

    private ApiResult() {
    }

    private ApiResult(Status status) {
        this(status, null);
    }

    private ApiResult(Status status, T entity) {
        this.code = status.getCode();
        this.detail = status.getDetail();
        this.entity = entity;
    }

    public Integer getCode() {
        return code;
    }

    public String getDetail() {
        return detail;
    }

    public T getEntity() {
        return entity;
    }

    public static ApiResult succeed() {
        return new ApiResult(Status.SUCCEED);
    }

    public static <E> ApiResult<E> succeed(E entity) {
        return new ApiResult<E>(Status.SUCCEED, entity);
    }

    public static ApiResult failed(Status status) {
        return new ApiResult(status);
    }

    public static <E> ApiResult<E> failed(Status status, E entity) {
        return new ApiResult<E>(status, entity);
    }
}
