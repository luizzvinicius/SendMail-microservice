package luiz.sendmail.exceptions;

public class EmailNotSent extends RuntimeException {
    public EmailNotSent(String to) {
        super(String.format("Email not sent to %s", to));
    }
}