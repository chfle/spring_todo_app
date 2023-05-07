package org.lehnert.todo.database.repository;

import org.lehnert.todo.database.tables.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * The UserRepository interface provides methods to interact with the database table 'users'
 */
public interface UserRepository extends CrudRepository<Users, Long> {
    /**
     * Finds a user by username or email.
     *
     * @param username the username of the user to find
     * @param email the email address of the user to find
     * @return an Optional containing the Users object if found, or an empty Optional otherwise
     */
    @Query(value = "select * from users where username = :username or email = :email", nativeQuery = true)
    Optional<Users> findUserByUsernameOrEmail(@Param("username") String username, @Param("email") String email);

    /**
     * Finds a user by username.
     *
     * @param username the username of the user to find
     * @return an Optional containing the Users object if found, or an empty Optional otherwise
     */
    @Query(value = "select * from users where username = :username", nativeQuery = true)
    Optional<Users> findUserByUsername(@Param("username") String username);

    /**
     * Finds a user by email.
     *
     * @param email the email address of the user to find
     * @return an Optional containing the Users object if found, or an empty Optional otherwise
     */
    @Query(value = "select * from users where email = :email", nativeQuery = true)
    Optional<Users> findUserByEmail(@Param("email") String email);
}
