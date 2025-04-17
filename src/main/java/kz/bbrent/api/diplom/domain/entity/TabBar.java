package kz.bbrent.api.diplom.domain.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TabBar {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    Map<String, Map<String, String>> data;
}
