package be.technobel.makerhub_backend.bll.services;

import org.springframework.stereotype.Service;


public interface WishlistService {
    void addToWishlist(Long userId, Long productionId, Long samplePackId);
    void removeFromWishlist(Long userId, Long productionId, Long samplePackId);
    void getWishlist();
}
