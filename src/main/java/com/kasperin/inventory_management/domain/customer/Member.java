package com.kasperin.inventory_management.domain.customer;

import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberNumber;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phoneNumber;

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setName(String name) {
        String[] parts =name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    @PrePersist
    public void generateMemberNumber(){
        this.memberNumber= RandomStringUtils.randomAlphanumeric(12);
        log.info("The member with Name: "+this.getName()+" has been assigned Member Number: "+this.getMemberNumber());
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member", orphanRemoval = true)
    @OrderBy("dateCreated")
    /*@JoinTable(name = "member_purchase_order",
            joinColumns =  @JoinColumn (name = "member_id"),
            inverseJoinColumns = {@JoinColumn(name = "purchase_order_id")}
    )*/
    private List<PurchaseOrder> purchaseOrders = new ArrayList<>();

    public void addPurchaseOrder(PurchaseOrder purchaseOrder){
        this.purchaseOrders.add(purchaseOrder);
        purchaseOrder.setMember(this);
    }

    public void removePurchaseOrder(PurchaseOrder purchaseOrder){
        this.purchaseOrders.remove(purchaseOrder);
        purchaseOrder.setMember(null);
    }

}
