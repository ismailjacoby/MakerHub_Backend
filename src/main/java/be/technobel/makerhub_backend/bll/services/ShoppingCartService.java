package be.technobel.makerhub_backend.bll.services;

import be.technobel.makerhub_backend.dal.models.enums.LicenseType;
import be.technobel.makerhub_backend.pl.models.dtos.CartItemsDto;

import java.util.List;

public interface ShoppingCartService {
    void addToCart(Long userId, Long itemId, boolean isProduction, LicenseType licenseType);
    void removeFromCart(Long userId, Long cartItemId);
    public List<CartItemsDto> getAllCartItems(Long userId);

}
