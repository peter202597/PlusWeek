package com.sparta.plusweek.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // ORDINAL 로 하면 정수로 저장이 되므로 이후 Role 추가 시 문제 생길 여지 있음
    private UserRoleEnum role;

    private User(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static User createUser(String username, String password) {
        return new User(username, password, UserRoleEnum.USER);
    }
}
