package basic.core.service;

import basic.core.domain.member.Member;
import basic.core.domain.order.Order;
import basic.core.repository.DiscountPolicy;
import basic.core.repository.FixDiscountPolicy;
import basic.core.repository.MemberRepository;
import basic.core.repository.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
