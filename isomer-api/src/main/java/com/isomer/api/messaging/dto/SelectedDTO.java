package com.isomer.api.messaging.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/4 10:42
 */
@Data
public class SelectedDTO implements Serializable {
    private Long id;
    private Integer tag;
}
