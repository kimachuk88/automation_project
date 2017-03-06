package com.epam.gmail.pages;

import com.epam.framework.controls.extension.Button;
import com.epam.framework.controls.extension.Label;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Uliana Pizhanska on 06/03/2017.
 */
public class SentMessagesPage extends BasePage{
    @FindBy(xpath = "//*[text() ='View message']")
    private Button btnViewMessage;

    @FindBy(xpath = "//h2[@class = 'hP']")
    private Label subTitle;

    public void clickOnViewMessage(){
        waitForControl(btnViewMessage);
        btnViewMessage.click();
    }

    public String getSubTitle(){
        return Label.getText(subTitle);
    }
}
