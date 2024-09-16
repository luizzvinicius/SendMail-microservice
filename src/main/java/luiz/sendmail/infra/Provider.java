package luiz.sendmail.infra;

import luiz.sendmail.exceptions.EmailNotSent;
import luiz.sendmail.interfaces.IProvider;
import luiz.sendmail.providers.SendGridProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Provider implements IProvider {
    private final SendGridProvider sendGridProvider;

    public Provider(SendGridProvider sendGridProvider) {
        this.sendGridProvider = sendGridProvider;
    }

    @Override
    public void sendMail(String from, String to, String subject, String content) {
        try {
            sendGridProvider.sendMail(from, to, subject, content);
        } catch (IOException e) {
            throw new EmailNotSent(to);
        }
    }
}