package org.lehnert.todo.database.compositeKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


/*
 *  Composite key for M:N Relation between Users and Lists with additional attributes
 */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UserListId implements Serializable {
    @Column(name = "user_id")
    @Setter
    @Getter
    private Long userId;

    @Column(name = "list_id")
    @Setter
    @Getter
    private Long listId;
}
