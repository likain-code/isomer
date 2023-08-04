package com.isomer.api.messaging.service;

import com.isomer.api.messaging.dto.SelectedDTO;
import com.isomer.common.pojo.ApiResult;

import java.util.List;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/3 14:24
 */
public interface IDeviceService {

    ApiResult<?> enableDevice(Long id, String secret);

    ApiResult<List<SelectedDTO>> fetchSelected(Boolean enable);
}
