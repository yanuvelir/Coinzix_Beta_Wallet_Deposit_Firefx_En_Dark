package Main_code;


import Wallet.Wallet_Dep_BTC;
import Wallet.Wallet_Dep_ETH;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static java.time.Duration.ofSeconds;

@Test
public class MainCode<Walletpackage> {

    // Инициализируем Webdriver при помощи метода public Static, для автоматического использования в других классах
    public static WebDriver driver;

    @Parameters({"StartUrl"})

    public void mainCode(String StartUrl) throws InterruptedException, IOException, UnsupportedFlavorException {

        System.out.println("Wallet row \"Deposit\" form verification, validation");

//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        // Переход на начальную страницу
        driver.get(StartUrl);

        Thread.sleep(2500);
        // Развертывание страницы на весь экран
        driver.manage().window().maximize();

        Thread.sleep(2500);
        WebElement LogBtn = driver.findElement(By.xpath("//a[contains(text(),'Log In')]"));
        LogBtn.click();

        Thread.sleep(2500);
        //Находим и вводи Email
        WebElement email = driver.findElement(By.id("email"));

        email.sendKeys("coinzix-automation@intary.net");

        //Находим и вводим Password
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("Vinnitsa-2022");

        // //Находим и нажимаем клавишу Log in
        WebElement logBtn = driver.findElement(By.xpath
                ("//button[@class='submit__button']"));
        logBtn.click();

        try {
            //Ожидаем появление капчи, и в случае когда она появилась решаем ее не более  минут
            WebElement instExchBtn = (new WebDriverWait(driver, ofSeconds(180)))
                    .until(ExpectedConditions
                            .presenceOfElementLocated(By.xpath("//button[contains(text(),'Instant exchange')]")));

        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread.sleep(2500);

        Wallet_Dep_BTC wallBtc = new Wallet_Dep_BTC();
        wallBtc.case_1("https://beta.coinzix.com/wallet/history");

        Wallet_Dep_ETH wallEth = new Wallet_Dep_ETH();
        wallEth.case_2("https://beta.coinzix.com/wallet/history");
//
//        Account acc = new Account();
//        acc.case_17("https://coinzix.com/account");
//
//        Security sec = new Security();
//        sec.case_18("https://coinzix.com/account/security");

//        Verification ver = new Verification();
//        ver.case_19("https://coinzix.com/account/verification");

//        Referral_program refPr = new Referral_program();
//        refPr.case_20("https://coinzix.com/account/referral");
//
//        Vip_level vip = new Vip_level();
//        vip.case_21("https://coinzix.com/account/vip");
//
//        Api_management api = new Api_management();
//        api.case_22("https://coinzix.com/account/api-management");
//
//        Log_out out = new Log_out();
//        out.case_23("https://coinzix.com/login");
//
//        Login log = new Login();
//        log.case_12("https://coinzix.com/login");
//
//        SignUp sign = new SignUp();
//        sign.case_24("https://coinzix.com/sign-up");
//
//        Theme Thm = new Theme();
//        Thm.case_15("https://coinzix.com/sign-up");
//
//        Language Lng = new Language();
//        Lng.case_16("https://coinzix.com/ro/sign-up");


        driver.quit();

    }

}
