package com.example.paymentservice.PaymentGateways;

import com.example.paymentservice.Exceptions.payLinkException;
import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGatewayImpl implements PaymentGatewayInterface{

    @Value("${stripe.key}")
    String apiKey;

    @Override
    public String createStandardPaymentLink(Long amount, String orderId, String phoneNumber, String name, String email) throws payLinkException {
        String url;

        try {
        Stripe.apiKey = apiKey;


        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(getPrice(amount).getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                .setType(
                                                PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT
                                )
                                .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("https://shorturl.at/8qhzr").build()
                                )
                                .build()
                        )
                        .build();

            PaymentLink paymentLink = PaymentLink.create(params);
            url=paymentLink.getUrl();
        }
        catch (Exception e) {
            throw new payLinkException("Stripe: error generating Link:  "+e.getMessage());
        }

        return url;
    }


    private Price getPrice(Long amount) throws payLinkException {
        Price price;

        try {


            Stripe.apiKey = apiKey;
            PriceCreateParams params =
                    PriceCreateParams.builder()
                            .setCurrency("usd")
                            .setUnitAmount(amount)
                            .setRecurring(                      //   ----------------------------->   remove in case of one time payment
                                    PriceCreateParams.Recurring.builder()
                                            .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                            .build()
                            )
                            .setProductData(
                                    PriceCreateParams.ProductData.builder().setName("Gods Plan").build()
                            )
                            .build();
            price = Price.create(params);
        }
        catch (Exception e) {
            throw new payLinkException("Stripe: error generating Price:  "+e.getMessage());
        }

        return price;
    }


    /*
    Stripe Price object Doc: https://docs.stripe.com/api/prices/object
    * The Price object
        {
          "id": "price_1MoBy5LkdIwHu7ixZhnattbh",
          "object": "price",
          "active": true,
          "billing_scheme": "per_unit",
          "created": 1679431181,
          "currency": "usd",
          "custom_unit_amount": null,
          "livemode": false,
          "lookup_key": null,
          "metadata": {},
          "nickname": null,
          "product": "prod_NZKdYqrwEYx6iK",
          "recurring": {
            "aggregate_usage": null,
            "interval": "month",
            "interval_count": 1,
            "trial_period_days": null,
            "usage_type": "licensed"
          },
          "tax_behavior": "unspecified",
          "tiers_mode": null,
          "transform_quantity": null,
          "type": "recurring",
          "unit_amount": 1000,
          "unit_amount_decimal": "1000"
        }
    * */
}
