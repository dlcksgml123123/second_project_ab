package com.team5.household.expense.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team5.household.expense.entity.PaymentInfoEntity;
import com.team5.household.expense.repository.PaymentInfoRepository;
import com.team5.household.expense.vo.responsevo.PaymentResponseVO;



@Service
public class PaymenrService {
    @Autowired PaymentInfoRepository pRepo;
    //결제수단 등록
    public PaymentResponseVO addPayment(PaymentInfoEntity data){
        PaymentResponseVO add = new PaymentResponseVO();
        List<PaymentInfoEntity> payList = pRepo.findAll();
        PaymentInfoEntity entity = pRepo.findByPiName(data.getPiName());//같은 카드 이름 중복 제거 
        if(payList.size() >=20){
            add.setStatus(false);
            add.setMessage("결제 수단을 20개 이상 등록할 수 없습니다.");
        }
        else {
            entity = new PaymentInfoEntity(null,data.getPiType(),data.getPiName());
            pRepo.save(entity);
            add.setStatus(true);
            add.setMessage("결제수단 등록 완료되었습니다.");
        }
        return add;
    }
    //결제수단 조회
    public PaymentResponseVO checkPayment(Integer type){
        PaymentResponseVO add = new PaymentResponseVO();
        PaymentInfoEntity payment = pRepo.findByPiType(type);
        if(payment == null){
            add.setStatus(false);
            add.setMessage("찾을 수 없는 결제수단입니다.");
            return add;
        }
        pRepo.findAll();
        add.setStatus(true);
        add.setMessage("결제수단이 조회되었습니다.");
        return add;
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
}
