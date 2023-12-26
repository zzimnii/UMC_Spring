package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request) {

        Gender gender = null;

        switch (request.getGender()) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .age(request.getAge())
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                .build();
    }

    public static MemberResponseDTO.MemberMissionResultDTO toCreateMemberMissionResultDTO(MemberMission memberMission){
        return MemberResponseDTO.MemberMissionResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .build();
    }

    public static StoreResponseDTO.MissionPreViewDTO toMissionPreViewDTO(MemberMission memberMission) {
        return StoreResponseDTO.MissionPreViewDTO.builder()
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .build();
    }

    public static StoreResponseDTO.MissionPreViewListDTO toMissionPreViewListDTO(Page<MemberMission> memberMissionList) {
        List<StoreResponseDTO.MissionPreViewDTO> missionPreViewDTOList = memberMissionList.stream()
                .map(MemberConverter::toMissionPreViewDTO).collect(Collectors.toList());
        return StoreResponseDTO.MissionPreViewListDTO.builder()
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }
}
