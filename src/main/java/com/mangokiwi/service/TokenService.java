package com.mangokiwi.service;

import com.mangokiwi.model.Token;
import com.mangokiwi.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;


/**
 * Created by zhenfeng on 5/4/17.
 */
@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public Token findByAccessToken(String accessToken){
        Token token = tokenRepository.findByAccessToken(accessToken);
        if(token == null)
            throw new EntityNotFoundException(" Token can not be found");
        return token;
    }

    public Token findById(Long id){
        Token token =  tokenRepository.findById(id);
        if(id == null)
            throw new EntityNotFoundException(" Token can not be found");
        return token;
    }

    public Token findByUserId(Long userId){
        Long id = tokenRepository.findByUserId(userId);
        if(id == null)
            throw new EntityNotFoundException(" Token can not be found");
        return tokenRepository.findById(id);
    }

    // when using update token's id must be existing in database
    public Token update(Token token){
        return tokenRepository.save(token);
    }

    // when using add token's id should be null
    public Token add(Token token ){
        return tokenRepository.save(token);
    }

    public void deleteById(Long id){
        tokenRepository.deleteById(id);
    }



    public boolean checkTokenExpired(Token token){
        return token.getExpireAt().before(new Date(System.currentTimeMillis()));
    }


}
