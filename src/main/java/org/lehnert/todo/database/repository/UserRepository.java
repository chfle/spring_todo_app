package org.lehnert.todo.database.repository;

import org.lehnert.todo.database.tables.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Users repository
 */
public interface UserRepository extends CrudRepository<Users, Long> {
    /**
     * Find user by username or email
     * @param username username
     * @param email email
     * @return the user
     */
    @Query(value = "select * from users where username = :username or email = :email", nativeQuery = true)
    Optional<Users> findUserByUsernameOrEmail(@Param("username") String username, @Param("email") String email);

    @Query(value = "select * from users where username = :username", nativeQuery = true)
    Optional<Users> findUserByUsername(@Param("username") String username);
}
