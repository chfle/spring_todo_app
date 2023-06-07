package org.lehnert.todo.database.repository;

import org.lehnert.todo.database.interfaces.IUsernameAndId;
import org.lehnert.todo.database.tables.Lists;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface ListRepository extends CrudRepository<Lists, Long> {
    @Query(value = "select * from lists where owner = :userId or lists.id in (select list_id from user_list where user_id = :userId)", nativeQuery = true)
    Iterable<Lists> findAllListsRelatedToMe(@Param("userId") Long userId);

    @Query(value = """
    select username, id from users where (id in (select owner from lists where users.id = owner and lists.id = :listId) or
                          id in (select user_id from user_list where users.id = user_id and list_id = :listId))\s
                      and exists(select * from lists where owner = :userId and lists.id = :listId)
    """, nativeQuery = true)
    Iterable<IUsernameAndId> getAllUsernamesByListAndOwner(@Param("listId") Long listId, @Param("userId") Long userId);
}
