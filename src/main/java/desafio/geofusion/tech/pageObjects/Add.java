package desafio.geofusion.tech.pageObjects;

import core.functions.Functions;
import core.util.SystemProperties;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Add extends Functions {
    // ------------------------------ FIELDS ------------------------------
    private static final Logger log = LoggerFactory.getLogger(Add.class);

    // ------------------------------ PUBLIC METHODS ------------------------------
    public void preencherCampoNomeProduto() {
        log.debug("Preencher campo nome produto");
        SystemProperties.set("ProdutoNome", "Produto TesteQA Automação limite de caracteres  " + createRandomNumber(100, 999));
        System.out.println("O Nome do produto é : " + SystemProperties.get("ProdutoNome") + "\n");
        preencher(By.name("name"), SystemProperties.get("ProdutoNome"));
    }

    public void preencherCampoValor() {
        log.debug("Preencher campo valor");
        SystemProperties.set("Valor", createRandomNumber(1000, 9999).toString());
        preencher(By.name("price"), SystemProperties.get("Valor"));
        System.out.println("O Valor é : " + SystemProperties.get("Valor") + "\n");
    }

    public void clicarCampoDataValidade() {
        log.debug("Clicar no campo data validade");
        clicar(By.name("data_expiration"));
        clicar(By.cssSelector("body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-bottom > div.datepicker-days > table > thead > tr:nth-child(2) > th.next"));
        List<WebElement> listDays = getElements(By.cssSelector(".day"));
        listDays.get(createRandomNumber(1, listDays.size())).click();
    }

    public void clicarBotaoSalvar() {
        log.debug("Clicar no botão Salvar");
        clicar(By.cssSelector(".btn.btn-primary"));
    }

    public void pegarTextoConfirmacaoProduto() {
        log.debug("Pegar texto alert confirmação");
        SystemProperties.set("MensagemConfirmacaoProdutoCriado", pegarTextoAlert());
    }

    public void clicarBotaoOkMensagem() {
        log.debug("Clicar no botão Ok da mensagem");
        clicarOkAlert();
    }

    public void clicarBotaoCancelar(){
        log.debug("Clicar no botão cancelar");
        clicar(By.cssSelector(".btn.btn-default"));
    }

    // ------------------------------ ASSERTS ------------------------------
    public void assertMensagemConfirmacaoFoiExida(String mensagemEsperada) {
        log.debug("Validar mensagem de confirmação");
        Assert.assertEquals("A mensagem " + mensagemEsperada + " não foi exibida", tratarString(mensagemEsperada), tratarString(SystemProperties.get("MensagemConfirmacaoProdutoCriado")));
        System.out.println("Mensagem de confirmação exibida : " + SystemProperties.get("MensagemConfirmacaoProdutoCriado") + "\n");
    }

    public void assertAcessarUrlAdd() {
        log.debug("Validando se a tela add foi acessada");
        Assert.assertTrue("A tela não foi acessada", driver.getCurrentUrl().contains("/add"));
    }

    // ------------------------------ END OF METHODS ------------------------------
}
