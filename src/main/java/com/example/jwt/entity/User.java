package com.example.jwt.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "foydalanuvchi",uniqueConstraints = @UniqueConstraint(columnNames = {"username","password"}))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

}
