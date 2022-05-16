package com.test.sonnar;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    public MainPage openPage (){
        open("https://sonarqube.dev-k8s.comparus.cloud/");
        sleep(5000);
        return this;
    }
    public MainPage login (String userName, String password){
//        class="identity-provider-link"
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        if(!currentUrl.contains("sessions/new")){
            return this;
        }
        SelenideElement link = $(By.className("identity-provider-link"));
        link.click();
        sleep(2000);
        SelenideElement loginField = $(By.id("username"));
        loginField.setValue(userName);
        SelenideElement passwordField = $(By.id("password"));
        passwordField.setValue(password);
        passwordField.submit();
        sleep(5000);
        return this;
    }
    public MainPage checkMenu (){
        SelenideElement menuBar = $(By.className("global-navbar-menu"));
        ElementsCollection menus = menuBar.$$(By.tagName("a"));
        menus.get(0).shouldHave(Condition.cssClass("active"));
        List<String> list = menus.texts();
        Assert.assertEquals(list.get(0),"Projects");
        Assert.assertEquals(list.get(1),"Issues");
        Assert.assertEquals(list.get(2),"Rules");
        Assert.assertEquals(list.get(3),"Quality Profiles");
        Assert.assertEquals(list.get(4),"Quality Gates");
        return this;
    }
    public MainPage checkTotal(String expected){
        SelenideElement total = $(By.id("projects-total"));
        total.shouldHave(Condition.matchesText(expected));
        return this;
    }
    public MainPage searchProject(String projectName){
          SelenideElement input = $x("//div[@class=\"page-header\"]//input[@class=\"search-box-input\"]");;
          input.setValue(projectName);
          sleep(2000);
          return this;
    }
    public IssuesPage openIssuePage() {
        SelenideElement linkIssue =$(By.xpath("//*[@class=\"global-navbar-menu\"]//a[text()=\"Issues\"]"));
        linkIssue.click();
        sleep(2000);
        return new IssuesPage();
    }
    public MainPage checkTotalNotZero(){
        SelenideElement total = $(By.id("projects-total"));
        total.shouldHave(Condition.not(Condition.matchesText("0")));
        return this;
    }
    public MainPage checkProjectAndClick(String name){
        SelenideElement projectLink = $(By.xpath("//a[text()=\"registry\"]"));
        projectLink.should(Condition.exist)
                .click();
        sleep(2000);
       return this;
    }

}
