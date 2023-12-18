package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreMissionConverter {

    public static StoreResponseDTO.MissionResultDTO missionResultDTO(Mission mission) {
        return StoreResponseDTO.MissionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(StoreRequestDTO.MissionDto request) {
        return Mission.builder()
                .reward(request.getReward())
                .price(request.getPrice())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .build();
    }
}
