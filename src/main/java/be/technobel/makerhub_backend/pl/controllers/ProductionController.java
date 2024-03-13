package be.technobel.makerhub_backend.pl.controllers;

import be.technobel.makerhub_backend.bll.services.ProductionService;
import be.technobel.makerhub_backend.pl.models.dtos.ProductionDto;
import be.technobel.makerhub_backend.pl.models.forms.ProductionForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

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

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public ProductionForm editProduction(@RequestBody ProductionForm productionForm){
        return productionService.editProduction(productionForm);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductionDto>> getAllProductions() {
        List<ProductionDto> productions = productionService.getAllProductions()
                .stream()
                .map(ProductionDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productions);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduction(@PathVariable("id") Long id) {
        productionService.deleteProduction(id);
    }
}

