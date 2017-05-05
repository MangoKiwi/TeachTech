package com.mangokiwi.service;

import com.mangokiwi.model.Token;
import com.mangokiwi.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhenfeng on 5/4/17.
 */
@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public Token findByAccessToken(String accessToken){
        return tokenRepository.findByAccessToken(accessToken);
    }

    public Token findById(Long id){
        return tokenRepository.findById(id);
    }

    public Token findByUserId(Long id){
        return null;
    }

    public Token findOneByQuery(String query){
        return null;
    }

    public List<Token> findListByQuery(String query){
        return null;
    }


    public Token updateById(Long id, Token token){
        return null;
    }

    public Token add(Token token ){
        return null;
    }

    public int deleteById(Long id){
        return tokenRepository.deleteById(id);
    }



    public boolean checkTokenExpired(Token token){
        return token.getExpireAt().after(new Date(System.currentTimeMillis()));
    }


}
