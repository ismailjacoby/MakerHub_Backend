package be.technobel.makerhub_backend.dal.repositories;

import be.technobel.makerhub_backend.dal.models.entities.NewsletterEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsletterEmailRepository extends JpaRepository<NewsletterEmail,Long> {
    boolean existsByEmail(String email);
    Optional<NewsletterEmail> findByEmail(String email);
}
