package luiz.sendmail.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SendMailDTO(
        @Email String from,
        @Email String to,
        @NotBlank String subject,
        @NotBlank String content
) {}