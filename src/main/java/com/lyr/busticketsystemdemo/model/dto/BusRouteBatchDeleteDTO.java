package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 车次信息批量删除DTO
 *
 * @author yunruili
 * @date 2025/03/28/18:44
 **/
@Data
public class BusRouteBatchDeleteDTO {
    /**
     * 车次id列表
     */
    private List<String> ids;
}
