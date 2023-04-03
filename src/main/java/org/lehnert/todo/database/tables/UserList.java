package org.lehnert.todo.database.tables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lehnert.todo.database.compositeKeys.UserListId;
import org.lehnert.todo.database.enums.UserTypes;

import java.util.List;

/**
 * M:N Relation between users and lists to support additional attributes
 */
@Entity
@Table(name = "user_list")
@AllArgsConstructor
@NoArgsConstructor
public class UserList {
    @EmbeddedId
    @Setter
    @Getter
    private UserListId id;

    @ManyToOne
    @Setter
    @Getter
    @MapsId("userId")
    private Users user;

    @ManyToOne
    @Setter
    @Getter
    @MapsId("listId")
    private Lists list;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Setter
    @Getter
    private UserTypes type;
}