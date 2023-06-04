package org.lehnert.todo.database.repository;

import org.lehnert.todo.database.tables.Lists;
import org.springframework.data.repository.CrudRepository;

public interface ListRepository extends CrudRepository<Lists, Long> {
}
