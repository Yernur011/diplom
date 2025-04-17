package kz.bbrent.api.diplom.domain.entity.business;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import kz.bbrent.api.diplom.domain.enums.DeliveryType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    List<String> images = new ArrayList<>();

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    List<String> brands = new ArrayList<>();

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    List<String> material = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    DeliveryType deliveryType;

    @OneToOne
    Items items;
}
