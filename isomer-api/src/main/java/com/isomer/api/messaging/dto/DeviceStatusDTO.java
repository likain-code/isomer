package com.isomer.api.messaging.dto;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/2 15:44
 */
public class DeviceStatusDTO {

    private Long deviceInfoId;
    private Boolean enable;
    private Boolean online;

    public DeviceStatusDTO() {
    }

    public DeviceStatusDTO(Long deviceInfoId, Boolean enable, Boolean online) {
        this.deviceInfoId = deviceInfoId;
        this.enable = enable;
        this.online = online;
    }

    public Long getDeviceInfoId() {
        return deviceInfoId;
    }

    public void setDeviceInfoId(Long deviceInfoId) {
        this.deviceInfoId = deviceInfoId;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "DeviceStatus{" +
                "deviceInfoId=" + deviceInfoId +
                ", enable=" + enable +
                ", online=" + online +
                '}';
    }
}
