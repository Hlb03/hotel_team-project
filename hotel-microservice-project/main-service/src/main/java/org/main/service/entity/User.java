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
@Table(name = "user", schema = "public")
public class User implements Serializable {
    @Id
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String nickname;

    private String login;
    private String password;
    private BigDecimal balance;
    private Integer age;
    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_rolename")
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private AccountStatus status;

    @OneToMany(mappedBy = "user")
    private List<UserResponse> responses;

    @OneToMany(mappedBy = "user")
    private List<UserRooms> userRooms;
}
