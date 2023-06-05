package pro.vaidas.mailingservice.controller;

import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.vaidas.mailingservice.model.Mail;
import pro.vaidas.mailingservice.service.SendMailService;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/mail")
public class EmailController {
    SendMailService service;

    public EmailController(SendMailService service) {
        this.service = service;
    }

    @PostMapping(value="/send", consumes = "application/json")
    public ResponseEntity<String> sendMail(@RequestBody Mail mail) {
        service.sendMail(mail);
        return new ResponseEntity<>("Email Sent successfully", HttpStatus.OK);
    }

    @PostMapping("/attachment")
    public ResponseEntity<String> sendAttachmentEmail(@RequestBody Mail mail) throws MessagingException, UnsupportedEncodingException {
        service.sendMailWithAttachments(mail);
        return new ResponseEntity<>("Attachment Email sent successfully", HttpStatus.OK);
    }

    @PostMapping("/newNote")
    public ResponseEntity<String> sendNewNoteEmail(@RequestBody Mail mail) throws MessagingException {
        service.sendEmailOnNewNote(mail);
        return new ResponseEntity<>("New Note Email sent successfully", HttpStatus.OK);
    }
}
