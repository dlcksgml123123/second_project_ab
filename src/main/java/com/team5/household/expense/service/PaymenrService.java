package com.team5.household.expense.service;

import java.util.List;

import com.team5.household.expense.repository.MemberInfoRepository;
import com.team5.household.expense.repository.PaymentInfoRepository;
import com.team5.household.expense.vo.paymentVO.PaymentDeleteResponseVO;
import com.team5.household.expense.vo.paymentVO.PaymentListVO;
import com.team5.household.expense.vo.paymentVO.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.team5.household.expense.entity.MemberInfoEntity;
import com.team5.household.expense.entity.PaymentInfoEntity;


@Service
@RequiredArgsConstructor
public class PaymenrService {
    private final MemberInfoRepository mRepo;
    private final PaymentInfoRepository pRepo;

    //결제수단 등록
    public PaymentAddResponseVO addPayment(String eamil, PaymentAddVO data){
        MemberInfoEntity member = mRepo.findByEmail(eamil).orElseThrow();
        // PaymentInfoEntity payment = pRepo.findByPiSeq(data.g).orElseThrow();
        PaymentAddVO addPayment = new PaymentAddVO();
        addPayment.setType(data.getType());
        addPayment.setPayment(data.getPayment());
        addPayment.setMemberSeq(member.getMiSeq());
        // addPayment = PaymentAddVO.builder().type(data.getType()).payment(data.getPayment()).memberSeq(member.getMiSeq()).build();

        PaymentInfoEntity memberPaymentInfo = new PaymentInfoEntity(addPayment);

        pRepo.save(memberPaymentInfo);
        String type = null;
        if(addPayment.getType() == 1){
            type = "카드";
        }
        else if(addPayment.getType() == 2){
            type = "은행";
        }
        else if(addPayment.getType() == 3){
            type = "현금";
        }
        PaymentAddResponseVO response = new PaymentAddResponseVO();
        response.setStatus(true);
        response.setType(type);
        response.setTypename(addPayment.getPayment());
        response.setEmail(eamil);
        response.setMessage("결제수단이 등록되었습니다.");

        return response;
    }

    //결제수단 삭제 
    public PaymentDeleteResponseVO deletePayment(Long seq){
        PaymentDeleteResponseVO response = new PaymentDeleteResponseVO();
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
    public PaymentResponseVO checkPayment(Integer type){
        PaymentResponseVO response  = new PaymentResponseVO();
        List<PaymentInfoEntity> payList = pRepo.findByPiType(type);
     //    System.out.println(payList.toString());
        if(payList.size() != 0){
         response.setStatus(true);
         response.setMessage("카테고리 조회 성공");
         response.setPayment(payList);
        }else{
            response.setStatus(false);
            response.setMessage("조회할 카테고리가 없습니다.");
        }
        return response;
     }
}
