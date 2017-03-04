package com.epam.framework.utility;

import com.epam.framework.controls.base.WebControlImpl;
import org.apache.log4j.Logger;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;

/**
 * Created by Uliana Pizhanska on 04/03/2017.
 */
public class CustomFieldDecorator extends DefaultFieldDecorator {

    protected Logger log = Logger.getLogger("WD");

    public CustomFieldDecorator(final SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<?> clazz = field.getType();
        ElementLocator locator = factory.createLocator(field);
        if (locator == null) {
            return null;
        }
        if (field.getAnnotation(FindBy.class) != null) {
            if (WebControlImpl.class.isAssignableFrom(field.getType())) {
                try {
                    WebElement proxy = proxyForLocator(loader, locator);
                    return clazz.getConstructor(WebElement.class, String.class,
                            String.class).newInstance(proxy);
                } catch (Exception e) {
                    log.error("WebElement can't be represented as " + clazz);
                }
            }
        }
        return super.decorate(loader, field);
    }
}
