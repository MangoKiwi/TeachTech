package com.mangokiwi.repository;

import com.mangokiwi.model.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhenfeng on 5/10/17.
 */
@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {

    Tag findById(Long id);

    @Query("SELECT '*' FROM Tag WHERE user_id = :user_id")
    List<Tag> findByUserId(@Param("user_id") Long userId);

    void deleteById(Long id);

}
