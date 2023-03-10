package clonecoding.tinder.members.controller;

import clonecoding.tinder.members.dto.*;
import clonecoding.tinder.members.service.MembersService;
import clonecoding.tinder.security.UserDetailsImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MembersController {

    private final MembersService membersService;

    // 전체 회원 조회해서 가져오기
    @ApiOperation(value = "회원 전체 조회(페이징)")
    @GetMapping
    public Page<MembersResponseDto> getMembers(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        log.info("Members 컨트롤러 - 회원 전체 조회 실행");
        return membersService.getMembers(userDetails.getMember().getPhoneNum());
    }

    @ApiOperation(value = "회원 한 명 조회")
    @GetMapping("/one")
    public MembersResponseDto getFirstMember(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody MemberFindRequestDto requestDto) {
        return membersService.getMember(userDetails.getMember().getPhoneNum(), requestDto);
    }

    @PostMapping("/signup")
    public MemberResponseMsgDto signup(@RequestBody MemberSignupRequestDto memberSignupRequestDto, HttpServletResponse response)  {
        return membersService.signup(memberSignupRequestDto, response);
    }

    @PostMapping("/login")
    public MemberResponseMsgDto login(@RequestBody MemberLoginRequestDto memberLoginRequestDto, HttpServletResponse response) {
        log.info("login 컨트롤러 실행 {}, {}", memberLoginRequestDto.getPhoneNum(), memberLoginRequestDto.getPassword());
        return membersService.login(memberLoginRequestDto, response);
    }

//    @PostMapping("/one")
//    public MembersResponseDto getMember(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody MemberFindRequestDto requestDto) {
//        return membersService.getMember(userDetails.getMember().getPhoneNum(), requestDto);
//    }
}
