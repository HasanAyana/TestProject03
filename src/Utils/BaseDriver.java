package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDriver {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions aksiyonlar;


    @BeforeClass
    public void Baslat(){

        Logger logger=Logger.getLogger("");//sistemdeki bütün logları üreten objeye servise ulaştım
        logger.setLevel(Level.SEVERE); //sadece errorları gösterir.
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.manage().deleteAllCookies();
        aksiyonlar=new Actions(driver);
        Duration dr=Duration.ofSeconds(30);
        driver.manage().timeouts().pageLoadTimeout(dr);//Sadece tüm sayfalanın kodlarını bilgisayarınıza inmesi süresyile ilgili
        //bu eklenmezse Selenium sayfa yüklenene kadar(sonsuza) bekler: 30 sn süresince sayfanın bekle yüklenmezse hata verir.

        driver.manage().timeouts().implicitlyWait(dr); //Elementin load edilmesi için geçmesi gereken max süre


        wait=new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    @AfterClass
    public void Kapat(){

        Tools.Bekle(3);
        driver.quit();
    }

}



