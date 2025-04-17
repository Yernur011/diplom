package kz.bbrent.api.diplom.domain.dto.auth;

public record ChangePasswordRequest(String oldPassword,
                                    String newPassword,
                                    String newPasswordConfirmation) {
}
