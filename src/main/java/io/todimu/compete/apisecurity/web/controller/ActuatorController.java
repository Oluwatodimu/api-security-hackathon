package io.todimu.compete.apisecurity.web.controller;

import io.todimu.compete.apisecurity.utils.ResponseConstants;
import io.todimu.compete.apisecurity.web.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/actuator")
public class ActuatorController {

    @GetMapping(value = "/health")
    public ResponseEntity<BaseResponse> health() {
        log.info("checking application health");
        return new ResponseEntity<>(new BaseResponse("ok", ResponseConstants.SUCCESS, false), HttpStatus.OK);
    }
}
