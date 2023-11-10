package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;

import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.page.Paymentpage;
import ru.netology.page.Purchasepage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openSource() {
        open("http://localhost:8080");
        SQLHelper.deleteTable();
    }

    @Test
    @DisplayName("Shuld successful card payment approved")
    void theCardPaymentMustBeApproved() {
        var cardinfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCodcvccvv());
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();// купить
        var form = new Paymentpage();
        form.fillingOutTheForm(cardinfo);
        form.paymentSuccessfull();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Shuld successful card payment declined")
    void theCardPaymentMustBeDeclined() {
        var cardinfo = new DataHelper.CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCodcvccvv());
        var purchasepage = new Purchasepage();
        purchasepage.BuyCreditCard();// купить в кредит
        var form = new Paymentpage();
        form.fillingOutTheForm(cardinfo);
        form.declinedPayment();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }

    // номер карты 0
    @Test
    public void theCardIsNull() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getCardNumberNull());
        form.declinedPayment();
    }

    // номер карты знаков меньше 16
    @Test
    public void theCardLess16() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getCardNumberLess());
        form.invalidCardFormat();
    }

    // в номере карты символы
    @Test
    public void theCardSymbol() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getCardNumberNoValid());
        form.invalidCardFormat();
    }

    // в номере карты буквы кириллицы
    @Test
    public void theCardCyrillic() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getCardNumberCyrillic());
        form.invalidCardFormat();
    }

    // в номере карты буквы кириллицы
    @Test
    public void theCardLatinalphabet() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getCardNumberLatin());
        form.invalidCardFormat();
    }

    // Номер карты не заполнен
    @Test
    public void theCardEmpty() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getCardNumberEmpty());
        form.invalidCardFormat();
    }

    // месяц больше 12
    @Test
    public void monthMoreThan12() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getMonth13());
        form.invalidCardExpirationDate();
    }

    // месяц 0
    @Test
    public void zeroMonth() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getMonthNull());
        form.monthNotValid();
    }

    // в месяце символ
    @Test
    public void simbolMonth() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getMonthSymbol());
        form.monthNotValid();
    }

    // в месяце буква
    @Test
    public void letterMonth() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getMonthLetter());
        form.monthNotValid();
    }

    // в месяце одна цифра
    @Test
    public void unformattedMonth() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getMonthUnformatted());
        form.monthNotValid();
    }

    // ввод в поле месяц два нуля
    @Test
    public void MonthTwoZeros() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getMonthNullNull());
        form.monthNotValid();
    }

    // месяц не заполнен
    @Test
    public void emptyMonth() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getMonthempty());
        form.monthNotValid();
    }

    // год меньше текущего
    @Test
    public void lessThanCurrentOneYear() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getYearLessThanTheCurrentOne());
        form.theСardExpired();
    }

    // год больше текущего на 10 лет
    @Test
    public void yearLongerThanTheCurrentObn() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getTheYearIs10YearsLongerThanTheCurrentOne());
        form.invalidCardExpirationDate();
    }

    // в поле года символ
    @Test
    public void yearNotValid() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getYearSimbol());
        form.yearNotValid();
    }

    // в поле года буква
    @Test
    public void yearNotValidSimbol() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getYearLetter());
        form.yearNotValid();
    }

    // в поле года один символ
    @Test
    public void yearNotValidOne() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getYearOne());
        form.yearNotValid();
    }

    // поле года не заполнено
    @Test
    public void yearNotValidEmpty() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getYearEmpty());
        form.yearNotValid();
    }

    // заполнение поля владелец кириллицей
    @Test
    public void thereMustBeAnErrorWhenEnteringTheOwnerInCyrillic() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getOwnerCyrillic());
        form.ownerNotValid();
    }

    // ввод в поле владелец символы
    @Test
    public void InTheOwnerFieldTheCharacters() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getOnwerSimbol());
        form.ownerNotValid();
    }

    // ввод в поле владелец цифры
    @Test
    public void InTheOwnerFieldDigit() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getOnwerFigure());
        form.ownerNotValid();
    }

    // Ввод в поле только одну букву
    @Test
    public void EnteringOnlyOneLetterInTheOwnerField() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getOnwerOneLetter());
        form.ownerNotValid();
    }

    // Ввод в поле более ста букв
    @Test
    public void EnteringMoreThan100CharactersInTheOwnerField() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getOnwerMoreThan100());
        form.ownerNotValid();
    }

    // Незаполненное поле владелец
    @Test
    public void TheOwnerFieldIsEmpty() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getOnwerEmpty());
        form.ownerNotValid();
    }

    // Ввод в поле CVCCVV символы
    @Test
    public void InTheCVCCVVFieldTheCharacters() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getCVCCVVSimbol());
        form.cvcNotValid();
    }

    // Ввод в поле CVCCVV буквы
    @Test
    public void InTheCVCCVVFieldTheLetters() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getCVCCVVLetter());
        form.cvcNotValid();
    }

    // Ввод в поле CVCCVV только одну цифру
    @Test
    public void ThereIsOnlyOneDigitInTheCVCCVVField() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getCVCCVVonedigit());
        form.cvcNotValid();
    }

    // Ввод в поле CVCCVV только двух цифр
    @Test
    public void ThereIsOnlyTwoDigitInTheCVCCVVField() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getCVCCVVtwodigit());
        form.cvcNotValid();
    }

    // Незаполненное поле CVCCVV
    @Test
    public void TheCVCCVVFieldIsEmpty() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getCVCCVVtwodigit());
        form.cvcNotValid();
    }

    // Незаполненное поле CVCCVV
    @Test
    public void TheCVCCVVEqual000() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();
        var form = new Paymentpage();
        form.fillingOutTheForm(DataHelper.getCVCCVV000());
        form.cvcNotValid();
    }

    // незаполненная форма
    @Test
    @DisplayName("The form must be filled in")
    void theCardPaymentEmpty() {
        var purchasepage = new Purchasepage();
        purchasepage.BuyCard();// купить
        var form = new Paymentpage();
        form.emptyform();
    }
}