package by.alekseichik.demo.services;

import by.alekseichik.demo.model.Order;
import by.alekseichik.demo.repository.OrdersRepository;

public class OrderServiceImpl implements OrderService{
    private final OrdersRepository ordersRepository;

    public OrderServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }


    @Override
    public void addOrder(Long diagnosesId, String docOrder) {
        Order order = new Order();
        order.setDiagnosisId(diagnosesId);
        order.setOrder(docOrder);
        ordersRepository.save(order);
    }
}
