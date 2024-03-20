package be.technobel.makerhub_backend.bll.services.implementations;

import be.technobel.makerhub_backend.bll.exceptions.NotFoundException;
import be.technobel.makerhub_backend.bll.services.CheckoutService;
import be.technobel.makerhub_backend.dal.models.entities.CartItemEntity;
import be.technobel.makerhub_backend.dal.models.entities.OrderEntity;
import be.technobel.makerhub_backend.dal.models.entities.ShoppingCartEntity;
import be.technobel.makerhub_backend.dal.models.entities.UserEntity;
import be.technobel.makerhub_backend.dal.models.enums.PaymentStatus;
import be.technobel.makerhub_backend.dal.repositories.OrderRepository;
import be.technobel.makerhub_backend.dal.repositories.ShoppingCartRepository;
import be.technobel.makerhub_backend.dal.repositories.UserRepository;
import be.technobel.makerhub_backend.pl.models.dtos.PaymentInfoDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;


    public CheckoutServiceImpl(ShoppingCartRepository shoppingCartRepository,
                               OrderRepository orderRepository,
                               UserRepository userRepository,
                               @Value("${stripe.key.secret}") String secretKey) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        Stripe.apiKey = secretKey;
    }

    @Override
    @Transactional
    public OrderEntity processCheckout(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(()-> new NotFoundException( "User not found"));

        ShoppingCartEntity cart = user.getShoppingCart();
        if(cart == null || cart.getItems().isEmpty()){
            throw new IllegalArgumentException("Shopping cart is empty");
        }

        // Create a new OrderEntity
        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setTotalAmount(calculateTotal(cart.getItems()));
        order.setPaymentStatus(PaymentStatus.PAID);
        order.setItems(new ArrayList<>());

        // Transfer CartItemEntities to OrderEntity
        for (CartItemEntity item : cart.getItems()) {
            item.setOrder(order);
            item.setShoppingCart(null);
            order.getItems().add(item);
        }

        cart.getItems().clear();
        shoppingCartRepository.save(cart);

        return orderRepository.save(order);
    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfoDto paymentInfo) throws StripeException {
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", (paymentInfo.getAmount()));
        params.put("currency", paymentInfo.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);

        return PaymentIntent.create(params);
    }

    private double calculateTotal(List<CartItemEntity> items) {
        return items.stream()
                .mapToDouble(CartItemEntity::getPrice)
                .sum();
    }

    public Session prepareCheckoutSession(String username) throws StripeException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));

        ShoppingCartEntity cart = user.getShoppingCart();
        if (cart == null || cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Shopping cart is empty");
        }

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
        for (CartItemEntity item : cart.getItems()) {
            SessionCreateParams.LineItem lineItem = createLineItemForCartItem(item);
            if (lineItem != null) {
                lineItems.add(lineItem);
            }
        }

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("https://example.com/success")
                .setCancelUrl("https://example.com/cancel")
                .addAllLineItem(lineItems)
                .build();

        return Session.create(params);
    }

    private SessionCreateParams.LineItem createLineItemForCartItem(CartItemEntity item) {
        long price = Math.round(item.getPrice() * 100);

        SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("eur")
                .setUnitAmount(price)
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(item.getProduction() != null ? item.getProduction().getTitle() : item.getSamplePack().getTitle())
                                .build()
                )
                .build();

        return SessionCreateParams.LineItem.builder()
                .setQuantity(1L)
                .setPriceData(priceData)
                .build();
    }


}