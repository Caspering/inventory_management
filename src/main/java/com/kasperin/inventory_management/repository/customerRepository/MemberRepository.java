package com.kasperin.inventory_management.repository.customerRepository;

import com.kasperin.inventory_management.domain.customer.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberNumberIgnoreCase(String memberNumber);
    Member findByFirstName(String firstName);

    Optional<Member> findByPhoneNumber(String phoneNumber);
    Optional<Member> findByFirstNameOrLastName(String firstName, String lastName);

    List<Member> findAllByFirstNameIgnoreCase(String firstName);
    List<Member> findAllByFirstNameContainingIgnoreCase(String firstName);

    boolean existsById(Long id);
    boolean existsByFirstNameIgnoreCase(String name);
    boolean existsByFirstNameContainingIgnoreCase(String firstName);
    boolean existsByMemberNumberIgnoreCase(String memberNumber);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByPhoneNumber(String phoneNumber);




}
