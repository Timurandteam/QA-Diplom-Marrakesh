package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PurchasePage {
    private final SelenideElement Buy = $(byText("Купить"));
    private final SelenideElement BuyALoan = $(byText("Купить в кредит"));
    private final SelenideElement PaymentCard = $(byText("Оплата по карте"));
    private final SelenideElement CreditCard = $(byText("Кредит по данным карты"));

    public DashboardPage BuyCard() {
        Buy.click(); //нажимаем кнопку купить
        PaymentCard.shouldBe(visible); // видим оплата по карте
        return new DashboardPage();
    }

    public DashboardPage BuyCreditCard() {
        BuyALoan.click(); //нажимаем кнопку Купить в кредит
        CreditCard.shouldBe(visible); // видим Кредит по данным карты
        return new DashboardPage();
    }
}