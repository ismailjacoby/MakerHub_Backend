package be.technobel.makerhub_backend.bll.mailing;

import be.technobel.makerhub_backend.dal.repositories.UserRepository;
import be.technobel.makerhub_backend.pl.models.forms.ContactForm;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    private final UserRepository userRepository;

    public EmailSenderService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void sendContactMessage(ContactForm form){
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(form.getEmail());
            helper.setTo("ismail.jacoby@gmail.com");
            helper.setSubject("Contact Form Submission: " + form.getSubject());

            String content = "Message from: " + form.getFirstName() + " " + form.getLastName() +
                    " (" + form.getEmail() + ")\n\n" + form.getMessage();

            mailSender.send(message);
            System.out.println("Mail sent successfully");
        }catch (MessagingException exception){
            exception.printStackTrace();
            System.out.println("Error sending email");
        }
    }

    public void forgotPasswordEmail(String email, String username, String password) {
        String subject = "Hybridvision - Forgot Password";
        String body = processForgotPasswordTemplate(email, username, password);

        sendEmail(email, subject, body);
    }

    private String processForgotPasswordTemplate(String email, String username, String password){
        Context context = new Context();
        context.setVariable("email", email);
        context.setVariable("username", username);
        context.setVariable("password", password);

        return templateEngine.process("account/forgotPassword",context);
    }

    public void sendEmail(String email, String subject, String body){
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(message, true,"UTF-8");

            helper.setFrom("checkmatemonsieur@gmail.com");
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(body, true);

            mailSender.send(message);

            System.out.println("Mail sent successfully!");
        } catch (MessagingException me){
            me.printStackTrace();
        }

    }
}
