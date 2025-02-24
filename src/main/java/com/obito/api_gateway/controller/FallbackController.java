package com.obito.api_gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @GetMapping("/order")
    public ResponseEntity<String> orderFallback(){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("We are facing in problem for Order Service..Reach out to Help Desk");
    }
    @GetMapping("/payment")
    public ResponseEntity<String> paymentFallback(){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("We are facing in problem for Payment Service..Reach out to Help Desk");
    }
    @GetMapping("/user")
    public ResponseEntity<String> userFallback(){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("We are facing in problem for User Service..Reach out to Help Desk");
    }
}
