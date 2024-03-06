package be.technobel.makerhub_backend.bll.services.implementations;

import be.technobel.makerhub_backend.bll.services.NewsletterEmailService;
import be.technobel.makerhub_backend.dal.models.entities.NewsletterEmail;
import be.technobel.makerhub_backend.dal.repositories.NewsletterEmailRepository;
import be.technobel.makerhub_backend.pl.models.forms.NewsletterSubscriptionForm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class NewsletterEmailServiceImpl implements NewsletterEmailService {
    private final NewsletterEmailRepository newsletterEmailRepository;

    public NewsletterEmailServiceImpl(NewsletterEmailRepository newsletterEmailRepository) {
        this.newsletterEmailRepository = newsletterEmailRepository;
    }

    @Override
    public void subscribeToNewsletter(NewsletterSubscriptionForm form) {
        if(form == null){
            throw new IllegalArgumentException("Form can't be null");
        }

        NewsletterEmail newsletterEmail = new NewsletterEmail();
        newsletterEmail.setEmail(form.getEmail());
        newsletterEmailRepository.save(newsletterEmail);
    }

    @Override
    public void unsubscribeFromNewsletter(NewsletterSubscriptionForm form) {
        if (form==null){
            throw new IllegalArgumentException("Form can't be null");
        }

        NewsletterEmail user = newsletterEmailRepository.findByEmail(form.getEmail()).orElseThrow(()-> new EntityNotFoundException("Email not found."));
        user.setSubscribed(false);
        newsletterEmailRepository.save(user);
    }
}
