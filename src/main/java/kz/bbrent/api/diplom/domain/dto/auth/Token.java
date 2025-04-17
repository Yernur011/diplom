package kz.bbrent.api.diplom.domain.dto.auth;

public record Token (
    String token,
    String type,
    String createdAt,
    String expiredAt){
}
