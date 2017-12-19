package desafio.geofusion.tech.pageObjects;

import core.functions.Functions;
import core.util.SystemProperties;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Listt extends Functions {
    // ------------------------------ FIELDS ------------------------------
    private static final Logger log = LoggerFactory.getLogger(Listt.class);

    // ------------------------------ PUBLIC METHODS ------------------------------
    public void clicarBotaoNovoProduto() {
        log.debug("Clicar no botão Novo Produto");
        clicar(By.cssSelector(".btn.btn-primary.pull-right.h2"));
    }

    public void clicarBotaoEditar() {
        log.debug("Clicar no botão Editar");
        clicarRandom(By.cssSelector(".btn.btn-warning.btn-xs"));
    }

    public void clicarBotaoVisualizar() {
        log.debug("Clicar no botão Visualizar");
        clicar(By.cssSelector(".btn.btn-success.btn-xs"));
    }

    public void clicarBotaoExcluir() {
        log.debug("Clicar no botão Excluir");
        clicar(By.cssSelector(".btn.btn-danger.btn-xs"));
    }

    public void clicarBotaoBuscar() {
        log.debug("Clicar no botão buscar");
        clicar(By.cssSelector(".btn.btn-primary"));
    }

    public void clicarLinkInicio() {
        log.debug("Clicar no link Inicio");
        clicar(By.cssSelector("#navbar > ul > li:nth-child(1) > a"));
    }

    public void pegarIDProduto() {
        log.debug("Gravar id Produto");
        SystemProperties.set("ProdutoId", getText(By.cssSelector("#list > div > table > tbody > tr > td:nth-child(1)")));
        System.out.println("O ID do Produto criado é : " + SystemProperties.get("ProdutoId") + "\n");
    }

    public void preencherCampoBuscaNome(String nomeProduto) {
        log.debug("Gravar id Produto");
        preencher(By.name("data[search]"), nomeProduto);
    }

    public void pegarDescricaoItemAleatorio() {
        log.debug("Pegar um item aleatório");
        List<WebElement> listtDescription = getElements(By.cssSelector("#list > div > table > tbody > tr > td:nth-child(2)"));
        SystemProperties.set("ProdutoAleatorio", listtDescription.get(createRandomNumber(1, listtDescription.size() - 1)).getText());
        System.out.println("O produto escolhido foi : " + SystemProperties.get("ProdutoAleatorio") + "\n");
    }

    public void clicarbotaoSim() {
        log.debug("Clicar no botão 'SIM'");
        clicar(By.xpath("//button[contains(.,'Sim')]"));
    }

    public void gravarMsgClicarBotaoAlert() {
        log.debug("Gravar msg e clicar OK button alert");
        SystemProperties.set("MensagemExcluirItem", pegarTextoAlert());
        System.out.println("O " + SystemProperties.get("MensagemExcluirItem") + " " + SystemProperties.get("ProdutoAleatorio") + "\n");
        clicarOkAlert();
    }

    // ------------------------------ ASSERT METHODS ------------------------------
    public void assertInformacoesPagina() {
        log.debug("Validar informações /list");
        Assert.assertTrue("Não foi possivel acessar a url", pegarUrlAtual().contains("/list"));
        Assert.assertTrue("Não foi exibido o campo 'Pesquisa'", existElement(By.name("data[search]")));
        Assert.assertTrue("Não foi exibido o texto 'Produtos'", existElement(By.cssSelector("#top > div:nth-child(1) > h2")));
        log.debug("As informações da tela /list foram validadas.");
    }

    public void assertDescricaoProdutoEditado(String desricaoItem) {
        log.debug("Validar descrição produto editado");
        Assert.assertTrue("A descrição do produto não foi editada", tratarString(getElement(By.cssSelector("#list > div > table > tbody > tr > td:nth-child(2)")).getText()).contains(tratarString(desricaoItem)));
    }

    public void assertMensagemExclusaoItem(String mensagemExlusaoProduto) {
        log.debug("Validar mensagem produto excluído");
        Assert.assertTrue("O produto não foi excluído corretamente", tratarString(SystemProperties.get("MensagemExcluirItem")).contains(tratarString(mensagemExlusaoProduto)));
    }

    public void assertProdutoExcluidoNaoPodeExibido() {
        log.debug("Validar que o produto excluído não é mais exibido na grid");
        esperar(2000);
        Assert.assertTrue("O produto excluído continua sendo exibido", existElement(By.cssSelector("#list > div > table")));
        esperar(1000);
        System.out.println("O produto : " + SystemProperties.get("ProdutoAleatorio") + " foi excluído com sucesso! \n ");
    }

    public void assertAcessarUrlList() {
        log.debug("Validando se a tela add foi acessada");
        Assert.assertTrue("A tela não foi acessada", driver.getCurrentUrl().contains("/list"));
    }

    // ------------------------------ END OF METHODS ------------------------------
}
