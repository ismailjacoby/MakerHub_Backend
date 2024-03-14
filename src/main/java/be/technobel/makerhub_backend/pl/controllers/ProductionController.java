package be.technobel.makerhub_backend.pl.controllers;

import be.technobel.makerhub_backend.bll.services.ProductionService;
import be.technobel.makerhub_backend.pl.models.dtos.ProductionDto;
import be.technobel.makerhub_backend.pl.models.forms.ProductionForm;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
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
    public ResponseEntity<?> uploadProduction(
            @RequestParam("title") String title,
            @RequestParam("bpm") int bpm,
            @RequestParam("releaseDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate releaseDate,
            @RequestParam("genre") String genre,
            @RequestParam("coverImage") MultipartFile coverImage,
            @RequestParam("audioMp3") MultipartFile audioMp3,
            @RequestParam(value = "audioWav", required = false) MultipartFile audioWav,
            @RequestParam(value = "audioZip", required = false) MultipartFile audioZip){
        productionService.uploadProduction(title, bpm, releaseDate, genre, coverImage, audioMp3, audioWav, audioZip);
        return ResponseEntity.ok().build();
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
    public ResponseEntity<?> deleteProduction(@PathVariable Long id) {
        productionService.deleteProduction(id);
        return ResponseEntity.ok().build();
    }
}

