package com.service.impl.order;

import com.converter.IConverter;
import com.dao.entity.order.OrderEntity;
import com.dao.impl.orm.order.OrderDAO;
import com.dto.order.Order;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;
    private IConverter<OrderEntity, Order> orderConverter;


    @Override
    public List<Order> allOrders() {
        return orderConverter.convertAll(orderDAO.getAll());
    }

    @Override
    public void add(Order order) {
        orderDAO.create(orderConverter.convertReverse(order));
    }

    @Override
    public void delete(Order order) {
        orderDAO.deleteEntity(order.getId());
    }

    @Override
    public void edit(Order order) {
        orderDAO.saveOrUpdateEntity(orderConverter.convertReverse(order));
    }

    @Override
    public Order getById(int id) {
        return orderConverter.convert(orderDAO.getEntity(id).get());
    }

    @Autowired
    public void setOrderDAO(OrderDAO orderDAO){
        this.orderDAO = orderDAO;
    }

    @Autowired
    public void setOrderConverter(IConverter<OrderEntity, Order> orderConverter) {
        this.orderConverter = orderConverter;
    }
}
