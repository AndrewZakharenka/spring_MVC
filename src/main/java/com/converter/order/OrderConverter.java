package com.converter.order;

import com.converter.AbstractConverter;
import com.dao.entity.client.ClientEntity;
import com.dao.entity.order.OrderEntity;
import com.dao.entity.product.ProductEntity;
import com.dto.client.Client;
import com.dto.order.Order;
import com.dto.product.Product;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter extends AbstractConverter<OrderEntity, Order> {

    @Override
    public Order convert(OrderEntity orderEntity) {
        return new Order(orderEntity.getId(), orderEntity.getDateOrder(),
                new Client(orderEntity.getClientEntity().getId(), orderEntity.getClientEntity().getName(),
                        orderEntity.getClientEntity().getSurname(),orderEntity.getClientEntity().getPhone()),
                new Product(orderEntity.getProductEntity().getId(), orderEntity.getProductEntity().getName(),
                        orderEntity.getProductEntity().getPrice()));
    }

    @Override
    public OrderEntity convertReverse(Order order) {
        return new OrderEntity(order.getId(), order.getDateOrder(),
                new ClientEntity(order.getClient().getId(), order.getClient().getName(),
                        order.getClient().getSurname(),order.getClient().getPhone()),
                new ProductEntity(order.getProduct().getId(), order.getProduct().getName(),
                        order.getProduct().getPrice()));
    }
}
