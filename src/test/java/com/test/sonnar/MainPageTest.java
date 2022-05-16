package com.test.sonnar;

import org.testng.annotations.Test;

public class MainPageTest {
    @Test
    public void t1_openPageTest(){
        MainPage page = new MainPage();
        page.openPage()
                .login("rekunova","1234567")
                .checkMenu()
                .checkTotal("284")
                .searchProject("Reg")
                .checkTotal("6");
    }
    @Test
    public void t2_issuePageTest (){
        MainPage page = new MainPage();
        page.openPage()
                .login("rekunova","1234567")
                .openIssuePage()
                .clickMyIssues();
    }

    @Test
    public void t3_projetTest(){
        MainPage page = new MainPage();
        page.openPage()
                .login("rekunova","1234567")
                .searchProject("registry")
                .checkTotalNotZero()
                .checkProjectAndClick("registry");
        new ActivityPage()
                .clickActivity()
                .selectChartType("Coverage")
                .selectChartType("Duplications")
                .selectChartType("Issues")
                .checkPage()
                .printStatus();
    }
}
