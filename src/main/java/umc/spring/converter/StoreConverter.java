package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class StoreConverter {

    public static StoreResponseDTO.StoreResultDTO storeResultDTO(Store store) {
        return StoreResponseDTO.StoreResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Store toStore(StoreRequestDTO.StoreDto request) {
        return Store.builder()
                .name(request.getName())
                .score(request.getScore())
                .address(request.getAddress())
                .reviewList(new ArrayList<>())
                .build();
    }
}
