package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Tools {
    public static void Bekle(int saniye) {
        try {
            Thread.sleep(saniye * 100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void subscribeValidation(){
        WebElement msg = BaseDriver.driver.findElement(By.cssSelector("[class='alert alert-success alert-dismissible']"));
        Assert.assertTrue(msg.getText().toLowerCase().contains("success"));
    }
}