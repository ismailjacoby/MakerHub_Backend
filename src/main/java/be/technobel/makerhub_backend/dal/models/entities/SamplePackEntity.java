package be.technobel.makerhub_backend.dal.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "sample_pack")
public class SamplePackEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private double price;
    private String coverImageUrl;
    private String audioUrl;

    @ManyToMany(mappedBy = "samplePacks")
    private List<WishlistEntity> wishlists;
}
