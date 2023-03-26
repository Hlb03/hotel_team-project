package org.main.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_has_room")
public class UserRooms implements Serializable {
    @Id
    @SequenceGenerator(name = "user_has_room_id_seq", sequenceName = "user_has_room_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "user_has_room_id_seq", strategy = GenerationType.SEQUENCE)
    private int id;

    private Date startRent;

    private Date endRent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
