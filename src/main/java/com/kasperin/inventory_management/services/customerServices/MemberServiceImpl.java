package com.kasperin.inventory_management.services.customerServices;

import com.kasperin.inventory_management.domain.customer.Member;
import com.kasperin.inventory_management.repository.customerRepository.MemberRepository;
import com.kasperin.inventory_management.services.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;


    private Optional<Member> getMemberById(Long id) {
        if (memberRepository.existsById(id)) {
            return memberRepository.findById(id);
        }else{
            throw new ResourceNotFoundException
                    ("Member with the requested id: "+ id +" was not found");
        }
    }
    private List<Member> getMemberByFirstName(String firstName) {
        if (memberRepository.existsByFirstNameIgnoreCase(firstName)) {
            return memberRepository.findAllByFirstNameIgnoreCase(firstName);
        }else{
            throw new ResourceNotFoundException
                    ("Member with the requested first name: "+ firstName +" was not found");
        }
    }
    private List<Member> getMemberByFirstNameContaining(String firstName) {
        if (memberRepository.existsByFirstNameContainingIgnoreCase(firstName)) {
            return memberRepository.findAllByFirstNameContainingIgnoreCase(firstName);
        }else{
            throw new ResourceNotFoundException
                    ("Member with the requested first name: "+ firstName +" was not found");
        }
    }
    private Member getMemberByMemberNumber(String memberNumber) {
        if (memberRepository.existsByMemberNumberIgnoreCase(memberNumber)) {
            return memberRepository.findByMemberNumberIgnoreCase(memberNumber);
        }else{
            throw new ResourceNotFoundException
                    ("Member with the requested member-number: "+ memberNumber +" was not found");
        }
    }


    @Override
    //@Validated(OnCreate.class)
    public Member save(@Valid Member member) throws Exception {

        if (!(memberRepository.existsByEmailIgnoreCase(member.getEmail()))){
            if(!(memberRepository.existsByPhoneNumber(member.getPhoneNumber()))){
                log.info("Member item: " + member.getName() + ", has been saved");
                return memberRepository.save(member);
            }else throw new Exception("A member with phone number: " + member.getPhoneNumber() +" already exists");
        }else throw new Exception("A member with email address: " + member.getEmail() +" already exists");
    }

    @Override
    public Optional<Member> findById(Long id) {
        return getMemberById(id);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public List<Member> findAllByFirstName(String firstName) {
        return getMemberByFirstName(firstName);
    }

    @Override
    public Member findByMemberNumber(String memberNumber) {
        return getMemberByMemberNumber(memberNumber);
    }

    @Override
    public List<Member> findAllByFirstNameContaining(String firstName) {
        return getMemberByFirstNameContaining(firstName);
    }

    @Override
    //@Validated(OnUpdate.class)
    public Optional<Member> updateById(Long id, @Valid Member memberPatch) {
        return getMemberById(id)
                .map(memberInDB -> {
                    updateFirstName(memberPatch, memberInDB);

                    updateLastName(memberPatch, memberInDB);

                    updateEmail(memberPatch, memberInDB);

                    updatePhoneNumber(memberPatch, memberInDB);

                    log.info("The member with member number : " + memberInDB.getMemberNumber() + " was updated");

                    return memberRepository.save(memberInDB);
                });
    }

    @Override
    public void deleteById(Long id)  {
        getMemberById(id).map(member -> {memberRepository.delete(member);
            return null;
        });
    }



    public void updateFirstName(@Valid Member memberPatch, Member memberInDB) {
        if (memberPatch.getFirstName() != null)
            memberInDB.setFirstName(memberPatch.getLastName());
    }

    public void updateLastName(@Valid Member memberPatch, Member memberInDB) {
        if (memberPatch.getLastName() != null)
            memberInDB.setLastName(memberPatch.getLastName());
    }

    public void updateEmail(@Valid Member memberPatch, Member memberInDB) {
        if (memberPatch.getEmail() != null)
            memberInDB.setEmail(memberPatch.getEmail());
    }

    public void updatePhoneNumber(@Valid Member memberPatch, Member memberInDB) {
        if (memberPatch.getPhoneNumber() != null)
            memberInDB.setPhoneNumber(memberPatch.getPhoneNumber());
    }

}