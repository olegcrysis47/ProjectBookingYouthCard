package ru.aristovo.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.aristovo.base.BaseTests;

import static org.junit.Assert.assertEquals;

public class SberbankTest extends BaseTests {

    @Test
    public void testBookingCard() throws InterruptedException {

        // 2. Нажать на меню – Карты
        String cardsButtonXPath = "//a[contains(text(), 'Карты')]";
        WebElement cardsButton = driver.findElement(By.xpath(cardsButtonXPath));
        cardsButton.click();

        // 3. Выбрать подменю – «Дебетовые карты»
        String debtCardsButtonXPath = "//a[@data-cga_click_top_menu=" +
                "'Карты_Дебетовые карты_type_important' and contains(text(), 'Дебетовые карты')]";
        WebElement debtCardsButton = driver.findElement(By.xpath(debtCardsButtonXPath));
        debtCardsButton.click();

        // 4. Проверить наличие на странице заголовка – «Дебетовые карты»
        String headingPageXPath = "//h1";
        waitUtilElementToBeVisible(By.xpath(headingPageXPath));
        WebElement headingPage = driver.findElement(By.xpath(headingPageXPath));
        assertEquals("Заголовок \"Дебетовые карты\" не найден!",
                "Дебетовые карты", headingPage.getText());

        Thread.sleep(5000);

    }

    /*
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

    /*
    Сделать код более компактным можно с помощью типовых условий ожидания ExpectedConditions, встроенных в Selenium.
    - visibilityOf(WebElement element) — ожидание видимости присутствующего в DOM элемента.
    - visibilityOfElementLocated(By locator) — ожидание появления элемента в DOM и его видимости
    http://internetka.in.ua/selenium-driver-element-visibility/
     */
    private void waitUtilElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitUtilElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
