package be.technobel.makerhub_backend.bll.services;

import be.technobel.makerhub_backend.dal.models.entities.ProductionEntity;
import be.technobel.makerhub_backend.pl.models.forms.ProductionForm;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductionService {
    public void uploadProduction(List<MultipartFile> files);
    ProductionForm editProduction(ProductionForm productionForm);
    List<ProductionEntity> getAllProductions();
    void deleteProduction(Long id);

}
