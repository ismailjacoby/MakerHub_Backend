package be.technobel.makerhub_backend.pl.controllers;

import be.technobel.makerhub_backend.bll.services.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/add")
    public void addToWishlist(@RequestParam Long userId, @RequestParam Long productionId, @RequestParam Long samplePackId) {
        wishlistService.addToWishlist(userId, productionId, samplePackId);
    }

    @PostMapping("/remove")
    public void removeFromWishlist(@RequestParam Long userId, @RequestParam Long productionId, @RequestParam Long samplePackId) {
        wishlistService.removeFromWishlist(userId, productionId, samplePackId);
    }
}
