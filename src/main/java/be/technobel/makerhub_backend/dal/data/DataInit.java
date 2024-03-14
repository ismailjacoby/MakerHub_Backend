package be.technobel.makerhub_backend.dal.data;

import be.technobel.makerhub_backend.dal.models.entities.ProductionEntity;
import be.technobel.makerhub_backend.dal.models.entities.UserEntity;
import be.technobel.makerhub_backend.dal.models.enums.MusicGenre;
import be.technobel.makerhub_backend.dal.models.enums.UserRole;
import be.technobel.makerhub_backend.dal.repositories.ProductionRepository;
import be.technobel.makerhub_backend.dal.repositories.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInit implements InitializingBean {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductionRepository productionRepository;

    public DataInit(UserRepository userRepository, PasswordEncoder passwordEncoder,
                    ProductionRepository productionRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.productionRepository = productionRepository;
    }

    /*
    * Creates a custom dummy database to test out different methods.
    * */
    @Override
    public void afterPropertiesSet() throws Exception {
        //ADMIN - Main
        UserEntity hybridvision = new UserEntity();
        hybridvision.setId(1L);
        hybridvision.setUsername("hybridvision");
        hybridvision.setFirstName("Hybrid");
        hybridvision.setLastName("Vision");
        hybridvision.setEmail("hybridvisionmusic@gmail.com");
        hybridvision.setActive(true);
        hybridvision.setBlocked(false);
        hybridvision.setRole(UserRole.ADMIN);
        hybridvision.setPassword(passwordEncoder.encode("Test1234="));
        userRepository.save(hybridvision);

        //ADMIN - Ismail
        UserEntity ismail = new UserEntity();
        ismail.setId(2L);
        ismail.setUsername("ismailjacoby");
        ismail.setFirstName("Ismail");
        ismail.setLastName("Jacoby");
        ismail.setEmail("ismailjacoby@gmail.com");
        ismail.setActive(true);
        ismail.setBlocked(false);
        ismail.setRole(UserRole.ADMIN);
        ismail.setPassword(passwordEncoder.encode("Test1234="));
        userRepository.save(ismail);

        //ADMIN - Ismail
        UserEntity francisco = new UserEntity();
        francisco.setId(3L);
        francisco.setUsername("franciscolopesmorais");
        francisco.setFirstName("Francisco");
        francisco.setLastName("Lopes Morais");
        francisco.setEmail("n1djfable@gmail.com");
        francisco.setActive(true);
        francisco.setBlocked(false);
        francisco.setRole(UserRole.ADMIN);
        francisco.setPassword(passwordEncoder.encode("Test1234="));
        userRepository.save(francisco);

        //CLIENT - string
        UserEntity user1 = new UserEntity();
        user1.setId(4L);
        user1.setUsername("stringstring");
        user1.setFirstName("String");
        user1.setLastName("String");
        user1.setEmail("string@gmail.com");
        user1.setActive(true);
        user1.setBlocked(false);
        user1.setRole(UserRole.CLIENT);
        user1.setPassword(passwordEncoder.encode("Test1234="));
        userRepository.save(user1);

        //CLIENT - string
        UserEntity user2 = new UserEntity();
        user2.setId(5L);
        user2.setUsername("testtest");
        user2.setFirstName("Test");
        user2.setLastName("Test");
        user2.setEmail("test@gmail.com");
        user2.setActive(true);
        user2.setBlocked(false);
        user2.setRole(UserRole.CLIENT);
        user2.setPassword(passwordEncoder.encode("Test1234="));
        userRepository.save(user2);

        // CLIENT - Travis Scott
        UserEntity travisScott = new UserEntity();
        travisScott.setId(6L);
        travisScott.setUsername("travisscott");
        travisScott.setFirstName("Travis");
        travisScott.setLastName("Scott");
        travisScott.setEmail("travisscott@example.com");
        travisScott.setActive(true);
        travisScott.setBlocked(false);
        travisScott.setRole(UserRole.CLIENT);
        travisScott.setPassword(passwordEncoder.encode("Test1234="));
        userRepository.save(travisScott);

        // CLIENT - Nicki Minaj
        UserEntity nickiminaj = new UserEntity();
        nickiminaj.setId(7L);
        nickiminaj.setUsername("nickiminaj");
        nickiminaj.setFirstName("Nicki");
        nickiminaj.setLastName("Minaj");
        nickiminaj.setEmail("nickiminaj@example.com");
        nickiminaj.setActive(true);
        nickiminaj.setBlocked(false);
        nickiminaj.setRole(UserRole.CLIENT);
        nickiminaj.setPassword(passwordEncoder.encode("Test1234="));
        userRepository.save(nickiminaj);

        // CLIENT - Pop Smoke
        UserEntity popsmoke = new UserEntity();
        popsmoke.setId(8L);
        popsmoke.setUsername("popsmoke");
        popsmoke.setFirstName("Pop");
        popsmoke.setLastName("Smoke");
        popsmoke.setEmail("popsmoke@example.com");
        popsmoke.setActive(true);
        popsmoke.setBlocked(false);
        popsmoke.setRole(UserRole.CLIENT);
        popsmoke.setPassword(passwordEncoder.encode("Test1234="));
        userRepository.save(popsmoke);

        // CLIENT - Snoop Dogg
        UserEntity snoopdogg = new UserEntity();
        snoopdogg.setId(9L);
        snoopdogg.setUsername("snoopdogg");
        snoopdogg.setFirstName("Snoop");
        snoopdogg.setLastName("Dogg");
        snoopdogg.setEmail("snoopdogg@example.com");
        snoopdogg.setActive(true);
        snoopdogg.setBlocked(false);
        snoopdogg.setRole(UserRole.CLIENT);
        snoopdogg.setPassword(passwordEncoder.encode("Test1234="));
        userRepository.save(snoopdogg);

        // CLIENT - Big Sean
        UserEntity bigsean = new UserEntity();
        bigsean.setId(10L);
        bigsean.setUsername("bigsean");
        bigsean.setFirstName("Big");
        bigsean.setLastName("Sean");
        bigsean.setEmail("bigsean@example.com");
        bigsean.setActive(true);
        bigsean.setBlocked(false);
        bigsean.setRole(UserRole.CLIENT);
        bigsean.setPassword(passwordEncoder.encode("Test1234="));
        userRepository.save(bigsean);

        // CLIENT - Post Malone
        UserEntity postmalone = new UserEntity();
        postmalone.setId(11L);
        postmalone.setUsername("postmalone");
        postmalone.setFirstName("Post");
        postmalone.setLastName("Malone");
        postmalone.setEmail("postmalone@example.com");
        postmalone.setActive(true);
        postmalone.setBlocked(false);
        postmalone.setRole(UserRole.CLIENT);
        postmalone.setPassword(passwordEncoder.encode("Test1234="));
        userRepository.save(postmalone);

        // CLIENT - Kanye West
        UserEntity kanyewest = new UserEntity();
        kanyewest.setId(12L);
        kanyewest.setUsername("kanyewest");
        kanyewest.setFirstName("Kanye");
        kanyewest.setLastName("West");
        kanyewest.setEmail("kanyewest@example.com");
        kanyewest.setActive(true);
        kanyewest.setBlocked(false);
        kanyewest.setRole(UserRole.CLIENT);
        kanyewest.setPassword(passwordEncoder.encode("Test1234="));
        userRepository.save(kanyewest);

        //Beat - Isla Rhythm
        ProductionEntity latin = new ProductionEntity();
        latin.setId(1L);
        latin.setTitle("Isla Rhythm");
        latin.setBpm(136);
        latin.setAudioMp3("https://hybridvision.s3.amazonaws.com/e4e898f5-ddc5-4502-8235-413c8b527939.mp3");
        latin.setCoverImage("https://hybridvision.s3.amazonaws.com/cb8b2cf1-ff66-41e5-a5a7-14abe27af9ad.png");
        latin.setReleaseDate(LocalDate.now());
        latin.setGenre(MusicGenre.LATIN);
        latin.setDuration(67);
        latin.setAvailable(true);
        productionRepository.save(latin);

        //Beat - Lies
        ProductionEntity lies = new ProductionEntity();
        lies.setId(2L);
        lies.setTitle("Lies");
        lies.setBpm(96);
        lies.setAudioMp3("https://hybridvision.s3.amazonaws.com/88869491-4198-4d40-b42c-37dfd0f4f558.mp3");
        lies.setCoverImage("https://hybridvision.s3.amazonaws.com/2ea0f1f3-31dd-47d5-a497-4f6d6676a631.png");
        lies.setReleaseDate(LocalDate.now());
        lies.setGenre(MusicGenre.RNB);
        lies.setDuration(136);
        lies.setAvailable(true);
        productionRepository.save(lies);

        //Beat - Toxic
        ProductionEntity toxic = new ProductionEntity();
        toxic.setId(3L);
        toxic.setTitle("Toxic");
        toxic.setBpm(100);
        toxic.setAudioMp3("https://hybridvision.s3.amazonaws.com/7196390b-cb11-42aa-8b50-1ed6bbde656c.mp3");
        toxic.setCoverImage("https://hybridvision.s3.amazonaws.com/d96e1a04-7d71-4b70-8479-26c5e6fc107f.png");
        toxic.setReleaseDate(LocalDate.now());
        toxic.setGenre(MusicGenre.CLUB);
        toxic.setDuration(146);
        toxic.setAvailable(true);
        productionRepository.save(toxic);


        //Beat - Chill
        ProductionEntity chill = new ProductionEntity();
        chill.setId(4L);
        chill.setTitle("Chill Trap");
        chill.setBpm(150);
        chill.setAudioMp3("https://hybridvision.s3.amazonaws.com/2d8ac8f0-8f2a-4a29-9d99-85662aaedbec.wav");
        chill.setCoverImage("https://hybridvision.s3.amazonaws.com/7a7155d8-4aa3-4ad3-9e8f-c4e6bfe7cf3e.png");
        chill.setReleaseDate(LocalDate.now());
        chill.setGenre(MusicGenre.EDM);
        chill.setDuration(89);
        chill.setAvailable(true);
        productionRepository.save(chill);

        //Beat - Ruthless
        ProductionEntity drill = new ProductionEntity();
        drill.setId(5L);
        drill.setTitle("Ruthless");
        drill.setBpm(150);
        drill.setAudioMp3("https://hybridvision.s3.amazonaws.com/fb6d4279-963c-4d20-a24c-ebdfd4ad9aae.mp3");
        drill.setCoverImage("https://hybridvision.s3.amazonaws.com/4cd095a2-a554-475d-b250-ce161e1bd0ba.png");
        drill.setReleaseDate(LocalDate.now());
        drill.setGenre(MusicGenre.DRILL);
        drill.setDuration(29);
        drill.setAvailable(true);
        productionRepository.save(drill);

        //Beat - Dancehall
        ProductionEntity dancehall = new ProductionEntity();
        dancehall.setId(6L);
        dancehall.setTitle("Safari");
        dancehall.setBpm(100);
        dancehall.setAudioMp3("https://hybridvision.s3.amazonaws.com/dc92286a-27a1-41ac-8e9f-e6009b731c56.wav");
        dancehall.setCoverImage("https://hybridvision.s3.amazonaws.com/27ddc8cc-63a4-4da4-8666-3e26fe95ef36.png");
        dancehall.setReleaseDate(LocalDate.now());
        dancehall.setGenre(MusicGenre.AFROBEATS);
        dancehall.setDuration(45);
        dancehall.setAvailable(true);
        productionRepository.save(dancehall);

        //Beat - Afrobeat
        ProductionEntity guidance = new ProductionEntity();
        guidance.setId(7L);
        guidance.setTitle("No Guidance");
        guidance.setBpm(99);
        guidance.setAudioMp3("https://hybridvision.s3.amazonaws.com/a93a465e-6b6b-48f4-b9d6-76c41f88bb8c.mp3");
        guidance.setCoverImage("https://hybridvision.s3.amazonaws.com/f218f578-1273-4602-b55e-890041d9615b.png");
        guidance.setReleaseDate(LocalDate.now());
        guidance.setGenre(MusicGenre.AFROBEATS);
        guidance.setDuration(175);
        guidance.setAvailable(true);
        productionRepository.save(guidance);

        //Beat - Lean on me
        ProductionEntity leanOnMe = new ProductionEntity();
        leanOnMe.setId(8L);
        leanOnMe.setTitle("Lean On Me");
        leanOnMe.setBpm(101);
        leanOnMe.setAudioMp3("https://hybridvision.s3.amazonaws.com/d9156966-4091-4f47-88b9-f1d2e927e387.mp3");
        leanOnMe.setCoverImage("https://hybridvision.s3.amazonaws.com/10c84393-6423-44aa-99cf-d91c870ee067.png");
        leanOnMe.setReleaseDate(LocalDate.now());
        leanOnMe.setGenre(MusicGenre.AFROBEATS);
        leanOnMe.setDuration(172);
        leanOnMe.setAvailable(true);
        productionRepository.save(leanOnMe);

        //Beat - Flashback
        ProductionEntity flashback = new ProductionEntity();
        flashback.setId(9L);
        flashback.setTitle("Lean On Me");
        flashback.setBpm(100);
        flashback.setAudioMp3("https://hybridvision.s3.amazonaws.com/0582abf3-6894-432c-b31e-18e34640c6e7.mp3");
        flashback.setCoverImage("https://hybridvision.s3.amazonaws.com/118d8eb7-73ad-430f-b9a2-e75543db5c93.png");
        flashback.setReleaseDate(LocalDate.now());
        flashback.setGenre(MusicGenre.AFROBEATS);
        flashback.setDuration(162);
        flashback.setAvailable(true);
        productionRepository.save(flashback);




    }
}
