package ru.aristovo.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.aristovo.base.BaseTests;

public class SberbankTest extends BaseTests {

    @Test
    public void testBookingCard() throws InterruptedException {

        // Нажать на меню – Карты
        String cardsButtonXPath = "//a[contains(text(), 'Карты')]";
        WebElement cardsButton = driver.findElement(By.xpath(cardsButtonXPath));
        cardsButton.click();

        Thread.sleep(5000);

    }

    /*
    3. Выбрать подменю – «Дебетовые карты»
    4. Проверить наличие на странице заголовка – «Дебетовые карты»
    5. Под заголовком из представленных карт найти “Молодёжная карта” и кликнуть на кнопку данной карты “Заказать онлайн”
    6. Проверить наличие на странице заголовка – «Молодёжная карта»
    7. кликнуть на кнопку «Оформить онлайн» под заголовком
    8. В представленной форме заполнить поля:
        •Фамилию, Имя, Отчетво, Имя и фамилия на карте, Дату рождения, E-mail, Мобильный телефон
        •Основной документ - не заполняем
    9. Проверить, что все поля заполнены правильно
    10. Нажать «Далее»
    11. Проверить, что появилось сообщение именно у незаполненных полях – «Обязательное поле»


    •Сборка проекта с помощью Maven
    •Аннотации JUnit (@Before, @After,....)
    •Assert, AssertThat
    •Параметризация (заполнять страницу с фио 3 раза)
     */
}
