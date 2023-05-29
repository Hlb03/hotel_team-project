package org.main.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO implements Serializable {
    private int id;
    private BigDecimal price;
    private String shortDescription;
    private String longDescription;
    private int amountOfPerson;
    private Object photoRoom;
    private int hotel;
    private Double rate;
    private List<String> comment;
}
