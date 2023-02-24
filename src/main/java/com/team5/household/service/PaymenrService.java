package com.team5.household.service;

import java.util.List;

import com.team5.household.entity.MemberInfoEntity;
import com.team5.household.entity.MemberPaymentInfoEntity;
import com.team5.household.repository.MemberInfoRepository;
import com.team5.household.repository.MemberPaymentInfoRepository;
import com.team5.household.vo.paymentVO.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team5.household.entity.PaymentInfoEntity;
import com.team5.household.repository.PaymentInfoRepository;


@Service
@RequiredArgsConstructor
public class PaymenrService {
    private final MemberInfoRepository mRepo;
    private final PaymentInfoRepository pRepo;
    private final MemberPaymentInfoRepository mpRepo;
    //결제수단 등록

    public PaymentAddResponseVO addPayment(PaymentAddVO data){
        MemberInfoEntity member = mRepo.findByMiSeq(data.getMemberSeq()).orElseThrow();
        PaymentInfoEntity payment = pRepo.findByPiSeq(data.getPaymentSeq()).orElseThrow();

        MemberPaymentInfoEntity memberPaymentInfo = new MemberPaymentInfoEntity(data.getMemberSeq(), data.getPaymentSeq());

        mpRepo.save(memberPaymentInfo);
        System.out.println(memberPaymentInfo.getMember());
//        PaymentAddResponseVO response = new PaymentAddResponseVO();
//        response.setPaymentType(addPayment.getPiType());
//        response.setPaymentName(addPayment.getPiName());
//        response.setMessage("결제수단이 등록되었습니다!");
        return null;
    }

    //결제수단 조회
    public PaymentResponseVO memberPaymentLisy(Long seq){
        PaymentResponseVO response = new PaymentResponseVO();
        List<MemberPaymentInfoEntity> payList = (List<MemberPaymentInfoEntity>) mpRepo.findByMiSeq(seq);
        if(payList.size() !=0){
            response.setStatus(true);
            response.setMessage("카테고리 조회 성공");
            response.setPayment(payList);
        }else {
            response.setStatus(false);
            response.setMessage("회원의 등록된 결제 수단이 없습니다.");
        }
        return response;
    }
//    public PaymentResponseVO checkPayment(Integer type){
//       PaymentResponseVO response  = new PaymentResponseVO();
//       List<PaymentInfoEntity> payList = pRepo.findByPiType(type);
//    //    System.out.println(payList.toString());
//       if(payList.size() != 0){
//        response.setStatus(true);
//        response.setMessage("카테고리 조회 성공");
//        response.setPayList(payList);
//
//       }else{
//           response.setStatus(false);
//           response.setMessage("조회할 카테고리가 없습니다.");
//       }
//       return response;
//    }
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
}
