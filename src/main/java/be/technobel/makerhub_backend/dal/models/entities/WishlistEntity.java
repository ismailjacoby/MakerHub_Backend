package be.technobel.makerhub_backend.dal.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "wishlist")
public class WishlistEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn (name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @ManyToMany
    @JoinTable(
        name = "wishlist_productions",
        joinColumns = @JoinColumn(name = "wishlist_id"),
        inverseJoinColumns = @JoinColumn(name = "samplepack_id")
    )
    private List<ProductionEntity> productions;


    @ManyToMany
    @JoinTable(
            name = "wishlist_sample_packs",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "sample_pack_id")
    )
    private List<SamplePackEntity> samplePacks;

}
