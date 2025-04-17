package kz.bbrent.api.diplom.service.external.email;

import kz.bbrent.api.diplom.domain.dto.mail.RequestToSendEmailDto;

public interface MailSenderService {
    String sendSimpleMessage(RequestToSendEmailDto requestToSendEmailDto);
}
