package com.kasperin.inventory_management.repository.customerRepository;

import com.kasperin.inventory_management.domain.customer.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberNumberIgnoreCase(String memberNumber);

    Member findByPhoneNumber(String phoneNumber);
}
