package com.isomer.device.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/3 13:28
 */
@Data
@AllArgsConstructor
public class DeviceRegisterDTO implements Serializable {
    private String upLinkTopic;
    private String downLinkTopic;
    private String secret;
}
