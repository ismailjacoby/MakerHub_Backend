package be.technobel.makerhub_backend.pl.controllers;

import be.technobel.makerhub_backend.bll.services.NewsletterEmailService;
import be.technobel.makerhub_backend.pl.models.forms.NewsletterSubscriptionForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newsletter")
public class NewsletterEmailController {

    private final NewsletterEmailService newsletterEmailService;

    public NewsletterEmailController(NewsletterEmailService newsletterEmailService) {
        this.newsletterEmailService = newsletterEmailService;
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/subscribe")
    public void subscribeToNewsletter(@RequestBody NewsletterSubscriptionForm form){
        newsletterEmailService.subscribeToNewsletter(form);
    }

    @PostMapping("/unsubscribe")
    public void unsubscribeFromNewsletter(@RequestBody NewsletterSubscriptionForm form){
        newsletterEmailService.unsubscribeFromNewsletter(form);
    }
}
