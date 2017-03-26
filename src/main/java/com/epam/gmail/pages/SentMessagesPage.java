package com.epam.gmail.pages;

import com.epam.framework.controls.extension.Button;
import com.epam.framework.controls.extension.CheckBox;
import com.epam.framework.controls.extension.Label;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Uliana Pizhanska on 06/03/2017.
 */
public class SentMessagesPage extends BasePage{
    @FindBy(xpath = "//h2[@class = 'hP']")
    private Label subTitle;

    @FindBy(id = "link_vsm")
    private Button btnViewMessage;


    @FindBy(xpath = "(//div[@dir='ltr'])[1]/div")
    private CheckBox checkAll;

    @FindBy(xpath = "//div[contains(text(),'Move to Inbox')]")
    private Button btnMoveToInbox;

    @FindBy(xpath = "//span[contains(text(),'Test Message')]")
    private Button btnView;

    public void clickOnMoveToInbox(){
        waitElementToClick(btnMoveToInbox);
        btnMoveToInbox.click();
    }

    public void checkMessages(){
        waitForControl(checkAll);
        checkAll.check();
    }

    public void clickOnViewMessage(){
        waitForControl(btnViewMessage);
        btnViewMessage.click();
    }

    public void clickOnBtnView(){
        waitForControl(btnView);
        btnView.click();
    }

    public String getSubTitle(){
        return Label.getText(subTitle);
    }


}
