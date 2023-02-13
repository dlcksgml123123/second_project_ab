package com.team5.household.expense.api;

import java.util.Map;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.household.expense.repository.PaymentInfoRepository;

@RestController
@Tag(name = "결제 API", description = "결제수단 추가 및 사용")
@RequestMapping("/payment")
public class PaymentAPIController {
    @Autowired PaymentInfoRepository pRepo;
    
    
}
