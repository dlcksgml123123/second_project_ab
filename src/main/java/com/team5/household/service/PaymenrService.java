package com.team5.household.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.team5.household.entity.PaymentInfoEntity;
import com.team5.household.repository.PaymentInfoRepository;
import com.team5.household.vo.PaymentAddVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymenrService {
    private final PaymentInfoRepository pRepo;

    public Map<String, Object> addPayment(PaymentAddVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        PaymentInfoEntity entity = null;
            entity = pRepo.save(entity);
            resultMap.put("status", true);
            resultMap.put("message", "결제수단이 등록되었습니다.");
            resultMap.put("code", HttpStatus.CREATED);
        return resultMap;
        // PaymentInfoEntity newPay = new PaymentInfoEntity();
        // pRepo.save(newPay);
        // PaymentAddVO pay = new PaymentAddVO();
        // pay.setPaymentName(data.getPaymentName());
        // pay.setPaymentType(data.getPaymentType());
        // return pay;
    }

}
