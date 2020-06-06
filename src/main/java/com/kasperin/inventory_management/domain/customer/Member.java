package com.kasperin.inventory_management.domain.customer;

import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private Set<PurchaseOrder> purchaseOrders;

}
