package umc.spring.service.StoreService;

import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {

    Store joinStore(StoreRequestDTO.StoreDto request);

    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDto request);

    Mission createMission(Long storeId, StoreRequestDTO.MissionDto request);
}
