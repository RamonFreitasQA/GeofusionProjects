package desafio.geofusion.tech.pageObjects;

import core.functions.Functions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Home extends Functions {

    // ------------------------------ FIELDS ------------------------------
    private static final Logger log = LoggerFactory.getLogger(Add.class);

    // ------------------------------ PUBLIC METHODS ------------------------------
    public void usuarioAcessaList() {
        log.debug("Deixar o usuário na tela list");
        preencherCampoDigiteNomeCompleto("Ramon Freitas");
        clicarBotaoCheck();
    }

    public void preencherCampoDigiteNomeCompleto(String texto) {
        log.debug("Preencher campo Owner");
        preencher(By.cssSelector("#owner"), texto);
    }

    public void clicarBotaoCheck() {
        log.debug("Clicar no botão check");
        clicar(By.cssSelector(".btn.btn-primary"));
    }

    public void clicarLinkSobre() {
        log.debug("Clicar no botão sobre");
        clicar(By.cssSelector("#navbar > ul > li:nth-child(2) > a"));
    }

    public void pegarTextoSobre() {
        log.debug("Pegar texto Sobre funcionalidade");
        System.out.print("\n " + getText(By.cssSelector("#info-modal > div > div > div.modal-body")) + "\n \n ");
    }

    public void clicarBotaoOkSobre() {
        log.debug("Clicar no botão OK sobre");
        clicar(By.cssSelector(".btn.btn-default"));
    }

    // ------------------------------ ASSERT'S ------------------------------
    public void assertCampoOwnerExibido() {
        log.debug("Validar popup sobre foi fechado");
        esperar(2000);
        Assert.assertTrue("O campo owner não foi exibido", existElement(By.cssSelector("#owner")));
    }

    public void assertAcessarUrl() {
        log.debug("Validando se a tela add foi acessada");
        Assert.assertTrue("A tela não foi acessada", driver.getCurrentUrl().equals("http://desafio.geofusion.tech/"));
    }
    // ------------------------------ END OF METHODS ------------------------------
}
