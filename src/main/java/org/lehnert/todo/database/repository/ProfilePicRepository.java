package org.lehnert.todo.database.repository;

import jakarta.transaction.Transactional;
import org.lehnert.todo.database.tables.ProfilePic;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfilePicRepository extends CrudRepository<ProfilePic, Long> {
    @Query(value = "select * from profile_pic where users_id = :userId", nativeQuery = true)
    Optional<ProfilePic> findProfilePicByUserId(@Param("userId") Long userid);

    @Transactional
    @Modifying
    @Query(value = "delete from profile_pic where users_id = :userId", nativeQuery = true)
    void deleteByUserId(@Param("userId") Long userId);
}
