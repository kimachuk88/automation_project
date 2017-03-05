package com.epam.gmail.pages;
import com.epam.framework.utility.CustomFieldDecorator;
import com.epam.framework.utility.Driver;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Uliana Pizhanska on 27/02/2017.
 */
public abstract class BasePage {

    protected Logger log = Logger.getLogger("WD: ");

    public BasePage() {
        PageFactory.initElements(new CustomFieldDecorator(Driver.instance), this);
    }
}
