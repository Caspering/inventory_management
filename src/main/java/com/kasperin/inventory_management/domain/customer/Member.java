package com.kasperin.inventory_management.domain.customer;

import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private Set<PurchaseOrder> purchaseOrders = new HashSet<>();

    public Member addPurchaseOrder(PurchaseOrder purchaseOrder){
        purchaseOrder.setMember(this);
        this.purchaseOrders.add(purchaseOrder);
        return this;
    }

/*public Set<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;

    }*/

}
