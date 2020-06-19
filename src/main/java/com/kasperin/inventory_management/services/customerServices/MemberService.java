package com.kasperin.inventory_management.services.customerServices;

import com.kasperin.inventory_management.domain.customer.Member;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface MemberService {

    List<Member> findAll();

    //@Validated(OnCreate.class)
    Member save(@Valid Member member) throws Exception;

    Member findByFirstName(String firstName);

    //@Validated(OnUpdate.class)
    Optional<Member> updateById(Long id, @Valid Member memberPatch);

    void deleteById(Long id);
}
