package com.mangokiwi.repository;

import com.mangokiwi.model.Teacher;
import com.mangokiwi.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by tangmaolei on 5/9/17.
 */
@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
	@Query("SELECT id from Teacher WHERE user_id = :userId")
	Teacher findById(@Param("userId") Long id);
}
