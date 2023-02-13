package com.team5.household.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.household.repository.PaymentInfoRepository;

@RestController
@RequestMapping("/payment")
public class PaymentAPIController {
    @Autowired PaymentInfoRepository pRepo;
    
    
}
