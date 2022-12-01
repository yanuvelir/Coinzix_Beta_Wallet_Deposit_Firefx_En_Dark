package Wallet;

import Main_code.MainCode;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

@Test
public class Wallet_Dep_BTC {

    WebDriver driver = MainCode.driver;

        @Parameters({"UrlAssert"})

        public void case_1(String UrlAssert) throws InterruptedException, IOException, UnsupportedFlavorException {

            System.out.println("Wallet, Deposit Address - Case1");

            Thread.sleep(2500);
            //Эмитируем передвижения курсора в центр кнопки Wallet
            WebElement Wallet = driver.findElement(By.xpath
                    ("//a[contains(text(),'Wallet')]"));
            Wallet.click();

            Thread.sleep(2500);
            //Эмитируем передвижения курсора в центр кнопки Wallet
            WebElement btcDepositBtn = driver.findElement(By.xpath
                    ("//div[@id='anchor-BTC']//button[contains(text(),'Deposit')]"));
            //btcDepositBtn.click();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click()", btcDepositBtn);

            Thread.sleep(2000);
            WebElement btcDepAdrss = driver.findElement
                    (By.xpath("//div[@class='dep-address__block address']"));
            //span[contains(text(),'*')]
            String assertbtcDepAdrss =  btcDepAdrss.getText();
            System.out.println(btcDepAdrss.getText());

            try {
                //Добавлен сравнение адреса. При заданых параметрах логина и пароля, адрес страницы меняется
                Assert.assertEquals("Deposit Address\n" +
                        "tb1qdlcssd5d4svwl0zmt9xmtljypnehf3dfrd555p\n" +
                        "Copy", assertbtcDepAdrss);
                                         //tb1qdlcssd5d4svwl0zmt9xmtljypnehf3dfrd555p
            } catch  (Throwable t) {
                System.out.println("Wallet case1");
                System.out.println(t.getMessage());
            }

            Thread.sleep(500);
            WebElement copyBtn = driver.findElement
                    (By.xpath("//div[@class='dep__row']//button[@class='light-page__link']"));
            copyBtn.click();

            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

            Object data = clipboard.getData(DataFlavor.stringFlavor);

            String copiedAdress = (String) data;
            System.out.println("By Copy button Copied adress is: " + copiedAdress);

            try {
                //Добавлен сравнение адреса. При заданых параметрах логина и пароля, адрес страницы меняется
                Assert.assertEquals(copiedAdress, "tb1qdlcssd5d4svwl0zmt9xmtljypnehf3dfrd555p");
                //tb1qdlcssd5d4svwl0zmt9xmtljypnehf3dfrd555p
            } catch  (Throwable t) {
                System.out.println("Wallet case1");
                System.out.println(t.getMessage());
            }

            Thread.sleep(2000);
            //Находим доску продажа/торговля и верифицируем ее наличие
            try {
                boolean qr = driver.findElement(By.xpath
                        ("//div[@class='action__container']//img[@class='lazyLoad isLoaded']")).isDisplayed();

                if (qr) {
                    System.out.println("*** QR code is displayed ***");
                }
            } catch (NoSuchElementException e)
            {
                System.out.println("*** QR code is not displayed ***");
            }

            Thread.sleep(500);
            WebElement deposHistLink = driver.findElement
                    (By.xpath("//div[@class='info__block']//a[@href='/wallet/history']"));
            deposHistLink.click();

            Thread.sleep(3500);
            //Копируем URL текущей страницы
            String GetCurrUrl = driver.getCurrentUrl();
            System.out.println(GetCurrUrl);

            try {
                //Добавлен сравнение адреса. При заданых параметрах логина и пароля, адрес страницы меняется
                //Assert.assertEquals(GetCurrUrl, "https://coinzix-stage1.intary.net/ro/spot/trading/BTCUSDT");
                Assert.assertEquals(GetCurrUrl, UrlAssert);
            } catch  (Throwable t) {
                System.out.println("Wrong Page");
                System.out.println(t.getMessage());
            }
        }

    }