package com.github.darogina.ideas.controller.api.v1;

import com.github.darogina.ideas.model.api.v1.HealthCheck;
import com.mangofactory.swagger.annotations.ApiIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.github.darogina.ideas.controller.RestController.API_REQUEST_MAPPING;

@RestController
@ApiIgnore
@RequestMapping(API_REQUEST_MAPPING + "/v1/healthCheck")
public class HealthCheckAPIv1Controller {

    private static final Logger log = LoggerFactory.getLogger(HealthCheckAPIv1Controller.class);

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    HealthCheck healthCheck() {
        return new HealthCheck();
    }
}
