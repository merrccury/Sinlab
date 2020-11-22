package by.alekseichik.demo.dto;

import by.alekseichik.demo.model.Order;
import lombok.Data;

import javax.persistence.Column;

@Data
public class OrderDto {
    private Long id;
    private String order;
    private Long diagnosisId;

    public OrderDto(Order order){
        id = order.getId();
        this.order = order.getOrder();
        diagnosisId = order.getDiagnosisId();
    }

}
