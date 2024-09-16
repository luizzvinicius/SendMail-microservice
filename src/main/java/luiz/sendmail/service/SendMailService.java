package luiz.sendmail.service;

import luiz.sendmail.infra.Provider;
import luiz.sendmail.interfaces.ISendEmail;
import org.springframework.stereotype.Service;

@Service
public class SendMailService implements ISendEmail {
    private final Provider provider;

    public SendMailService(Provider provider) {
        this.provider = provider;
    }

    @Override
    public void sendMail(String from, String to, String subject, String content) {
        provider.sendMail(from, to, subject, content);
    }
}