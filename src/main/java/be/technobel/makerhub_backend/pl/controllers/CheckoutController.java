package be.technobel.makerhub_backend.pl.controllers;

import be.technobel.makerhub_backend.bll.services.CheckoutService;
import be.technobel.makerhub_backend.dal.models.entities.OrderEntity;
import be.technobel.makerhub_backend.pl.models.dtos.OrderDto;
import be.technobel.makerhub_backend.pl.models.dtos.PaymentInfoDto;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<OrderDto> processCheckout(@RequestParam String username){
        try{
            OrderEntity order = checkoutService.processCheckout(username);
            OrderDto orderDto = OrderDto.fromEntity(order);
            return ResponseEntity.ok(orderDto);
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfoDto paymentInfo) throws StripeException {
        PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<String> createCheckoutSession(@RequestParam String username) {
        try {
            Session session = checkoutService.prepareCheckoutSession(username);
            return ResponseEntity.ok(session.getUrl());
        } catch (StripeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create checkout session");
        }
    }


}
