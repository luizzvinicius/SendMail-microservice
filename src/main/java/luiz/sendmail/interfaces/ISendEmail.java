package luiz.sendmail.interfaces;

public interface ISendEmail {
    void sendMail(String from, String to, String subject, String content);
}