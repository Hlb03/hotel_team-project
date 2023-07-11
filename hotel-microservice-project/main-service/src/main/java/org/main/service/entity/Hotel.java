package org.main.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Hotel {
    @Id
    @SequenceGenerator(sequenceName = "hotel_id_seq", name = "hotel_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "hotel_id_seq", strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;

    @Override
    public String toString() {
        return String.format("Hotel with name %s has id %d", name, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return id == hotel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
