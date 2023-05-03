package org.main.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO implements Serializable {
    private int id;
    private BigDecimal price;
    private String briefDescription;
    private String description;
    private String imageReference;
    private int hotel;
    private Double rate;
}
