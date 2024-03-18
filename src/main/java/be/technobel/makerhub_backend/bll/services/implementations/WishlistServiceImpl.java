package be.technobel.makerhub_backend.bll.services.implementations;

import be.technobel.makerhub_backend.bll.exceptions.NotFoundException;
import be.technobel.makerhub_backend.bll.services.WishlistService;
import be.technobel.makerhub_backend.dal.models.entities.ProductionEntity;
import be.technobel.makerhub_backend.dal.models.entities.SamplePackEntity;
import be.technobel.makerhub_backend.dal.models.entities.UserEntity;
import be.technobel.makerhub_backend.dal.models.entities.WishlistEntity;
import be.technobel.makerhub_backend.dal.repositories.ProductionRepository;
import be.technobel.makerhub_backend.dal.repositories.SamplePackRepository;
import be.technobel.makerhub_backend.dal.repositories.UserRepository;
import be.technobel.makerhub_backend.dal.repositories.WishlistRepository;
import be.technobel.makerhub_backend.pl.models.dtos.CartItemsDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository wishlistRepository;
    private final SamplePackRepository samplePackRepository;
    private final ProductionRepository productionRepository;
    private final UserRepository userRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, SamplePackRepository samplePackRepository, ProductionRepository productionRepository, UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.samplePackRepository = samplePackRepository;
        this.productionRepository = productionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addToWishlist(Long userId, Long productionId, Long samplePackId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
        WishlistEntity wishlist = user.getWishlist();

        if (wishlist == null) {
            wishlist = new WishlistEntity();
            wishlist.setUser(user);
            user.setWishlist(wishlist);
        }

        if (productionId != null) {
            ProductionEntity production = productionRepository.findById(productionId)
                    .orElseThrow(() -> new RuntimeException("Production not found."));
            wishlist.getProductions().add(production);
        } else if (samplePackId != null) {
            SamplePackEntity samplePack = samplePackRepository.findById(samplePackId)
                    .orElseThrow(() -> new RuntimeException("Sample Pack not found."));
            wishlist.getSamplePacks().add(samplePack);
        }

        wishlistRepository.save(wishlist);
    }

    @Override
    public void removeFromWishlist(Long userId, Long productionId, Long samplePackId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
        WishlistEntity wishlist = user.getWishlist();

        if (wishlist != null) {
            if (productionId != null) {
                ProductionEntity production = productionRepository.findById(productionId)
                        .orElseThrow(() -> new RuntimeException("Production not found."));
                wishlist.getProductions().remove(production);
            } else if (samplePackId != null) {
                SamplePackEntity samplePack = samplePackRepository.findById(samplePackId)
                        .orElseThrow(() -> new RuntimeException("Sample Pack not found."));
                wishlist.getSamplePacks().remove(samplePack);
            }

            wishlistRepository.save(wishlist);
        }
    }

    @Override
    public List<CartItemsDto> getAllWishlist(String username) {
        return null;
    }
}
