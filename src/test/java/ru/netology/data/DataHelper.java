package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.lang.Math.random;
import static java.lang.String.format;

public class DataHelper {
    public static final Faker faker = new Faker(new Locale("en"));

    // создание конструктора
    private DataHelper() {
    }

    // заполнение поля "Номер карты"
    public static String getApprovedCardNumber() {
        return ("4444 4444 4444 4441");
    }

    public static String getDeclinedCardNumber() {
        return ("4444 4444 4444 4442");
    }

    // Заполнение поля "Месяц"
    public static String getValidMonth() {
        LocalDate localDate = LocalDate.now();
        return format("%02d", localDate.getMonthValue());
    }

    // Заполнение поля "Год"
    public static String getValidYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    // Заполнение поля "владелец"
    public static String getValidHolder() {
        return faker.name().firstName();
    }

    // Заполнение поля "код CVC/CVV"
    public static String getValidCodCvccvv() {
        double x = random() * 1000;
        int result = (int) Math.ceil(x);
        return String.valueOf(result);
    }

    // заполнение карты не валидными символами
    public static CardInfo getCardNumberNull() {
        return new CardInfo("0000000000000000", getValidMonth(), getValidYear(), getValidHolder(), getValidCodCvccvv());
    }

    public static CardInfo getCardNumberLess() {
        return new CardInfo("111111111", getValidMonth(), getValidYear(), getValidHolder(), getValidCodCvccvv());
    }

    public static CardInfo getCardNumberNoValid() {
        return new CardInfo("444444444?444444", getValidMonth(), getValidYear(), getValidHolder(), getValidCodCvccvv());
    }

    public static CardInfo getCardNumberCyrillic() {
        return new CardInfo("781234567890вв56", getValidMonth(), getValidYear(), getValidHolder(), getValidCodCvccvv());
    }

    public static CardInfo getCardNumberLatin() {
        return new CardInfo("781234567890VV56", getValidMonth(), getValidYear(), getValidHolder(), getValidCodCvccvv());
    }

    public static CardInfo getCardNumberEmpty() {
        return new CardInfo("", getValidMonth(), getValidYear(), getValidHolder(), getValidCodCvccvv());
    }

    // заполнение месяца не валидными символами
    public static CardInfo getMonth13() {
        return new CardInfo(getApprovedCardNumber(), "13", getValidYear(), getValidHolder(), getValidCodCvccvv());
    }

    public static CardInfo getMonthNull() {
        return new CardInfo(getApprovedCardNumber(), "0", getValidYear(), getValidHolder(), getValidCodCvccvv());
    }

    public static CardInfo getMonthSymbol() {
        return new CardInfo(getApprovedCardNumber(), "1!", getValidYear(), getValidHolder(), getValidCodCvccvv());
    }

    public static CardInfo getMonthLetter() {
        return new CardInfo(getApprovedCardNumber(), "F2", getValidYear(), getValidHolder(), getValidCodCvccvv());
    }

    public static CardInfo getMonthUnformatted() {
        return new CardInfo(getApprovedCardNumber(), "5", getValidYear(), getValidHolder(), getValidCodCvccvv());
    }

    public static CardInfo getMonthNullNull() {
        return new CardInfo(getApprovedCardNumber(), "00", getValidYear(), getValidHolder(), getValidCodCvccvv());
    }

    public static CardInfo getMonthEmpty() {
        return new CardInfo(getApprovedCardNumber(), "", getValidYear(), getValidHolder(), getValidCodCvccvv());
    }

    // заполнение года не валидными символами
    public static CardInfo getYearLessThanTheCurrentOne() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "22", getValidHolder(), getValidCodCvccvv());
    }

    public static CardInfo getTheYearIs10YearsLongerThanTheCurrentOne() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "54", getValidHolder(), getValidCodCvccvv());
    }

    public static CardInfo getYearSymbol() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "2+", getValidHolder(), getValidCodCvccvv());
    }

    public static CardInfo getYearLetter() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "2G", getValidHolder(), getValidCodCvccvv());
    }

    public static CardInfo getYearOne() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "2", getValidHolder(), getValidCodCvccvv());
    }

    public static CardInfo getYearEmpty() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "", getValidHolder(), getValidCodCvccvv());
    }

    // заполнение поля владелец не валидными символами
    public static CardInfo getOwnerCyrillic() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "ТИМУР", getValidCodCvccvv());
    }

    public static CardInfo getOnwerSimbol() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "TIM$UR", getValidCodCvccvv());
    }

    public static CardInfo getOnwerFigure() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "TI6MUR", getValidCodCvccvv());
    }

    public static CardInfo getOnwerOneLetter() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "T", getValidCodCvccvv());
    }

    public static CardInfo getOnwerMoreThan100() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "Tiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiimmmmmmmmmmmmmmmmmmmmmmmmmmmuuuuuuuuuuuuuuuuuuuuuuuuuuuurrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr", getValidCodCvccvv());
    }

    public static CardInfo getOnwerEmpty() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "", getValidCodCvccvv());
    }

    // заполнение CVC/CVV не валидными символами
    public static CardInfo getCVCCVVSimbol() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), "12%");
    }

    public static CardInfo getCVCCVVLetter() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), "12A");
    }

    public static CardInfo getCVCCVVonedigit() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), "7");
    }

    public static CardInfo getCVCCVVtwodigit() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), "79");
    }

    public static CardInfo getCVCCVVEmpty() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), "");
    }

    public static CardInfo getCVCCVV000() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), "000");
    }

    // данные карты
    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String holder;
        String codcvccvv;
    }

}