package com.kasperin.inventory_management.domain.commerce;

import com.kasperin.inventory_management.domain.Items.FruitAndVege;
import com.kasperin.inventory_management.domain.Items.ProcessedFood;
import com.kasperin.inventory_management.domain.Items.Stationary;
import com.kasperin.inventory_management.domain.customer.Member;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateCreated;

    private int quantity;

    private String memberNumber;

    private String orderNumber;

    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    @OneToMany
    private Set<FruitAndVege> fruitAndVeges;

    @OneToMany
    private Set<Stationary> stationaries;

    @OneToMany
    private Set<ProcessedFood> processedFoods;

    @ManyToOne
    private Member member;

    public String getMemberNumber() {
        return member.getMemberNumber();
    }

    public void setMemberNumber() {
        this.memberNumber = getMemberNumber();
    }
}
