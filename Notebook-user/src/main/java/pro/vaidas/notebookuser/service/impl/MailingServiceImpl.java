package pro.vaidas.notebookuser.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pro.vaidas.notebookuser.model.Mail;
import pro.vaidas.notebookuser.service.MailingService;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;

@Service
public class MailingServiceImpl implements MailingService {

    @Override
    public String sendEmail(Mail email) throws IOException {


        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8084/api/v1/mail/send";

        HttpEntity<Mail> request = new HttpEntity<Mail>(
                new Mail(email.getRecipient(), email.getSubject(), email.getMessage()));

        if (exists(url)) {
            String response = restTemplate.postForObject(url, request, String.class);
            System.out.println(response);
            if (response.equals("Email Sent successfully")) {
                return "Email Sent successfully";
            } else {
                return "Email not sent";
            }
        } else {return "Mailing server down";}
    }

    static boolean exists(String serverUrl)  {
        final Socket socket;

        try {
            URL url = new URL(serverUrl);
            socket = new Socket(url.getHost(), url.getPort());
        } catch (IOException e) {
            return false;
        }
        try {
            socket.close();
        } catch (IOException e) {     }
        return true;
    }
}
