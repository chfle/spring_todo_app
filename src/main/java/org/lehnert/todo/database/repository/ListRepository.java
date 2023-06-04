package org.lehnert.todo.database.repository;

import org.lehnert.todo.database.tables.Lists;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface ListRepository extends CrudRepository<Lists, Long> {
    @Query(value = "select * from lists where owner = :userId or lists.id in (select list_id from user_list where user_id = :userId)", nativeQuery = true)
    Iterable<Lists> findAllListsRelatedToMe(@Param("userId") Long userId);
}
