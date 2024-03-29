package org.lehnert.todo.database.tables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lehnert.todo.database.enums.AccessTypes;

import java.util.List;

/**
 * List Table for saving todos
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Lists {
    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false)
    private Long owner;

    @ManyToMany(mappedBy = "lists")
    @Setter
    @Getter
    private List<Users> users;

    @Setter
    @Getter
    @Column(nullable = false)
    private String name;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccessTypes accessType;

    @OneToMany(mappedBy = "lists")
    @Setter
    @Getter
    private List<Todo> todos;
}
