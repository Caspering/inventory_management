package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.domain.customer.Member;
import com.kasperin.inventory_management.services.customerServices.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(description = "Member API")
@RestController
@RequestMapping(MemberController.BASE_URL)
@RequiredArgsConstructor
public class MemberController {
    public static final String BASE_URL = "/api/v1/members";

    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Member createNewMember(@RequestBody Member member) throws Exception {
        return memberService.save(member);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Member> getAll(@RequestParam(value = "name", defaultValue = "") String name) {
        if (!name.isEmpty()){
            return memberService.findAllByFirstNameContaining(name);
        }//else return everything
        return memberService.findAll();
    }

    @ApiOperation(value = "Update a member property by id")
    @PatchMapping({"/{id}"})
    public Optional<Member> updateById(@PathVariable Long id, @RequestBody Member member){
        return memberService.updateById(id,member);
    }

    @ApiOperation(value = "Get a member by its id")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Optional<Member> getById(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @ApiOperation(value = "Delete a member by its id")
    @DeleteMapping({"{ID}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long ID) {
        memberService.deleteById(ID);
    }
}
