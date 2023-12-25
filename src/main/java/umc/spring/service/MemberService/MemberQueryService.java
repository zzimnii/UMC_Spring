package umc.spring.service.MemberService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Review;

import java.util.Optional;

public interface MemberQueryService {
    Optional<Member> findMember(Long id);

    Page<Review> getMyReviewList(Long memberId, Integer page);
}
