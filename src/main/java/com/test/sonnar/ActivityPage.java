package com.test.sonnar;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.apache.log4j.Logger.getLogger;

public class ActivityPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityPage.class);
    public ActivityPage openActivity(){
        return this;
    }
    public ActivityPage clickActivity() {
        SelenideElement myActivity = $(By.xpath("//a[text()=\"Activity\"]"));
        myActivity.click();
        sleep(5000);
        return this;
    }
    public ActivityPage selectChartType(String name){
        $(By.xpath("//span[@class=\"Select-value-label\" and @role=\"option\"]"))
                .click();
        $(By.xpath("//div[@class=\"Select-menu-outer\"]//div[text()=\"" + name+ "\"]"))
                .click();
        sleep(5000);
        return this;
    }
    public ActivityPage printStatus(){
        LOGGER.info("printStatus Start");
        //ul[@class="project-activity-versions-list"]/li[1]//span[@class="analysis-version"]
        SelenideElement span =$(By.xpath("//ul[@class=\"project-activity-versions-list\"]/li[1]//span[@class=\"analysis-version\"]"));
        LOGGER.info("Version :{}", span.getText());
        SelenideElement date = $(By.xpath("//ul[@class=\"project-activity-versions-list\"]/li[1]//ul[@class=\"project-activity-days-list\"]/li[1]/div[@class=\"project-activity-date\"]/span"));
        LOGGER.info("Date :{}", date.getText());
        SelenideElement time =$(By.xpath("//ul[@class=\"project-activity-versions-list\"]/li[1]//ul[@class=\"project-activity-days-list\"]/li[1]/ul[@class=\"project-activity-analyses-list\"]//time"));
        LOGGER.info("Time :{}", time.getText());
        SelenideElement status = $(By.xpath("//ul[@class=\"project-activity-versions-list\"]/li[1]//ul[@class=\"project-activity-days-list\"]/li[1]/ul[@class=\"project-activity-analyses-list\"]//strong"));
        LOGGER.info("Status: {}", status.getText());
        LOGGER.info("=======");
        return this;
    }
    public ActivityPage checkPage(){
        $("[class='Select-placeholder']").should(Condition.exist);
        $ ("input[class='date-input-control-input'][placeholder='Start Date']")
                .should(Condition.exist);
        $ ("input[class='date-input-control-input'][placeholder='End Date']")
                .should(Condition.exist);
        SelenideElement button = $(By.xpath("//button[text()='Reset dates']"));
        button.shouldHave(Condition.cssClass("disabled"));
        button.shouldHave(Condition.attribute("aria-disabled","true"));

        return this;
    }
}
