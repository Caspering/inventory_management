package com.kasperin.inventory_management.services.customerServices;

import com.kasperin.inventory_management.domain.customer.Member;

import java.util.List;

public interface MemberService {

    List<Member> findByFirstNameContaining(String firstName);
}
