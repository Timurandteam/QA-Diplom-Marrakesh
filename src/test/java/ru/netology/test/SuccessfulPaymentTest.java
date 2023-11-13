package ru.netology.test;

import ru.netology.data.SQLHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.page.PurchasePage;

import static com.codeborne.selenide.Selenide.open;

public class SuccessfulPaymentTest {
    @BeforeEach
    public void openSource() {
        open("http://localhost:8080");
        SQLHelper.deleteTable();
    }

    @Test
    @DisplayName("the Buy form should open")
    public void ShouldFormBuy() {
        var purchasepage = new PurchasePage();
        purchasepage.BuyCard();
    }

    @Test
    @DisplayName("a credit purchase form should open")
    public void ShouldFormBuyCredit() {
        var purchasepage = new PurchasePage();
        purchasepage.BuyCreditCard();
    }
}