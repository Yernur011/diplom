package kz.bbrent.api.diplom.domain.dto.mail;

import java.util.List;

public record RequestToSendEmailDto(String receiver,
                                    List<String> receivers,
                                    String senderEmail,
                                    String subject,
                                    String text) {
}
