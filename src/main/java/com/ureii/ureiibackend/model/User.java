package com.ureii.ureiibackend.model;

import com.ureii.ureiibackend.validate.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username is mandatory", groups = {ValidationGroups.Register.class, ValidationGroups.Login.class})
    private String username;
    @Column(nullable = false)
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is mandatory", groups = ValidationGroups.Register.class)
    @Email(message = "Email should be valid", groups = ValidationGroups.Register.class)
    private String email;
    private String phone;
    private String nickname;
    private String role;
    private Date createdAt;
    private Date updatedAt;
}
