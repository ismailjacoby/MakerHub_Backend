package be.technobel.makerhub_backend.dal.models.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCartEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> items;
}
