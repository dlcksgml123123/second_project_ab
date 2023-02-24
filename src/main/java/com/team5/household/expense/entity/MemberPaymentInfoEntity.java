package com.team5.household.expense.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="memberpayment_info")
public class MemberPaymentInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mpi_seq") private Long mpiSeq;
    @Column(name = "mpi_mi_seq") private Long mpiMiSeq;
    @Column(name = "mpi_pi_seq") private Long mpiPiSeq;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="mpi_mi_seq", insertable = false, updatable = false)
    private Collection<MemberInfoEntity> member;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="mpi_pi_seq", insertable = false, updatable = false)
    private Collection<PaymentInfoEntity> payment;

    @Builder
    public MemberPaymentInfoEntity(Long miSeq, Long piSeq){
        this.mpiMiSeq = miSeq;
        this.mpiPiSeq = piSeq;
    }
}
