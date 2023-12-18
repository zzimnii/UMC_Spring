package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreReviewConverter {

    public static StoreResponseDTO.ReviewResultDTO reviewResultDTO(Review review) {
        return StoreResponseDTO.ReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(StoreRequestDTO.ReviewDto request) {
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .content(request.getContent())
                .build();
    }
}
