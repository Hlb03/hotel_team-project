package org.main.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.main.service.dto.LocationDTO;

import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Location implements Serializable {

    @Id
    @SequenceGenerator(name = "location_id_seq", sequenceName = "location_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "location_id_seq", strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;

//    @JsonIgnore
    @OneToMany(mappedBy = "location")
    private List<Hotel> hotels;

    @Override
    public String toString() {
        return String.format("Location with id %d and name %s", id, name);
    }
}
