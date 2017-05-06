package com.mangokiwi.repository;

import com.mangokiwi.model.Token;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by zhenfeng on 5/3/17.
 */
public interface TokenRepository extends CrudRepository<Token, Long>{
    Token findByAccessTokenAndVendor(String accessToken, String vendor);

    void deleteById(Long id);

    Token findById(Long id);

    @Query("SELECT id from Token WHERE user_id = :userId")
    Long findByUserId(@Param("userId") Long id);

    @Query("UPDATE Token SET accessToken = :accessToken WHERE id = :id")
    int updateAccessTokenById(@Param("id") Long id, @Param("accessToken") String accessToken);



}
