package com.mangokiwi.repository;

import com.mangokiwi.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zhenfeng on 4/27/17.
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findById(Long id);

}
