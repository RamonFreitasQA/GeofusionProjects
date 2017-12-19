package desafio.geofusion.tech.pageObjects;

import core.functions.Functions;
import core.util.SystemProperties;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Edit extends Functions {
    // ------------------------------ FIELDS ------------------------------
    private static final Logger log = LoggerFactory.getLogger(Add.class);

    // ------------------------------ PUBLIC METHODS ------------------------------
    public void preencherCampoNomeProduto() {
        log.debug("Preencher campo nome produto");
        SystemProperties.set("ProdutoNomeNovo", "Produto TesteQA Automação limite de caracteres  " + createRandomNumber(100, 999));
        System.out.println("O Novo Nome do produto é : " + SystemProperties.get("ProdutoNomeNovo") + "\n");
        preencher(By.name("name"), SystemProperties.get("ProdutoNomeNovo"));
    }

    public void preencherCampoValor() {
        log.debug("Preencher campo valor");
        SystemProperties.set("ValorNovo", createRandomNumber(1000, 9999).toString());
        preencher(By.cssSelector(".form-control.ng-pristine.ng-untouched.ng-valid.ng-valid-required"), SystemProperties.get("ValorNovo"));
        System.out.println("O Novo Valor é : " + SystemProperties.get("ValorNovo") + "\n");
    }

    public void clicarCampoDataValidade() {
        log.debug("Clicar no campo data validade");
        clicar(By.name("data_expiration"));
        clicar(By.cssSelector("#campo3"));
        List<WebElement> listDays = getElements(By.cssSelector(".day"));
        listDays.get(createRandomNumber(1, listDays.size())).click();
    }

    public void clicarBotaoSalvar() {
        log.debug("Clicar no botão Salvar");
        clicar(By.cssSelector(".btn.btn-primary"));
    }

    public void pegarTextoConfirmacaoEdicaoProduto() {
        log.debug("Pegar texto alert confirmação edição");
        SystemProperties.set("MensagemConfirmacaoProdutoEditado", pegarTextoAlert());
    }

    public void clicarBotaoOkMensagem() {
        log.debug("Clicar no botão Ok da mensagem");
        clicarOkAlert();
    }

    // ------------------------------ ASSERTS ------------------------------
    public void validarMensagemConfirmacaoEdicaoFoiExibida(String mensagemEsperada) {
        log.debug("Validar mensagem de confirmação");
        Assert.assertEquals("A mensagem " + mensagemEsperada + " não foi exibida", tratarString(mensagemEsperada), tratarString(SystemProperties.get("MensagemConfirmacaoProdutoEditado")));
        System.out.println("Mensagem de confirmação exibida : " + SystemProperties.get("MensagemConfirmacaoProdutoEditado") + "\n");
    }

    // ------------------------------ END OF METHODS ------------------------------
}
