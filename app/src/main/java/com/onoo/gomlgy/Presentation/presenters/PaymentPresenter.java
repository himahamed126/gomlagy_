package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.Network.response.CouponResponse;
import com.onoo.gomlgy.Network.response.OrderResponse;
import com.onoo.gomlgy.Network.response.SendEmailOrderResponse;
import com.onoo.gomlgy.Network.response.StripeClientSecretResponse;
import com.onoo.gomlgy.Presentation.ui.activities.PaymentView;
import com.onoo.gomlgy.Presentation.ui.activities.StripePaymentView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.CODInteractor;
import com.onoo.gomlgy.domain.interactors.CouponInteractor;
import com.onoo.gomlgy.domain.interactors.OrderInteractor;
import com.onoo.gomlgy.domain.interactors.PaypalInteractor;
import com.onoo.gomlgy.domain.interactors.SendEmailOrderInteractor;
import com.onoo.gomlgy.domain.interactors.StripeInteractor;
import com.onoo.gomlgy.domain.interactors.WalletInteractor;
import com.onoo.gomlgy.domain.interactors.impl.CODInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.CouponInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.OrderInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.PaypalInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.SendOrderEmailInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.StripeInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.WalletInteractorImpl;
import com.google.gson.JsonObject;

public class PaymentPresenter extends AbstractPresenter implements CouponInteractor.CallBack, PaypalInteractor.CallBack, StripeInteractor.CallBack, CODInteractor.CallBack, OrderInteractor.CallBack, WalletInteractor.CallBack, SendEmailOrderInteractor.CallBack {
    private PaymentView paymentView;
    private StripePaymentView stripePaymentView;

    public PaymentPresenter(Executor executor, MainThread mainThread, PaymentView paymentView) {
        super(executor, mainThread);
        this.paymentView = paymentView;
    }

    public PaymentPresenter(Executor executor, MainThread mainThread, StripePaymentView stripePaymentView) {
        super(executor, mainThread);
        this.stripePaymentView = stripePaymentView;
    }

    public void applyCoupon(int user_id, String code, String token) {
        new CouponInteractorImpl(mExecutor, mMainThread, this, user_id, code, token).execute();
    }

    public void submitPaypalOrder(String token, JsonObject jsonObject) {
        new PaypalInteractorImpl(mExecutor, mMainThread, this, token, jsonObject).execute();
    }

    public void submitStripeRequest(String token, JsonObject jsonObject) {
        new StripeInteractorImpl(mExecutor, mMainThread, this, token, jsonObject).execute();
    }

    public void submitWalletOrder(String token, JsonObject jsonObject) {
        new WalletInteractorImpl(mExecutor, mMainThread, this, token, jsonObject).execute();
    }

    public void submitCODOrder(String token, JsonObject jsonObject) {
        new CODInteractorImpl(mExecutor, mMainThread, this, token, jsonObject).execute();
    }

    public void submitOrder(String token, JsonObject jsonObject) {
        new OrderInteractorImpl(mExecutor, mMainThread, this, token, jsonObject).execute();
    }

    public void sendEmailOrder(String token, JsonObject jsonObject) {
        new SendOrderEmailInteractorImpl(mExecutor, mMainThread, this, token,jsonObject).execute();
    }

    @Override
    public void onCouponApplied(CouponResponse couponResponse) {
        if (paymentView != null){
            paymentView.onCouponApplied(couponResponse);
        }
    }

    @Override
    public void onCouponAppliedError() {

    }

    @Override
    public void onPayaplOrderSubmitted(OrderResponse orderResponse) {
        if (paymentView != null){
            paymentView.onOrderSubmitted(orderResponse);
        }
    }

    @Override
    public void onPayaplOrderSubmitError() {

    }

    @Override
    public void ononClientSecretReceived(StripeClientSecretResponse stripeClientSecretResponse) {
        if (stripePaymentView != null){
            stripePaymentView.onClientSecretReceived(stripeClientSecretResponse);
        }
    }

    @Override
    public void ononClientSecretReceiveError() {

    }

    @Override
    public void onCODOrderSubmitted(OrderResponse orderResponse) {
        if (paymentView != null){
            paymentView.onOrderSubmitted(orderResponse);
        }
    }

    @Override
    public void onCODOrderSubmitError() {

    }

    @Override
    public void onOrderSubmitted(OrderResponse orderResponse) {
        if (paymentView != null){
            paymentView.onOrderSubmitted(orderResponse);
        }
    }

    @Override
    public void onOrderSubmitError() {

    }

    @Override
    public void onWalletOrderSubmitted(OrderResponse orderResponse) {
        if (paymentView != null){
            paymentView.onOrderSubmitted(orderResponse);
        }
    }

    @Override
    public void onWalletOrderSubmitError() {

    }

    @Override
    public void onSendEmailOrderSubmitted(SendEmailOrderResponse sendEmailOrderResponse) {
        if (paymentView != null){
            paymentView.onSendEmailOrderSubmitted(sendEmailOrderResponse);
        }
    }

    @Override
    public void onSendEmailOrderSubmitError() {

    }
}
