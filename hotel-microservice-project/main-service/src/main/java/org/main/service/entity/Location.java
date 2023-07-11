package org.main.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.main.service.dto.LocationDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Location {

    @Id
    @SequenceGenerator(name = "location_id_seq", sequenceName = "location_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "location_id_seq", strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;

    @OneToMany(mappedBy = "location")
    private List<Hotel> hotels;

    @Override
    public String toString() {
        return String.format("Location with id %d and name %s", id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
