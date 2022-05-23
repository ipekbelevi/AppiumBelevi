import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.security.Key;
import java.time.Duration;
import java.util.List;
import java.util.Random;


public class StepImp extends BaseTest {

    private static MobileElement element;

    @Step("<second> kadar bekle")
    public void waitForsecond(int second) throws InterruptedException {
        Thread.sleep(1000 * second);
        logger.info(second + "beklendi ");
    }

    @Step("<Key> ID'li elemente tıkla")
    public void clickElementByid(String Key) throws InterruptedException {
        appiumDriver.findElement(By.id(Key)).click();
        waitForsecond(5);
        logger.info(Key + " elementine tıklandı");
    }

    @Step("<Key> ID'li elemente <keywordc> değerini yaz")
    public void SendkeyElementByid(String Key, String keywordc) {
        appiumDriver.findElement(By.id(Key)).sendKeys(keywordc);
        logger.info(Key + "Elementine tıklandı ve değer girildi " + keywordc);

    }

    @Step("<Key> xpath'li elemente tıkla")
    public void clickElementByxpath(String Key) throws InterruptedException {
        appiumDriver.findElement(By.xpath(Key)).click();
        waitForsecond(5);
        logger.info(Key + "Elementine tıklandı ");
    }

    @Step("<Key> xpath'li elemente <keywordc> değerini yaz")
    public void SendkeyElementByxpath(String Key, String keywordc) {
        appiumDriver.findElement(By.xpath(Key)).sendKeys(keywordc);
        logger.info(Key + "Elementine tıklandı ve değer girildi: " + keywordc);

    }

    @Step("Sayfayı yukarı kaydır")
    public void yukariKaydirma() {
        Dimension dims = appiumDriver.manage().window().getSize();
        PointOption pointOptionStart, pointOptionEnd;
        int edgeBorder = 10;
        final int PRESS_TIME = 200; // ms
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
        new TouchAction(appiumDriver).press(pointOptionStart)
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME))).moveTo(pointOptionEnd).release().perform();
        logger.info("Sayfa yukarı kaydırıldı.");
    }

    @Step("<Byxpath> xpath bul ve <keyword> değerini kontrol et")
    public void textControlByxpath(String xpath, String keyword) {
        element = appiumDriver.findElement(By.xpath(xpath));
        logger.info("Text değeri " + element.getText());
        Assert.assertTrue("Text değeri bulunmamadı ", element.getText().equals(keyword));
    }

    @Step("<ByID> ID bul ve <keyword> değerini kontrol et")
    public void textControlByID(String Key, String keyword) {
        element = appiumDriver.findElement(By.id(Key));
        logger.info("Text değeri " + element.getText());
        Assert.assertTrue("Text değeri bulunmamadı ", element.getText().equals(keyword));
    }

    @Step("<Key> Rastgele bir ürün seçimi yapıldı.")
    public void randomSecim(String Key) throws InterruptedException {
        List<MobileElement> products = appiumDriver.findElements(By.id(Key));
        int maxDeger = products.size();
        Random rnd = new Random();
        int randomInt = rnd.nextInt(maxDeger);
        products.get(randomInt).click();
        waitForsecond(5);
        logger.info("Rastgele seçilen elemente tıklandı.");
    }

    @Step("geri don butonuna tıkla")
    public void backButton() {
        ((AndroidDriver) appiumDriver).pressKey(new KeyEvent(AndroidKey.BACK));
    }

    @Step("<Key> element kontrol edilir.")
    public boolean isElementVisibleByid(String key) {
        try
        {
            element = appiumDriver.findElement(By.id(key));
            logger.info(key + " elementi görünür.");
            Assert.assertTrue("Element Görünürdür",element.isDisplayed());
            return true;
        } catch (Exception e) {
            logger.error(key + " elementi bulunamadı.");
            Assert.assertTrue(" Element bulunamadı.",element.isDisplayed());
            return false;
        }
    }
}

