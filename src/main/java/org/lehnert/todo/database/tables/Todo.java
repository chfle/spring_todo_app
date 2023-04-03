package org.lehnert.todo.database.tables;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.lehnert.todo.database.enums.UserTypes;

import java.sql.Date;
import java.util.List;

/**
 * To-do Table
 */
@Entity
public class Todo {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Setter
    @Getter
    @Column(nullable = false)
    private String title;

    /**
     * description
     */
    @Setter
    @Getter
    private String detail;

    @Setter
    @Getter
    Date date;

    @ManyToOne
    @JoinColumn(name = "todo_id", nullable = false)
    @Setter
    @Getter
    private Lists lists;

    @ManyToMany(mappedBy = "todos")
    @Setter
    @Getter
    private List<Users> users;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Setter
    @Getter
    private AccessType type;
}
