package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class StoreConverter {

    public static StoreResponseDTO.JoinResultDTO joinResultDTO(Store store) {
        return StoreResponseDTO.JoinResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Store toStore(StoreRequestDTO.JoinDto request) {
        return Store.builder()
                .name(request.getName())
                .score(request.getScore())
//                .region(request.getRegion())
                .reviewList(new ArrayList<>())
                .build();
    }
}
