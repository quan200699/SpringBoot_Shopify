package com.example.market.service.order;

import com.example.market.model.Orders;
import com.example.market.repository.IOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdersService implements IOrdersService {
    @Autowired
    private IOrdersRepository ordersRepository;

    @Override
    public Iterable<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return ordersRepository.findById(id);
    }

    @Override
    public Orders save(Orders orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public void remove(Long id) {
        ordersRepository.deleteById(id);
    }
}
