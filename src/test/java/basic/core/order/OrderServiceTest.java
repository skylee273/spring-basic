package basic.core.order;

import basic.core.domain.member.Grade;
import basic.core.domain.member.Member;
import basic.core.domain.order.Order;
import basic.core.service.MemberService;
import basic.core.service.MemberServiceImpl;
import basic.core.service.OrderService;
import basic.core.service.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder(){
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
