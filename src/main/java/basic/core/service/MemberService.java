package basic.core.service;

import basic.core.domain.member.Member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
