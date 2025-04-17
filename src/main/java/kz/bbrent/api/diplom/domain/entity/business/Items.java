package kz.bbrent.api.diplom.domain.entity.business;

import jakarta.persistence.*;
import kz.bbrent.api.diplom.domain.enums.ItemType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false, columnDefinition = "TEXT")
    String icon;

    String title;

    String simpleDescription;

    @Enumerated(EnumType.STRING)
    ItemType itemType;

    BigDecimal price;

    @CreationTimestamp
    LocalDateTime createdAt;

    float rating;

    float discount;
}
