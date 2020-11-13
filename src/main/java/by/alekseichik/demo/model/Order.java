package by.alekseichik.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "doctors_orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order")
    private String order;

    @Column(name = "diagnosis_id")
    private Long diagnosisId;
}
