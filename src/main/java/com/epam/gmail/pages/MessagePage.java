package com.epam.gmail.pages;

import com.epam.framework.controls.extension.Button;
import com.epam.framework.controls.extension.Input;
import com.epam.framework.controls.extension.Label;
import com.epam.framework.utility.Driver;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Uliana Pizhanska on 05/03/2017.
 */
public class MessagePage extends BasePage {

    @FindBy(xpath = "//*[@class='gb_9a gbii']")
    private Button btnAccountInfo;

    @FindBy(xpath="//*[@class='gb_vb gb_wb']")
    private Label accountName;

    @FindBy(xpath = "//div[contains(text(),'COMPOS')]")
    private Button btnCompose;

    @FindBy(xpath = "//tbody/tr[1]/td[2]/div[@class = 'oj']/div/textarea")
    private Input fieldTo;

    @FindBy(xpath = "//input[@name = 'subjectbox']")
    private Input subject;

    public void clickOnAccountInfo(){
        waitForPageLoad();
        waitForControl(btnAccountInfo);
        btnAccountInfo.click();
    }

    public String getAccountName(){
        return Label.getText(accountName);
    }

    public void clickOnComposeMessage(){
        waitForControl(btnCompose);
        btnCompose.click();
    }

    public void setReceiver(String receiveEmail){
        fieldTo.setText(receiveEmail);
    }



}
