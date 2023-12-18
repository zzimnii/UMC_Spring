package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.converter.StoreReviewConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.validation.annotation.ExistMembers;
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
    public ApiResponse<StoreResponseDTO.StoreResultDTO> store(@RequestBody @Valid StoreRequestDTO.StoreDto request) {
        Store store = storeCommandService.joinStore(request);
        return ApiResponse.onSuccess(StoreConverter.storeResultDTO(store));
    }

    @PostMapping("{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.ReviewResultDTO> review(@RequestBody @Valid StoreRequestDTO.ReviewDto request ,
                                                                @ExistStores @PathVariable(name = "storeId") Long storeId,
                                                                @ExistMembers @RequestParam(name = "memberId") Long memberId) {
        Review review = storeCommandService.createReview(storeId, memberId, request);
        return ApiResponse.onSuccess(StoreReviewConverter.reviewResultDTO(review));
    }
}
