package org.mmlak.organizer.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor(force = true)
@Data
@AllArgsConstructor
public class User {
    @Id
    private String username;

    @Column
    @ToString.Exclude
    private String password;

    @ElementCollection(targetClass = Role.class)
    @Column
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

}
