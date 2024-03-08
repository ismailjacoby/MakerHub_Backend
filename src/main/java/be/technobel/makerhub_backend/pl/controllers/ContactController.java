package be.technobel.makerhub_backend.pl.controllers;

import be.technobel.makerhub_backend.bll.mailing.EmailSenderService;
import be.technobel.makerhub_backend.pl.models.forms.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
    @Autowired
    private EmailSenderService emailSenderService;


    /**
     * Processes a contact form submission and sends an email message.
     *
     * @param form The contact form submitted by the user.
     * @return A string indicating the message was sent successfully.
     */
    @PostMapping("/contact")
    public String handleContactForm(@RequestBody ContactForm form) {

        // Creates a new ContactForm instance and sets its properties from the submitted form.
        ContactForm form1 = new ContactForm();
        form1.setFirstName(form.getFirstName());
        form1.setLastName(form.getLastName());
        form1.setEmail(form.getEmail());
        form1.setSubject(form.getSubject());
        form1.setMessage(form.getMessage());

        // Sends the contact message using the emailSenderService.
        emailSenderService.sendContactMessage(form1);
        return "Message sent successfully";
    }
}
