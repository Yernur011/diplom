package kz.bbrent.api.diplom.domain.entity.business;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @OneToMany
    List<Items> items = new ArrayList<>();

    @CreationTimestamp
    LocalDateTime createdAt;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    Map<String, Object> paymentMethod = new HashMap<>();

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    Map<String, Object> deliveryMethod = new HashMap<>();

}
