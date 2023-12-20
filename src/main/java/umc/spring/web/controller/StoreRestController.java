package umc.spring.web.controller;

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

    @PostMapping("{storeId}/missions")
    public ApiResponse<StoreResponseDTO.MissionResultDTO> mission(@RequestBody @Valid StoreRequestDTO.MissionDto request,
                                                                  @ExistStores @PathVariable(name = "storeId") Long storeId) {
        Mission mission = storeCommandService.createMission(storeId, request);
        return ApiResponse.onSuccess(StoreMissionConverter.missionResultDTO(mission));
    }
}
