package be.technobel.makerhub_backend.bll.services;

import be.technobel.makerhub_backend.pl.models.dtos.CartItemsDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface WishlistService {
    void addToWishlist(Long userId, Long productionId, Long samplePackId);
    void removeFromWishlist(Long userId, Long productionId, Long samplePackId);
    List<CartItemsDto> getAllWishlist(String username);
}
