package com.team5.household.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team5.household.entity.PaymentInfoEntity;
import com.team5.household.repository.PaymentInfoRepository;
import com.team5.household.vo.paymentVO.PaymentAddResponseVO;
import com.team5.household.vo.paymentVO.PaymentAddVO;
import com.team5.household.vo.paymentVO.PaymentListVO;
import com.team5.household.vo.paymentVO.PaymentResponseVO;

import net.bytebuddy.asm.Advice.Return;




@Service
public class PaymenrService {
    @Autowired PaymentInfoRepository pRepo;
    //결제수단 등록
    public PaymentAddResponseVO addPayment(PaymentAddVO data){
        PaymentInfoEntity addPayment = new PaymentInfoEntity(data);
        pRepo.save(addPayment);
        PaymentAddResponseVO response = new PaymentAddResponseVO();
        response.setPaymentType(addPayment.getPiType());
        response.setPaymentName(addPayment.getPiName());
        response.setMessage("결제수단이 등록되었습니다!");
        return response;
    }
    //결제수단 조회
    public PaymentResponseVO checkPayment(Integer type){
       PaymentResponseVO response  = new PaymentResponseVO();
       List<PaymentInfoEntity> payList = pRepo.findByPiType(type);
    //    System.out.println(payList.toString());
       if(payList.size() != 0){
        response.setStatus(true);
        response.setMessage("카테고리 조회 성공");
        response.setPayList(payList);

       }else{
           response.setStatus(false);
           response.setMessage("조회할 카테고리가 없습니다.");
       }
       return response;
    }
    //결제수단 삭제 
    public PaymentResponseVO deletePayment(Long seq){
        PaymentResponseVO response = new PaymentResponseVO();
        if(pRepo.countByPiSeq(seq) != 0){ 
            pRepo.deleteById(seq);
            response.setStatus(true); 
            response.setMessage("결제수단이 삭제되었습니다.");
        }
        else {
            response.setStatus(false);
            response.setMessage("삭제할 결제수단의 번호를 다시 확인해주세요.");
        }
        return response;   
    }
    //결제 수단 전부 조회
    public PaymentListVO listall(){
        PaymentListVO response = new PaymentListVO();
        List<PaymentInfoEntity> payList = pRepo.findAll();

        if(payList != null){
            response.setStatus(true);
            response.setMessage("결제수단 리스트 조회 성공");
            response.setPayList(payList);

        }else{
            response.setStatus(false);
            response.setMessage("조회할 리스트가 없습니다.");
        }
        return response;
    }
}
