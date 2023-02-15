package com.team5.household.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.team5.household.service.PaymenrService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/payment")
@RequiredArgsConstructor
public class PaymentAPIController {
    private final PaymenrService pService;
    
    
}
