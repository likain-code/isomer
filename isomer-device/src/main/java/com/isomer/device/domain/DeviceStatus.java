package com.isomer.device.domain;

import java.sql.Timestamp;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/2 15:44
 */
public class DeviceStatus {

    private Long deviceInfoId;
    private Boolean enable;
    private Boolean online;
    private Timestamp enableTime;
    private Timestamp onlineUpdateTime;

    public Long getDeviceInfoId() {
        return deviceInfoId;
    }

    public DeviceStatus setDeviceInfoId(Long deviceInfoId) {
        this.deviceInfoId = deviceInfoId;
        return this;
    }

    public Boolean getEnable() {
        return enable;
    }

    public DeviceStatus setEnable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public Boolean getOnline() {
        return online;
    }

    public DeviceStatus setOnline(Boolean online) {
        this.online = online;
        return this;
    }

    public Timestamp getEnableTime() {
        return enableTime;
    }

    public DeviceStatus setEnableTime(Timestamp enableTime) {
        this.enableTime = enableTime;
        return this;
    }

    public Timestamp getOnlineUpdateTime() {
        return onlineUpdateTime;
    }

    public DeviceStatus setOnlineUpdateTime(Timestamp onlineUpdateTime) {
        this.onlineUpdateTime = onlineUpdateTime;
        return this;
    }

    @Override
    public String toString() {
        return "DeviceStatus{" +
                "deviceInfoId=" + deviceInfoId +
                ", enable=" + enable +
                ", online=" + online +
                ", enableTime=" + enableTime +
                ", onlineUpdateTime=" + onlineUpdateTime +
                '}';
    }
}
