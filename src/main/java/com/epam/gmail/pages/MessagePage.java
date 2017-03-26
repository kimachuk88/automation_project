package com.epam.gmail.pages;

import com.epam.framework.controls.extension.Button;
import com.epam.framework.controls.extension.CheckBox;
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

    @FindBy(xpath = "//a[contains(text(),'Inbox')]")
    private Button btnInboxFolder;

    @FindBy(xpath = "//a[contains(text(),'Drafts')]")
    private Button btnDraftsFolder;

    @FindBy(xpath="//*[@class='gb_vb gb_wb']")
    private Label accountName;

    @FindBy(xpath = "//div[contains(text(),'COMPOS')]")
    private Button btnCompose;

    @FindBy(xpath = "//tbody/tr[1]/td[2]/div[@class = 'oj']/div/textarea")
    private Input fieldTo;

    @FindBy(xpath = "//input[@name = 'subjectbox']")
    private Input subject;

    @FindBy(xpath = "//img[@aria-label='Save & Close']")
    private Button btnCloseMessage ;

    @FindBy(xpath = "//div[@aria-label='Message Body']")
    private Input messageBody;

    @FindBy(xpath = "//*[text() = 'Send']")
    private Button btnSend;

    @FindBy(xpath = "//div[contains(@class, 'oZ-jc T-Jo J-J5-Ji')][1]")
    private CheckBox chkDraftMessage;

    public void clickOnAccountInfo(){
        waitForPageLoad();
        waitForControl(btnAccountInfo);
        btnAccountInfo.click();
    }

    public String getAccountName(){
        return Label.getText(accountName);
    }

    public void clickOnComposeMessage(){
        waitForPageLoad();
        waitElementToClick(btnCompose);
        btnCompose.click();
    }

    public void setReceiver(String receiveEmail){
        waitForPageLoad();
        waitForControl(fieldTo);
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
        waitForPageLoad();
    }

    public void clickOnInboxFolder(){
        waitForControl(btnInboxFolder);
        btnInboxFolder.click();
        waitForPageLoad();
    }

    public void clickOnDraftsFolder(){
        waitForControl(btnDraftsFolder);
        btnDraftsFolder.click();
    }

    public void clickOnCloseMessage(){
        waitForControl(btnCloseMessage);
        btnCloseMessage.click();
    }

    public void checkDraftMessage(){
        waitForControl(chkDraftMessage);
        chkDraftMessage.check();
    }
}
