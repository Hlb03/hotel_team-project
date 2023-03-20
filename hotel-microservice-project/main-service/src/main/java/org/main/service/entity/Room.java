package org.main.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Room implements Serializable {
    @Id
    @SequenceGenerator(name = "room_id_seq", sequenceName = "room_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "room_id_seq", strategy = GenerationType.SEQUENCE)
    private int id;

    private BigDecimal price;

    private String description;

    @Column(name = "img_reference")
    private String imageReference;

    private double rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room")
    private List<UserResponse> responses;

    @OneToMany(mappedBy = "room")
    private List<UserRooms> userRooms;
}
