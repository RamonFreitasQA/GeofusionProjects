package core.functions;

import core.seleniumWebriver.WebdriverFactor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Functions extends WebdriverFactor {



    // -------------------------- PROTECTED METHODS --------------------------
    protected static void esperar(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Something given wrong, when I tried wait");
        }
    }

    protected void clicar(By locator) {
        checkReadyState();
        wait(locator);
        WebElement element = getElement(locator);
        highlight(element);
        element.click();
    }

    protected void acessarUrl(String url) {
        driver.get(url);
    }

    protected void clicarRandom(By locator) {
        checkReadyState();
        wait(locator);
        List<WebElement> optionClick = getElements(locator);
        WebElement optionSelected = optionClick.get(createRandomNumber(0, 2));
        highlight(optionSelected);
        optionSelected.click();
    }

    protected void preencher(By locator, String texto) {
        checkReadyState();
        wait(locator);
        WebElement element = getElement(locator);
        highlight(element);
        element.clear();
        element.sendKeys(texto);
    }

    protected boolean existElement(By locator) {
        wait(locator);
        return getElements(locator).size() != 0;
    }

    protected boolean existElements(By locator) {
        return getElements(locator).size() != 0;
    }

    protected String getText(By locator) {
        checkReadyState();
        wait(locator);
        return getElement(locator).getText();
    }

    protected List<WebElement> getElements(By locator) {
        wait(locator);
        return driver.findElements(locator);
    }

    protected String pegarUrlAtual() {
        return driver.getCurrentUrl();
    }

    protected Integer createRandomNumber(Integer numeroInicial, Integer numeroFinal) {
        Random random = new Random();
        return random.nextInt(numeroFinal) + numeroInicial;
    }

    protected void clicarOkAlert() {
        driver.switchTo().alert().accept();
    }

    protected String pegarTextoAlert() {
        esperar(500);
        return driver.switchTo().alert().getText();
    }

    protected String tratarString(String texto) {
        String tratarTexto = texto;
        tratarTexto.trim();
        tratarTexto = texto.replaceAll(" ", "");
        return tratarTexto.toUpperCase();
    }

    protected WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    // -------------------------- PRIVATE METHODS --------------------------

    private void highlight(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='4px solid red'", element);
    }

    private void checkReadyState() {
        Integer cont = 0;
        while (!((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete") && cont != 60) {
            esperar(500);
            cont++;
        }
    }

    private void wait(By locator) {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    // -------------------------- END OF METHODS --------------------------
}
