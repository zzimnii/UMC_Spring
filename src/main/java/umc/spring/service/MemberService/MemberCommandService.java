package umc.spring.service.MemberService;

import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberRequestDTO;

public interface MemberCommandService {

    Member joinMember(MemberRequestDTO.JoinDto request);

    MemberMission createMemberMission(Long missionId, Long memberId, MemberRequestDTO.ChallengeMissionDto request);

    MemberMission completeMemberMission(Long missionId, Long memberId, Long memberMissionId, MemberRequestDTO.CompleteMissionDto request);
}
