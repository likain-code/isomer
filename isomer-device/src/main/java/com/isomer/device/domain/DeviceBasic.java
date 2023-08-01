package com.isomer.device.domain;

import com.isomer.pretreatment.generate.annotation.SupportGenerate;
import com.isomer.pretreatment.validate.annotation.SupportValidate;

import java.sql.Timestamp;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/1 10:42
 */
@SupportValidate
@SupportGenerate
public class DeviceBasic {

    private Long id;
    private Integer tag;
    private String name;
    private String key;
    private String remark;
    private Timestamp createTime;
    private String createBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return "DeviceBasic{" +
                "id=" + id +
                ", tag=" + tag +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                '}';
    }
}
