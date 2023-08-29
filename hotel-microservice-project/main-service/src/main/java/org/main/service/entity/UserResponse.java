package org.main.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_response")
public class UserResponse {


    @Id
    @SequenceGenerator(name = "user_response_id_seq", sequenceName = "user_response_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "user_response_id_seq", strategy = GenerationType.SEQUENCE)
    private int id;

    private String comment;

    @Column(name = "date_time")
    private Timestamp dateTime;

    @Column(columnDefinition = "NUMERIC(2,1)")
    private double rate;

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
        UserResponse that = (UserResponse) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
