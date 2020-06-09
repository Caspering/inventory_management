package com.kasperin.inventory_management.repository.customerRepository;

import com.kasperin.inventory_management.domain.customer.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberNumberIgnoreCase(String memberNumber);

    Optional<Member> findByPhoneNumber(String phoneNumber);

    Optional<Member> findByFirstNameOrLastName(String firstName, String lastName);
}
