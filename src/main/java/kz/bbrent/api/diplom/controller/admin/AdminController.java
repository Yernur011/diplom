package kz.bbrent.api.diplom.controller.admin;

import kz.bbrent.api.diplom.service.auth.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static kz.bbrent.api.diplom.utills.codes.Uris.API_V1;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1)
public class AdminController {
    private final
    RegistrationService registrationService;

}
