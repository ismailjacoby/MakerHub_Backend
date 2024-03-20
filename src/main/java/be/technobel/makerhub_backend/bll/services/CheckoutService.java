package be.technobel.makerhub_backend.bll.services;

import be.technobel.makerhub_backend.dal.models.entities.OrderEntity;
import be.technobel.makerhub_backend.dal.models.entities.UserEntity;
import be.technobel.makerhub_backend.pl.models.dtos.PaymentInfoDto;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;



import java.util.List;

public interface CheckoutService {
    OrderEntity processCheckout(String username);
    PaymentIntent createPaymentIntent(PaymentInfoDto paymentInfo) throws StripeException;
    Session prepareCheckoutSession(String username) throws StripeException;
}
