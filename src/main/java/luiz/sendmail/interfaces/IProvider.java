package luiz.sendmail.interfaces;

import java.io.IOException;

public interface IProvider {
    void sendMail(String from, String to, String subject, String content) throws IOException;
}