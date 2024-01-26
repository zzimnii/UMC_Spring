package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistMembers;
import umc.spring.validation.annotation.ExistMissions;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;


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
        return ApiResponse.onSuccess(MemberConverter.toCreateMemberMissionResultDTO(memberMission));
    }

    @PostMapping("/mission/{missionId}/complete")
    public ApiResponse<MemberResponseDTO.MemberMissionResultDTO> ComplteMemberMission(@RequestBody @Valid MemberRequestDTO.CompleteMissionDto request,
                                                                                 @ExistMissions @RequestParam(name = "missionId") Long missionId,
                                                                                 @ExistMembers @RequestParam(name = "memberId") Long memberId,
                                                                                      @RequestParam(name = "memberMissionId") Long memberMissionId){
        MemberMission memberMission = memberCommandService.completeMemberMission(missionId, memberId, memberMissionId, request);
        return ApiResponse.onSuccess(MemberConverter.toCreateMemberMissionResultDTO(memberMission));
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "특정 멤버의 리뷰 목록 조회 API",description = "특정 멤버의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1페이지입니다.")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getMemberReviewList(@ExistMembers @PathVariable(name = "memberId") Long memberId, @CheckPage @RequestParam(name = "page") Integer page){
        Page<Review> reviews = memberQueryService.getMyReviewList(memberId,page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviews));
    }

    @GetMapping("/{memberId}/missions/challenging")
    @Operation(summary = "특정 멤버의 진행중인 미션 목록 조회 API",description = "특정 멤버의 진행중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<StoreResponseDTO.MissionPreViewListDTO> getMemberMissionList(@ExistMembers @PathVariable(name = "memberId") Long memberId, @CheckPage @RequestParam(name = "page") Integer page){
        Page<MemberMission> missions = memberQueryService.getMyChallengeMissionList(memberId, page);
        return ApiResponse.onSuccess(MemberConverter.toMissionPreViewListDTO(missions));
    }

}
