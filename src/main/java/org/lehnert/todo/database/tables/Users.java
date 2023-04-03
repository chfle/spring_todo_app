package org.lehnert.todo.database.tables;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.util.List;

/**
 * User Table
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users {
    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @Setter
    @Getter
    @JoinTable(name = "user_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "list_id"))
    private List<Lists> lists;

    @Setter
    @Getter
    @Column(nullable = false)
    String username;

    /**
     * @implNote should be hashed
     */
    @Setter
    @Getter
    @Column(nullable = false)
    String password;

    @Setter
    @Getter
    @Column(nullable = false)
    String email;

    @Setter
    @Getter
    @Column(nullable = false)
    Date firstLogin;
}
