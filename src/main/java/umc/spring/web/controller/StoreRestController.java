package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.converter.StoreMissionConverter;
import umc.spring.converter.StoreReviewConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.annotation.ExistMembers;
import umc.spring.validation.annotation.ExistRegion;
import umc.spring.validation.annotation.ExistStores;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @PostMapping("/join")
    public ApiResponse<StoreResponseDTO.StoreResultDTO> store(@RequestBody @Valid StoreRequestDTO.StoreDto request,
                                                              @ExistRegion @RequestParam(name="regionId") Long regionId) {
        Store store = storeCommandService.joinStore(regionId, request);
        return ApiResponse.onSuccess(StoreConverter.storeResultDTO(store));
    }

    @PostMapping("{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.ReviewResultDTO> review(@RequestBody @Valid StoreRequestDTO.ReviewDto request ,
                                                                @ExistStores @PathVariable(name = "storeId") Long storeId,
                                                                @ExistMembers @RequestParam(name = "memberId") Long memberId) {
        Review review = storeCommandService.createReview(storeId, memberId, request);
        return ApiResponse.onSuccess(StoreReviewConverter.reviewResultDTO(review));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStores @PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page){
        storeQueryService.getReviewList(storeId,page);
        return null;
    }

    @PostMapping("{storeId}/missions")
    public ApiResponse<StoreResponseDTO.MissionResultDTO> mission(@RequestBody @Valid StoreRequestDTO.MissionDto request,
                                                                  @ExistStores @PathVariable(name = "storeId") Long storeId) {
        Mission mission = storeCommandService.createMission(storeId, request);
        return ApiResponse.onSuccess(StoreMissionConverter.missionResultDTO(mission));
    }
}
