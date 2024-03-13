package be.technobel.makerhub_backend.pl.models.dtos;

import be.technobel.makerhub_backend.dal.models.entities.ProductionEntity;
import be.technobel.makerhub_backend.dal.models.enums.LicenseType;
import be.technobel.makerhub_backend.dal.models.enums.MusicGenre;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ProductionDto(
String title,
int bpm,
int duration,
String coverImage,
String audioMp3,
String audioWav,
String audioZip,
MusicGenre genre
) {
    public static ProductionDto fromEntity(ProductionEntity p){
        return new ProductionDto(p.getTitle(),p.getBpm(),p.getDuration(),p.getCoverImage(),p.getAudioMp3(),p.getAudioWav(),p.getAudioZip(),p.getGenre());
    }
}