package ru.aristovo.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

        // 5. Под заголовком из представленных карт найти “Молодёжная карта”
        // и кликнуть на кнопку данной карты “Заказать онлайн”
        String orderOnlineYouthCardXPath = "//a[@data-product='Молодёжная карта']/span[text()='Заказать онлайн']";
        WebElement orderOnlineYouthCard = driver.findElement(By.xpath(orderOnlineYouthCardXPath));
        waitElementToBeClickable(orderOnlineYouthCard);
        orderOnlineYouthCard.click();

        // 6. Проверить наличие на странице заголовка – «Молодёжная карта»
        String headPageYouthXPath = "//h1";
        waitUtilElementToBeVisible(By.xpath(headPageYouthXPath));
        WebElement headPageYouth = driver.findElement(By.xpath(headPageYouthXPath));
        assertEquals("Заголовок \"Молодёжная карта\" не найден!",
                "Молодёжная карта", headPageYouth.getText());

        /* НЕ СРАБАТЫВАЕТ
        org.openqa.selenium.ElementClickInterceptedException: element click intercepted: Element is not clickable at point (473, -242)
        без этого кода драйвер сам прыгает на форму заполнения
        // 7. Кликнуть на кнопку «Оформить онлайн» под заголовком
        String checkoutOnlineButtonXPath =
                "//a[@data-test-id='PageTeaserDict_button']/span[contains(text(), 'Оформить онлайн')]";
        WebElement checkoutOnlineButton = driver.findElement(By.xpath(checkoutOnlineButtonXPath));
        waitElementToBeClickable(checkoutOnlineButton);
        checkoutOnlineButton.click();
        */

        // Прокрутка до формы заполнения "Личные данные"
        String personalDataXPath = "//h2[text()='Личные данные']";
        WebElement personalData = driver.findElement(By.xpath(personalDataXPath));
        scrollToElementJs(personalData);

        Thread.sleep(5000);

    }

    /*
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

    /*
    Ожидаем когда элемент станет кликабельным
     */
    private void waitElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Метод для прокрутки до нужного места
    private void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
