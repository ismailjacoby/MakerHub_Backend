package be.technobel.makerhub_backend.pl.models.forms;

import be.technobel.makerhub_backend.dal.models.enums.LicenseType;
import be.technobel.makerhub_backend.dal.models.enums.MusicGenre;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.apache.logging.log4j.message.Message;

import java.time.LocalDate;

public class ProductionForm {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "BPM is required")
    @Min(20) @Max(200)
    @Positive(message = "Number must be positive")
    private int bpm;
    private int duration;
    private boolean available;
    @FutureOrPresent
    private LocalDate releaseDate;
    @NotBlank(message = "Cover Image is required")
    private String coverImage;
    @NotBlank(message = "Atleast one MP3 file is required")
    private String audioMp3;
    private String audioWav;
    private String audioZip;
    private MusicGenre genre;
}
