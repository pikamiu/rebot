package com.auto.rebot;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * <p>简要说明...</p>
 *
 * @author Draher
 * @version 1.0
 * @since 2020/2/24 23:41
 */
public class SpiderMan {

    public static void main(String[] args) throws InterruptedException {
        // 设置启动参数
        String driverPath = System.getProperty("user.dir") + "\\deploy\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        // 访问目标网站
        WebDriver driver = new ChromeDriver();
        driver.get("http://120.78.80.19:8086/swagger-ui.html");

        // 浏览器全屏化
        driver.manage().window().maximize();

        // 展开搜索控制
        driver.findElement(By.id("endpointListTogger_搜索控制")).click();
        Thread.sleep(2000);

        // 展开搜索报纸新闻信息
        driver.findElement(By.xpath("//*[@id=\"搜索控制_searchPaperUsingPOST\"]/div[1]/h3/span[2]/a")).click();
        Thread.sleep(2000);

        // 滚动条滚动到参数输入框
        String script = "return arguments[0].scrollIntoView();";
        WebElement searchElement = driver.findElement(By.name("searchDTO"));
        ((JavascriptExecutor) driver).executeScript(script, searchElement);
        Thread.sleep(500);

        // 输入搜索关键字
        searchElement.sendKeys("{\n" +
                "  \"keyword\": \"天气\"\n" +
                "}");

        // 执行try it out
        driver.findElement(By.xpath("//*[@id='搜索控制_searchPaperUsingPOST_content']//input[@class='submit'][@type='button']")).click();
        Thread.sleep(2000);

        // 滚动到执行结果
        WebElement respElement = driver.findElement(By.xpath("//*[@id=\"搜索控制_searchPaperUsingPOST_content\"]/div[3]/div[3]"));
        ((JavascriptExecutor) driver).executeScript(script, respElement);
        Thread.sleep(1000);

        // 执行弹框
        ((JavascriptExecutor) driver).executeScript("alert('wonderful!!!， you really are a genius!!');");
        Thread.sleep(5000);

        // 关闭弹框
        Alert alert = new WebDriverWait(driver, 5).until(ExpectedConditions.alertIsPresent());
        alert.accept();

        // 隐藏搜索控制
        driver.findElement(By.id("endpointListTogger_搜索控制")).click();
        Thread.sleep(2000);

        // 退出
        driver.quit();
    }
}
