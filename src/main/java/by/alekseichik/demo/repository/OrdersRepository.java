package by.alekseichik.demo.repository;


import by.alekseichik.demo.model.Order;
import by.alekseichik.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long id);

}
