package basic.core.service;

import basic.core.domain.order.Order;

public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);

}

