package be.technobel.makerhub_backend.bll.services;

import be.technobel.makerhub_backend.pl.models.forms.NewsletterSubscriptionForm;

public interface NewsletterEmailService {
    void subscribeToNewsletter(NewsletterSubscriptionForm form);
    void unsubscribeFromNewsletter(NewsletterSubscriptionForm form);
}
