package ru.netology.test;

import data.SQLHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.Purchasepage;

import static com.codeborne.selenide.Selenide.open;

public class SuccessfulPaymentTest {
    @BeforeEach
    public void openSource() {
        open("http://localhost:8080");
        SQLHelper.deleteTable();
    }

    @Test
    @DisplayName("the Buy form should open")
    public void ShuldFormBuy() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
    }

    @Test
    @DisplayName("a credit purchase form should open")
    public void ShuldFormBuyCredit() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCreditCard();
    }
}