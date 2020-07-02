package com.kasperin.inventory_management.services.customerServices;

import com.kasperin.inventory_management.controllers.exceptions.ResourceNotFoundException;
import com.kasperin.inventory_management.domain.customer.Member;
import com.kasperin.inventory_management.repository.customerRepository.MemberRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    public static final long ID = 1L;

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    MemberServiceImpl memberServiceImpl;


    @Test
    void findAll() {
        //given
        Member member = new Member();
        List<Member> members = new ArrayList<>();
        members.add(member);
        given(memberRepository.findAll()).willReturn(members);

        //when
        List<Member> foundMembers = memberServiceImpl.findAll();

        //then
        then(memberRepository).should().findAll();
        assertThat(foundMembers).hasSize(1);
    }

    @Test
    void findById() {
        //given
        Member member = new Member();
        given(memberRepository.existsById(ID)).willReturn(true);
        when(memberRepository.findById(ID)).thenReturn(Optional.of(member));

        //when
        Optional<Member> foundMember = memberServiceImpl.findById(ID);

        //then
        then(memberRepository).should().findById(ID);
        assertThat(foundMember).isNotNull();
    }

    @Test
    void save() throws Exception {
        //given
        Member member = new Member();
        given(memberRepository.save(any(Member.class))).willReturn(member);

        //when
        Member savedMember = memberServiceImpl.save(new Member());

        //then
        then(memberRepository).should().save(any(Member.class));
        assertThat(savedMember).isNotNull();
    }



    @Test
    void deleteById_whenIdIdFound() {
        //given
        Member member = new Member();
        given(memberRepository.existsById(ID)).willReturn(true);
        when(memberRepository.findById(ID)).thenReturn(Optional.of(member));
        //when
        memberServiceImpl.deleteById(ID);
        //then
        then(memberRepository).should(times(1)).delete(member);
    }

    @Test
    void deleteById_whenIdIsNotFoundThrowsNotFoundException() {

        //given
        given(memberRepository.existsById(ID)).willReturn(false);

        //then
        assertThrows(ResourceNotFoundException.class, () -> {
            memberServiceImpl.deleteById(ID);
        });
    }
}