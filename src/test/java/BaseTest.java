import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class BaseTest {
    protected static AppiumDriver<MobileElement> appiumDriver;
    static Logger logger = Logger.getLogger(BaseTest.class);
    protected boolean localAndroid = true;

    public BaseTest() {
        String log4jConfigFile = System.getProperty("user.dir") + File.separator + "log4j.properties";
        PropertyConfigurator.configure(log4jConfigFile);
    }

    @BeforeScenario
    public void Setup() throws MalformedURLException {
        if (localAndroid) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "android");
            desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.ozdilek.ozdilekteyim");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.ozdilek.ozdilekteyim.MainActivity");
            desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, false);
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
            desiredCapabilities.setCapability("unicodeKeyboard", false);
            desiredCapabilities.setCapability("resetKeyboard", false);
            URL url = new URL("http://0.0.0.0:4723/wd/hub");
            appiumDriver = new AndroidDriver(url, desiredCapabilities);
            logger.info("Android başlatıldı.");
        }
    }

    @AfterScenario
    public void AfterSenerio() {
        appiumDriver.quit();
        logger.warn("Android kapatıldı.");
    }

}