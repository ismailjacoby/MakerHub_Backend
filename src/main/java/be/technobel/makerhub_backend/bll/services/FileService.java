package be.technobel.makerhub_backend.bll.services;

import org.springframework.web.multipart.MultipartFile;

public interface  FileService {
    public String uploadFile(MultipartFile file);
}
