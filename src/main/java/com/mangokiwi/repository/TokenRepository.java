package com.mangokiwi.repository;

import com.mangokiwi.model.Token;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by zhenfeng on 5/3/17.
 */
public interface TokenRepository extends CrudRepository<Token, String>{
    Token findByAccessToken(String accessToken);

    int deleteById(Long id);

    Token findById(Long id);

    @Query("UPDATE Token SET accessToken = :accessToken WHERE id = :id")
    int updateAccessTokenById(@Param("id") Long id, @Param("accessToken") String accessToken);


}
