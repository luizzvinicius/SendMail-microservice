package luiz.sendmail.providers;

import com.sendgrid.*;
import luiz.sendmail.interfaces.IProvider;
import org.springframework.stereotype.Component;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Content;

import java.io.IOException;

@Component
public class SendGridProvider implements IProvider {
    private final SendGrid sendGrid = new SendGrid(System.getenv("SENDGRID_API_KEY"));

    @Override
    public void sendMail(String from, String to, String subject, String content) throws IOException {
        Mail mail = new Mail(
                new Email(from),
                subject,
                new Email(to),
                new Content("text/plain", content)
        );

        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        sendGrid.api(request);
    }
}