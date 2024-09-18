package luiz.sendmail.controllers;

import jakarta.validation.Valid;
import luiz.sendmail.dtos.SendMailDTO;
import luiz.sendmail.service.SendMailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class SendMailController {
    private final SendMailService sendMailService;

    public SendMailController(SendMailService sendMailService) {
        this.sendMailService = sendMailService;
    }

    @PostMapping
    public ResponseEntity<Void> sendMail(@RequestBody @Valid SendMailDTO dto) {
        sendMailService.sendMail(dto.from(), dto.to(), dto.subject(), dto.content());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}