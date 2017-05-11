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
	Teacher findById(Long id);

	@Query("SELECT id FROM Teacher WHERE user_id = :userId")
	Long findByUserId(@Param("userId") Long id);

	@Query("UPDATE Teacher SET WHERE user_id = :id")
	int updateTeacherByUserId(@Param("id") Long id);
}