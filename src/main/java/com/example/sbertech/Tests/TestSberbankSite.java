package com.example.sbertech.Tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestSberbankSite extends Assert {
    private WebDriver driver;

    @BeforeTest
    public void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tatyana\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.sberbank.ru/ru/person");
    }

    @AfterTest
    public void tearDown() {
        if(driver != null)
            driver.quit();
    }

    @Test
    public void testSocialButtons() {
        String region_change = "//*[@id=\"main\"]//*[@class=\"header \"]//*[@class=\"hd-ft-region\"]/div";
        String region_name = "//*[@role=\"dialog\"]//*[@class=\"kit-input\"]/input";
        String regions_list = "//*[@class=\"kit-row hd-ft-region__list\"]//*[contains(@class,\"kit-link\")]";
        String expected_region = "Нижегородская область";
        String footer_xpath = "//*[@class=\"footer\"]";
        String social_xpath = footer_xpath + "//*[@class=\"footer__social_item\"]/a";

        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.findElement(By.xpath(region_change)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(region_name)));
        driver.findElement(By.xpath(region_name)).sendKeys(expected_region);
        List<WebElement> regions = driver.findElements(By.xpath(regions_list));
        regions.stream().filter(e -> expected_region.equals(e.getText())).limit(1).forEach(WebElement::click);

        assertEquals(expected_region, driver.findElement(By.xpath(region_change)).getText());

        WebElement footer = driver.findElement(By.xpath(footer_xpath));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", footer);

        List<WebElement> socialGroups = driver.findElements(By.xpath(social_xpath));
        assert !socialGroups.isEmpty();
    }

}
