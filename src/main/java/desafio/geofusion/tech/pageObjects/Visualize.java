package desafio.geofusion.tech.pageObjects;

import core.functions.Functions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Visualize extends Functions {
    // ------------------------------ FIELDS ------------------------------
    private static final Logger log = LoggerFactory.getLogger(Listt.class);

    // ------------------------------ PUBLIC METHODS ------------------------------
    public void clicarBotaoVoltar() {
        log.debug("Clicar no botão Voltar");
        clicar(By.cssSelector("#actions > div > a.btn.btn-default"));
    }

    // ------------------------------ ASSERTS METHODS ------------------------------
    public void assertDescricaoProdutoVisualizado(String descricaoListAleatorio) {
        log.debug("validar se o item é mesmo selecionado na lista");
        Assert.assertTrue("A descrição não condiz com o selecionado na lista", tratarString(getElement(By.cssSelector("#main > div:nth-child(2) > div:nth-child(2) > p.ng-pristine.ng-untouched.ng-valid.ng-binding")).getText()).contains(tratarString(descricaoListAleatorio)));
        System.out.println("O produto " + descricaoListAleatorio + " foi validado com sucesso! \n");
    }

    public void assertIDProdutoVisualizado(String idProduto) {
        log.debug("Validar se o ID do item é mesmo selecionado na lista");
        Assert.assertTrue("O ID do produto não condiz com o selecionado na lista", tratarString(getElement(By.cssSelector("#main > div:nth-child(2) > div:nth-child(1) > p.ng-pristine.ng-untouched.ng-valid.ng-binding")).getText()).contains(tratarString(idProduto)));
    }

    // ------------------------------ END OF METHODS ------------------------------
}
