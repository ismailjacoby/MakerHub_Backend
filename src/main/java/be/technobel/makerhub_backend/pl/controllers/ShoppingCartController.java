package be.technobel.makerhub_backend.pl.controllers;

import be.technobel.makerhub_backend.bll.services.ShoppingCartService;
import be.technobel.makerhub_backend.dal.models.enums.LicenseType;
import be.technobel.makerhub_backend.pl.models.dtos.CartItemsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/addItem")
    public ResponseEntity<String> addItemToCart(
            @RequestParam Long userId,
            @RequestParam Long itemId,
            @RequestParam boolean isProduction,
            @RequestParam LicenseType licenseType) {
        try {
            shoppingCartService.addToCart(userId, itemId, isProduction, licenseType);
            return new ResponseEntity<>("Item added to cart successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while adding the item to the cart.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/removeItem/{userId}/{cartItemId}")
    public ResponseEntity<String> removeItemFromCart(
            @PathVariable Long userId,
            @PathVariable Long cartItemId) {
        try {
            shoppingCartService.removeFromCart(userId, cartItemId);
            return new ResponseEntity<>("Item removed from cart successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while removing the item from the cart.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cart/items/{userId}")
    public ResponseEntity<List<CartItemsDto>> getCartItems(@PathVariable Long userId) {
        try {
            List<CartItemsDto> items = shoppingCartService.getAllCartItems(userId);
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
