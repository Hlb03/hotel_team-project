package org.main.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Hotel implements Serializable {
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
}
