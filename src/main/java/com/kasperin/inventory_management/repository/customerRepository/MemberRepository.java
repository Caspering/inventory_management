package com.kasperin.inventory_management.repository.customerRepository;

import com.kasperin.inventory_management.domain.Items.Stationary;
import com.kasperin.inventory_management.domain.customer.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberNumberIgnoreCase(String memberNumber);
    Member findByBarcode(String barcode);
    Member findByBarcodeOrNameIgnoreCase(String barcode, String name);
    Member findByFirstName(String firstName);

    Optional<Member> findByPhoneNumber(String phoneNumber);
    Optional<Member> findByFirstNameOrLastName(String firstName, String lastName);

    List<Member> findAllByFirstNameIgnoreCase(String firstName);

    boolean existsById(Long id);
    boolean existsByFirstNameIgnoreCase(String name);
    boolean existsByFirstNameContaining(String firstName);
    boolean existsByBarcode(String barcode);
    boolean existsByMemberNumberIgnoreCase(String memberNumber);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByPhoneNumber(String phoneNumber);




}
