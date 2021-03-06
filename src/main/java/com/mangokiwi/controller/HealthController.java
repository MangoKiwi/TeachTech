package com.mangokiwi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhenfeng on 4/24/17.
 */

@RestController
public class HealthController extends BaseController {

    @RequestMapping(value = HEALTH_URL, method = RequestMethod.GET)
    public String healthCheck(){
        return "Connection is good";
    }

}
