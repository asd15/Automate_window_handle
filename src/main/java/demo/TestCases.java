package demo;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
//Selenium Imports
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
///


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01() throws IOException{
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open");
        String currentWindow = driver.getWindowHandle();
        WebElement frame = driver.findElement(By.xpath("//*[@id='iframeResult']"));
        driver.switchTo().frame(frame);
        driver.findElement(By.xpath("//button[text()='Try it']")).click();
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if(!window.equals(currentWindow)){
                driver.switchTo().window(window);
            }
        }
        String url= driver.getCurrentUrl();
        System.out.println(" Current URL :"+ url);
        String title= driver.getTitle();
        System.out.println("Current Title :"+ title);
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File("c://screenshot.png");
        FileUtils.copyFile(SrcFile, DestFile);
        driver.close();
        
        driver.switchTo().window(currentWindow);
 
    }


}
