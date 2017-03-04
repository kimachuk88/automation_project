package com.epam.framework.utility;

import com.epam.framework.controls.base.WebControlImpl;
import org.apache.log4j.Logger;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.*;

import java.awt.*;
import java.lang.reflect.Field;

/**
 * Created by Uliana Pizhanska on 04/03/2017.
 */
public class CustomFieldDecorator extends DefaultFieldDecorator {

    protected Logger log = Logger.getLogger("WD");

    public CustomFieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<?> decoratableClass = decoratableClass(field);
        if (decoratableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }
            return createElement(loader, locator, decoratableClass);
        }
        return super.decorate(loader, field);
    }
    private Class<?> decoratableClass(Field field) {
        Class<?> clazz = field.getType();
        try {
            clazz.getConstructor(WebElement.class);
        } catch (Exception e) {
            return null;
        }
        return clazz;
    }

    protected <T> T createElement(ClassLoader loader,
                                  ElementLocator locator, Class<T> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return createInstance(clazz, proxy);
    }
    private <T> T createInstance(Class<T> clazz, WebElement element) {
        try {
            return (T) clazz.getConstructor(WebElement.class)
                    .newInstance(element);
        } catch (Exception e) {
            throw new AssertionError(
                    "WebElement can't be represented as " + clazz
            );
        }
    }
}
