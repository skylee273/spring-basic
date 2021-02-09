package basic.core.repository;

import basic.core.domain.member.Member;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long memberId);
}
