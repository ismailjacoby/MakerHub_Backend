package be.technobel.makerhub_backend.pl.controllers;

import be.technobel.makerhub_backend.bll.services.ProductionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/productions")
public class ProductionController {

    private final ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadProduction(@RequestParam("files") List<MultipartFile> files) {
        productionService.uploadProduction(files);
    }
}

