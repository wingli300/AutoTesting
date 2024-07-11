/*
 * (C) Copyright 2021 Boni Garcia (https://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.bonigarcia.wdm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.github.bonigarcia.wdm.SpringBootDemoApp;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;

/**
 * Test using a local web application based on spring-boot.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
@Listeners({ZTestReport.class})
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringBootDemoApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringChromeTest {

    WebDriver driver;

    @LocalServerPort
    int serverPort;

    @BeforeAll
    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    @BeforeTest
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    @AfterTest
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(description="测试DEMO")
    @Parameters({"username"})
    void test(String username) {

        // Open system under test
        driver.get("https://coding.imooc.com/class/303.html");
        System.out.println("username="+username);
//        // Verify first page title
//        String firstPageTitle = driver.getTitle();
//        String expectedFirstPageTitle = "Spring Boot Test - Page 1";
//        assertEquals(expectedFirstPageTitle, firstPageTitle);
//
//        // Click on link
//        driver.findElement(By.linkText("another")).click();
//
//        // Verify second page caption
//        String secondPageCaption = driver.findElement(By.id("caption"))
//                .getText();
//        String expectedSecondPageTitle = "Other page";
//        assertEquals(expectedSecondPageTitle, secondPageCaption);
        try {
            Thread.sleep(1000);
            WebElement element = driver.findElement(By.id("js-signin-btn"));
            element.click();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
