package com.epam.gmail.pages;

import com.epam.framework.controls.extension.Button;
import com.epam.framework.controls.extension.Input;
import com.epam.framework.controls.extension.Label;
import com.epam.framework.utility.Driver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by Uliana Pizhanska on 05/03/2017.
 */
public class MessagePage extends BasePage {

    @FindBy(xpath = "//*[@class='gb_9a gbii']")
    private Button btnAccountInfo;

    @FindBy(xpath = "//a[contains(text(),'Sent')]")
    private Button btnSentFolder;

    @FindBy(xpath="//*[@class='gb_vb gb_wb']")
    private Label accountName;

    @FindBy(xpath = "//div[contains(text(),'COMPOS')]")
    private Button btnCompose;

    @FindBy(xpath = "//tbody/tr[1]/td[2]/div[@class = 'oj']/div/textarea")
    private Input fieldTo;

    @FindBy(xpath = "//input[@name = 'subjectbox']")
    private Input subject;

    @FindBy(xpath = "//div[@aria-label='Message Body']")
    private Input messageBody;

    @FindBy(xpath = "//*[text() = 'Send']")
    private Button btnSend;

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


    public void setSubjectMessage(String subjectMessage){
        subject.setText(subjectMessage);
    }

    public void setMessageBodyField(String msgText){
        messageBody.setText(msgText);
    }

    public void clickOnSend(){
        waitForControl(btnSend);
        btnSend.click();
    }

    public void clickOnSentFolder(){
        waitForControl(btnSentFolder);
        btnSentFolder.click();
    }
}
