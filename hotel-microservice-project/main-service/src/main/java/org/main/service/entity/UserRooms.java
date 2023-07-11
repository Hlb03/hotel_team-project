package org.main.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_has_room")
public class UserRooms {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRooms userRooms = (UserRooms) o;
        return id == userRooms.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
