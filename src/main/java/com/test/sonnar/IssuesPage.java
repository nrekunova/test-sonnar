package com.test.sonnar;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class IssuesPage {
    public IssuesPage openIssue(){
        return this;
    }
    public IssuesPage clickMyIssues(){
        SelenideElement myIssues = $(By.xpath("//button[text()=\"My Issues\"]"));
        myIssues.click();
        sleep(5000);
        //*[@class="empty-search"]
        $(By.className("empty-search")).should(Condition.exist);
//        Type
        $(By.xpath("//a[@data-facet=\"BUG\"]//span[@class=\"facet-stat\"]"))
                .shouldHave(Condition.matchesText("0"));
        $(By.xpath("//a[@data-facet=\"VULNERABILITY\"]//span[@class=\"facet-stat\"]"))
                .shouldHave(Condition.matchesText("0"));
        $(By.xpath("//a[@data-facet=\"CODE_SMELL\"]//span[@class=\"facet-stat\"]"))
                .shouldHave(Condition.matchesText("0"));
//        Severity
        $(By.xpath("//a[@data-facet=\"BLOCKER\"]//span[@class=\"facet-stat\"]"))
                .shouldHave(Condition.matchesText("0"));

        return this;
    }

}
