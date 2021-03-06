package com.mangokiwi.repository;

import com.mangokiwi.model.Token;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by zhenfeng on 5/3/17.
 */
@Repository
public interface TokenRepository extends CrudRepository<Token, Long>{
    Token findByAccessTokenAndVendor(String accessToken, String vendor);

    void deleteById(Long id);

    Token findById(Long id);

    @Query("SELECT id from Token WHERE user_id = :userId")
    Long findTokenIdByUserId(@Param("userId") Long id);

    @Query("UPDATE Token SET accessToken = :accessToken WHERE id = :id")
    int updateAccessTokenById(@Param("id") Long id, @Param("accessToken") String accessToken);



}
