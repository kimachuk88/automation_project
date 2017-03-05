package com.epam.framework.controls.base;

import com.epam.framework.utility.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;

import java.util.List;

/**
 * Created by Uliana Pizhanska on 28/02/2017.
 */
public abstract class WebControlImpl implements WebControl {
    private final WebElement element;

    public WebControlImpl(final WebElement element) {
        this.element = element;
    }

    @Override
    public void click() {
        element.click();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        element.sendKeys(keysToSend);
    }


    @Override
    public Point getLocation() {
        return element.getLocation();
    }


    @Override
    public void submit() {
        element.submit();
    }


    @Override
    public String getAttribute(String name) {
        return element.getAttribute(name);
    }


    @Override
    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }


    @Override
    public Dimension getSize() {
        return element.getSize();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return element.findElements(by);
    }

    @Override
    public String getText() {
        return element.getText();
    }

    @Override
    public String getTagName() {
        return element.getTagName();
    }

    @Override
    public boolean isSelected() {
        return element.isSelected();
    }

    @Override
    public WebElement findElement(By by) {
        return element.findElement(by);
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public void clear() {
        element.clear();
    }
    @Override
    public WebElement getWrappedElement() {
        return element;
    }

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable) element).getCoordinates();
    }

    @Override
    public Rectangle getRect(){
        return this.element.getRect();}

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return this.element.getScreenshotAs(outputType);
    }

    public void clickJS() {
        ((JavascriptExecutor)Driver.instance).executeScript("arguments[0].click();", element);
    }

    public void moveToElement(){
        new Actions(Driver.instance).moveToElement(element).perform();
    }
}
