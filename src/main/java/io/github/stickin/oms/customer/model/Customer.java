package io.github.stickin.oms.customer.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "oms_customer")
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
    private UUID id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

}
