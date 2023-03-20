package org.main.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user_response")
public class UserResponse implements Serializable {


    @Id
    @SequenceGenerator(name = "user_response_id_seq", sequenceName = "user_response_id_seq")
    @GeneratedValue(generator = "user_response_id_seq", strategy = GenerationType.SEQUENCE)
    private int id;

    private String comment;

    @Column(name = "date_time")
    private Timestamp dateTime;

    private double rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
