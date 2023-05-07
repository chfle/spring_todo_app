package org.lehnert.todo.database.repository;

import jakarta.transaction.Transactional;
import org.lehnert.todo.database.tables.ProfilePic;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * The ProfilePicRepository interface provides methods to interact with the database table `profile_pic`
 */
public interface ProfilePicRepository extends CrudRepository<ProfilePic, Long> {
    /**
     * Finds a profile picture by user ID.
     *
     * @param userid the ID of the user to find the profile picture for
     *@return an Optional containing the ProfilePic object if found, or an empty Optional otherwise
     */
    @Query(value = "select * from profile_pic where users_id = :userId", nativeQuery = true)
    Optional<ProfilePic> findProfilePicByUserId(@Param("userId") Long userid);

    /**
     * Deletes a profile picture by user ID.
     *
     * @param userId the ID of the user whose profile picture to delete
     */
    @Transactional
    @Modifying
    @Query(value = "delete from profile_pic where users_id = :userId", nativeQuery = true)
    void deleteByUserId(@Param("userId") Long userId);
}
