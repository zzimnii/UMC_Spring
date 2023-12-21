package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.validation.annotation.ExistMembers;
import umc.spring.validation.annotation.ExistMissions;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/join")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/mission")
    public ApiResponse<MemberResponseDTO.MemberMissionResultDTO> doMemberMission(@RequestBody @Valid MemberRequestDTO.ChallengeMissionDto request,
                                                                                    @ExistMissions @RequestParam(name = "missionId") Long missionId,
                                                                                 @ExistMembers @RequestParam(name = "memberId") Long memberId){
        MemberMission memberMission = memberCommandService.createMemberMission(missionId, memberId, request);
        return ApiResponse.onSuccess(MemberMissionConverter.toCreateMemberMissionResultDTO(memberMission));
    }
}
