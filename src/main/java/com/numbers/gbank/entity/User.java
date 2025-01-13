package com.numbers.gbank.entity;

import com.numbers.gbank.enums.ROLE;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User extends AuditBaseEntity  {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int age;

    private ROLE role;

    @Column(unique = true, nullable = false)
    private String email;

}
