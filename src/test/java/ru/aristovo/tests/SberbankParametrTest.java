package ru.aristovo.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.aristovo.base.BaseTests;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SberbankParametrTest extends BaseTests {

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                {"Пушкарев", "Олег", "Александрович", "OLEG PUSHKAREV"},
                {"Стасюк", "Павел", "Павлович", "PAVEL STASIUK"},
                {"Багдалова", "Кристина", "Наильевна", "KRISTINA BAGDALOVA"}
        });
    }

    @Parameterized.Parameter(0)
    public String lastName;

    @Parameterized.Parameter(1)
    public String firstName;

    @Parameterized.Parameter(2)
    public String middleName;

    @Parameterized.Parameter(3)
    public String cardName;

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
        org.openqa.selenium.ElementClickInterceptedException:
        element click intercepted: Element is not clickable at point (473, -242)
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
        waitUtilElementToBeVisible(personalData);
        scrollToElementJs(personalData);

        Thread.sleep(4000); // пришлось проставить задержку, т.к. не прокручивал до "Личные данные"

        // 8. В представленной форме заполнить поля:
        //        •Фамилию, Имя, Отчетво, Имя и фамилия на карте, Дату рождения, E-mail, Мобильный телефон
        //        •Основной документ - не заполняем

        // Фамилия
        String lastNameFieldXPath = "//input[@name='odc-personal__lastName']";
        WebElement lastNameField = driver.findElement(By.xpath(lastNameFieldXPath));
        waitElementToBeClickable(lastNameField);
        lastNameField.click();
        lastNameField.sendKeys(lastName);

        assertEquals("Фамилия введена не верно", lastName, lastNameField.getAttribute("value"));

        // Имя
        String firstNameFiledXPath = "//input[@id='odc-personal__firstName']";
        WebElement firstNameField = driver.findElement(By.xpath(firstNameFiledXPath));
        waitElementToBeClickable(firstNameField);
        firstNameField.click();
        firstNameField.sendKeys(firstName);

        assertEquals("Имя введено не верно", firstName, firstNameField.getAttribute("value"));

        // Отчетство
        String middleNameFieldXPath = "//input[@id='odc-personal__middleName']";
        WebElement middleNameField = driver.findElement(By.xpath(middleNameFieldXPath));
        waitElementToBeClickable(middleNameField);
        middleNameField.click();
        middleNameField.sendKeys(middleName);

        assertEquals("Отчетство введено не верно",
                middleName, middleNameField.getAttribute("value"));

        // Имя и фамилия на карте - ТОЛЬКО ПРОВЕРКА БЕЗ ВВОДА
        String nameOnCardXFieldPath = "//input[@id='odc-personal__cardName']";
        WebElement nameOnCardField = driver.findElement(By.xpath(nameOnCardXFieldPath));
        assertEquals("Имя на карте введно не верно",
                cardName, nameOnCardField.getAttribute("value"));

        // Дата рождения
        String birthDateXPath = "//input[@id='odc-personal__birthDate']";
        WebElement birthDate = driver.findElement(By.xpath(birthDateXPath));
        waitElementToBeClickable(birthDate);
        birthDate.click();
        birthDate.sendKeys("01.12.2001");

        assertEquals("Дата введена не верно", "01.12.2001", birthDate.getAttribute("value"));

        // E-mail
        String emailFieldXPath = "//input[@id='odc-personal__email']";
        WebElement emailField = driver.findElement(By.xpath(emailFieldXPath));
        waitElementToBeClickable(emailField);
        emailField.click();
        emailField.sendKeys("tyler98765@yandex.ru");

        assertEquals("Поле e-mail заполнено не верно",
                "tyler98765@yandex.ru", emailField.getAttribute("value"));

        // Мобильный телефон
        String mobilePhoneXPath = "//input[@id='odc-personal__phone']";
        WebElement mobilePhone = driver.findElement(By.xpath(mobilePhoneXPath));
        waitElementToBeClickable(mobilePhone);
        mobilePhone.click();
        mobilePhone.sendKeys("9991234455");

        assertEquals("Мобильный телефон введен не верно",
                "+7 (999) 123-44-55", mobilePhone.getAttribute("value"));
        
        // 10. Нажать «Далее»
        String continueButtonXPath = "//span[.='Далее']";
        WebElement continueButton = driver.findElement(By.xpath(continueButtonXPath));
        waitElementToBeClickable(continueButton);
        continueButton.click();

        // 11. Проверить, что появилось сообщение именно у незаполненных полях – «Обязательное поле»
        // Проверка сообщения "Обязательное поле" под полем "Серия"
        String seriesPassportNoEnterXPath = "//label[contains(text(), " +
                "'Серия')]/following-sibling::div[contains(text(), 'Обязательное поле')]";
        WebElement seriesPassportNoEnter = driver.findElement(By.xpath(seriesPassportNoEnterXPath));
        waitUtilElementToBeVisible(seriesPassportNoEnter);

        assertEquals("Поле не соответствует ожидаемому",
                "Обязательное поле", seriesPassportNoEnter.getText());

        // Проверка сообщения "Обязательное поле" под полем "Номер"
        String numbersPassportNoEnterXPath = "//label[contains(text(), " +
                "'Номер')]/following-sibling::div[contains(text(), 'Обязательное поле')]";
        WebElement numbersPassportNoEnter = driver.findElement(By.xpath(numbersPassportNoEnterXPath));
        waitUtilElementToBeVisible(numbersPassportNoEnter);

        assertEquals("Поле не соответствует ожидаемому",
                "Обязательное поле", numbersPassportNoEnter.getText());

        // Проверка сообщения "Обязательное поле" под полем "Дата выдачи"
        String datePassportNoEnterXPath = "//label[contains(text(), 'Дата выдачи')]" +
                "/following-sibling::div[contains(text(), 'Обязательное поле')]";
        WebElement datePassportNoEnter = driver.findElement(By.xpath(datePassportNoEnterXPath));
        waitUtilElementToBeVisible(datePassportNoEnter);

        assertEquals("Поле не соответствует ожидаемому",
                "Обязательное поле", datePassportNoEnter.getText());

        Thread.sleep(5000);

    }

    /*
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
