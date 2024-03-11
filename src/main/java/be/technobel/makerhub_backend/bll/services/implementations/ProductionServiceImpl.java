package be.technobel.makerhub_backend.bll.services.implementations;

import be.technobel.makerhub_backend.bll.services.ProductionService;
import be.technobel.makerhub_backend.dal.models.entities.ProductionEntity;
import be.technobel.makerhub_backend.dal.repositories.ProductionRepository;
import com.mpatric.mp3agic.Mp3File;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class ProductionServiceImpl implements ProductionService {

    private final S3ServiceImpl s3Service;
    private final ProductionRepository productionRepository;

    public ProductionServiceImpl(S3ServiceImpl s3Service, ProductionRepository productionRepository) {
        this.s3Service = s3Service;
        this.productionRepository = productionRepository;
    }

    @Override
    public void uploadProduction(List<MultipartFile> files){
        ProductionEntity production = new ProductionEntity();


        for(MultipartFile file : files){
            //If file is not Empty do the following
            if(!file.isEmpty()) {
                String fileUrl = s3Service.uploadFile(file);
                String extension = StringUtils.getFilenameExtension(file.getOriginalFilename()).toLowerCase();

                switch (extension) {
                    case "mp3":
                        production.setAudioMp3(fileUrl);
                        try {
                            Path tempFile = Files.createTempFile("upload_", ".mp3");
                            File temp = tempFile.toFile();
                            file.transferTo(temp);

                            Mp3File mp3File = new Mp3File(temp);
                            long durationInSeconds = mp3File.getLengthInSeconds();
                            production.setDuration((int) durationInSeconds);
                            temp.delete();
                        } catch (Exception e){
                            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to process MP3 file.");
                        }
                        break;
                    case "wav":
                        production.setAudioWav(fileUrl);
                        break;
                    case "zip":
                        production.setAudioZip(fileUrl);
                        break;
                    case "png":
                        production.setCoverImage(fileUrl);
                        break;
                }
            }
        }

        productionRepository.save(production);
    }
}
