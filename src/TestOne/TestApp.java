package TestOne;

import Utils.BaseDriver;
import Utils.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestApp extends BaseDriver {
    @Test(priority = 1)
    void register() throws InterruptedException {

        driver.get("https://demowebshop.tricentis.com/");


        WebElement register = driver.findElement(By.linkText("Register"));
        register.click();
        aksiyonlar.click(driver.findElement(By.id("gender-male")));
        aksiyonlar.click(driver.findElement(By.id("FirstName"))).sendKeys("hasan");
        aksiyonlar.click(driver.findElement(By.id("LastName"))).sendKeys("ayana");
        aksiyonlar.click(driver.findElement(By.id("Email"))).sendKeys("grup6@gmail.com");
        aksiyonlar.click(driver.findElement(By.id("Password"))).sendKeys("abc123");
        aksiyonlar.click(driver.findElement(By.id("ConfirmPassword"))).sendKeys("abc123");
        aksiyonlar.click(driver.findElement(By.id("register-button"))).build().perform();

        WebElement textCheck = driver.findElement(By.cssSelector("[class='validation-summary-errors']>ul>li"));
        Assert.assertEquals("The specified email already exists", textCheck.getText());

    }

    @Test(priority = 2)
    void reRegister() {

        driver.get("https://demowebshop.tricentis.com/");


        WebElement register = driver.findElement(By.linkText("Register"));
        register.click();
        aksiyonlar.click(driver.findElement(By.id("gender-male")));
        aksiyonlar.click(driver.findElement(By.id("FirstName"))).sendKeys("hasan");
        aksiyonlar.click(driver.findElement(By.id("LastName"))).sendKeys("ayana");
        aksiyonlar.click(driver.findElement(By.id("Email"))).sendKeys("grup6@gmail.com");
        aksiyonlar.click(driver.findElement(By.id("Password"))).sendKeys("abc123");
        aksiyonlar.click(driver.findElement(By.id("ConfirmPassword"))).sendKeys("abc123");
        aksiyonlar.click(driver.findElement(By.id("register-button"))).build().perform();
        WebElement textCheck = driver.findElement(By.cssSelector("[class='validation-summary-errors']>ul>li"));

        Assert.assertEquals("The specified email already exists", textCheck.getText());

    }

    @Test(priority = 3)
    void login() {
        driver.get("https://demowebshop.tricentis.com/");

        aksiyonlar.click(driver.findElement(By.linkText("Log in"))).perform();
        aksiyonlar.click(driver.findElement(By.id("Email"))).sendKeys("grup6@gmail.com");
        aksiyonlar.click(driver.findElement(By.id("Password"))).sendKeys("abc123");
        aksiyonlar.click(driver.findElement(By.className("login-button"))).build().perform();
        WebElement loginCheck = driver.findElement(By.linkText("grup6@gmail.com"));
        Assert.assertEquals("grup6@gmail.com", loginCheck.getText());
        aksiyonlar.click(driver.findElement(By.linkText("Log out"))).perform();

    }

    @Test(priority = 4)
    void negativeLogin() {
        driver.get("https://demowebshop.tricentis.com/");

        aksiyonlar.click(driver.findElement(By.linkText("Log in"))).perform();
        aksiyonlar.click(driver.findElement(By.id("Email"))).sendKeys("grup6@gmail.com");
        aksiyonlar.click(driver.findElement(By.id("Password"))).sendKeys("abc12");
        aksiyonlar.click(driver.findElement(By.className("login-button"))).build().perform();
        WebElement loginCheck = driver.findElement(By.cssSelector("[class='validation-summary-errors']>ul>li"));
        Assert.assertTrue(loginCheck.getText().contains("The credentials provided are incorrect"));

    }
    @Test(priority = 5)
    void orderTest() throws InterruptedException {
        driver.get("https://demowebshop.tricentis.com/");

        aksiyonlar.click(driver.findElement(By.linkText("Log in"))).perform();
        aksiyonlar.click(driver.findElement(By.id("Email"))).sendKeys("grup6@gmail.com");
        aksiyonlar.click(driver.findElement(By.id("Password"))).sendKeys("abc123");
        aksiyonlar.click(driver.findElement(By.className("login-button"))).build().perform();

        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,200)");

        aksiyonlar.click(driver.findElement(By.cssSelector("[class='product-item'][data-productid='31']"))).build().perform();
        aksiyonlar.click(driver.findElement(By.id("add-to-cart-button-31"))).build().perform();
        aksiyonlar.click(driver.findElement(By.className("cart-qty"))).build().perform();
        WebElement chartCheck= driver.findElement(By.className("product-name"));
        Assert.assertTrue(chartCheck.getText().contains("14.1-inch Laptop"));
        //wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.id("termsofservice"))));
        aksiyonlar.click(driver.findElement(By.id("termsofservice"))).build().perform();
        aksiyonlar.click(driver.findElement(By.id("checkout"))).build().perform();

        WebElement country= driver.findElement(By.id("BillingNewAddress_CountryId"));
        Select sl=new Select(country);
        sl.selectByValue("77");
        aksiyonlar.click(driver.findElement(By.id("BillingNewAddress_City"))).sendKeys("istanbul");
        //aksiyonlar.sendKeys(driver.findElement(By.id("BillingNewAddress_City")),"istanbul");
        aksiyonlar.click(driver.findElement(By.id("BillingNewAddress_Address1"))).sendKeys("adres1");
        aksiyonlar.click(driver.findElement(By.id("BillingNewAddress_ZipPostalCode"))).sendKeys("1234");
        aksiyonlar.click(driver.findElement(By.id("BillingNewAddress_PhoneNumber"))).sendKeys("056799740");
        aksiyonlar.click(driver.findElement(By.cssSelector("[class='button-1 new-address-next-step-button'][onclick='Billing.save()']"))).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("[class='button-1 new-address-next-step-button'][onclick='Shipping.save()']"))));
        aksiyonlar.click(driver.findElement(By.cssSelector("[class='button-1 new-address-next-step-button'][onclick='Shipping.save()']"))).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("[class='button-1 shipping-method-next-step-button']"))));
        aksiyonlar.click(driver.findElement(By.cssSelector("[class='button-1 shipping-method-next-step-button']"))).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("[class='button-1 payment-method-next-step-button']"))));
        aksiyonlar.click(driver.findElement(By.cssSelector("[class='button-1 payment-method-next-step-button']"))).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("[class='button-1 payment-info-next-step-button']"))));
        aksiyonlar.click(driver.findElement(By.cssSelector("[class='button-1 payment-info-next-step-button']"))).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("[class='button-1 confirm-order-next-step-button']"))));
        aksiyonlar.click(driver.findElement(By.cssSelector("[class='button-1 confirm-order-next-step-button']"))).build().perform();

        System.out.println("");
        WebElement productCheck= driver.findElement(By.className("title"));
        Assert.assertFalse(productCheck.getText().contains("Your order has been successfully processed!"));

    }
}