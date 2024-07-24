package Tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainTab {

    WebDriver driver;
   @Test
    public void openNewTab() throws InterruptedException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.globalsqa.com/demo-site/frames-and-windows/");
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        String windowHanldeMainTab = driver.getWindowHandle();
        driver.findElement(By.linkText("Click Here")).click();
        Set<String> handles = driver.getWindowHandles();
        for (String handle :handles){
            if (!handle.equals(windowHanldeMainTab)){
                driver.switchTo().window(handle);
                driver.findElement(By.id("iFrame")).click();
                driver.switchTo().frame("globalSqa");
                Thread.sleep(3000);
                driver.findElement(By.xpath("//span[@id='current_filter']")).click();
                driver.findElement(By.xpath("//div[@data-option-value='.softwaretesting']")).click();
                driver.findElement(By.xpath("//img[@data-image-title=\"ManalTestingTraining\"]")).click();
                driver.findElement(By.xpath("//span[text()='Manual Testing Training']")).isDisplayed();
                driver.switchTo().defaultContent();
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,250)", "");
                driver.findElement(By.xpath("(//span[@class='link_span'])[14]")).click();
                driver.close();
            }
        }
        driver.switchTo().window(windowHanldeMainTab);
        driver.navigate().to("https://demo.automationtesting.in/Alerts.html");
        driver.findElement(By.xpath("//a[@href=\"#CancelTab\"]")).click();
        driver.findElement(By.xpath("//div[@id=\"CancelTab\"]/button")).click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        String actual = driver.findElement(By.id("demo")).getText();
        Assert.assertEquals(actual,"You Pressed Cancel");
        driver.close();
        driver.quit();


   }
}
