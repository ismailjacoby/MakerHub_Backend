package be.technobel.makerhub_backend.bll.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductionService {

    public void uploadProduction(List<MultipartFile> files);
}
