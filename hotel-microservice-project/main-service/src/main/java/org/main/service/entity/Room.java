package org.main.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Room {
    @Id
    @SequenceGenerator(name = "room_id_seq", sequenceName = "room_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "room_id_seq", strategy = GenerationType.SEQUENCE)
    private int id;

    private BigDecimal price;

    @Column(name = "brief_description")
    private String briefDescription;

    private String description;

    @Column(name = "person_amount")
    private int personAmount;

    @Column(name = "img_reference")
    private String imageReference;

    @Column(name = "total_rate", columnDefinition = "NUMERIC(2,1)")
    private Double totalRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room")
    private List<UserResponse> responses;

    @OneToMany(mappedBy = "room")
    private List<UserRooms> userRooms;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
