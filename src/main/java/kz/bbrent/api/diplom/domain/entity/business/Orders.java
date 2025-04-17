package kz.bbrent.api.diplom.domain.entity.business;

import jakarta.persistence.*;
import kz.bbrent.api.diplom.domain.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    UUID trackingId;

    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    OrderStatus status;

}
