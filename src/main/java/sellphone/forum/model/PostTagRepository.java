package sellphone.forum.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import jakarta.transaction.Transactional;

public interface PostTagRepository extends JpaRepository<PostTag, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM PostTag pt WHERE pt.tag.tagId = :tagId")
    void deleteByTagId(@Param("tagId") Integer tagId);
}