package desafio.geofusion.tech.steps;

import core.functions.Functions;
import core.util.SystemProperties;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import desafio.geofusion.tech.pageObjects.Home;
import desafio.geofusion.tech.pageObjects.Listt;
import desafio.geofusion.tech.pageObjects.Visualize;

public class VisualizarProduto extends Functions {

    // ------------------------------ PUBLIC METHODS ------------------------------
    @Given("^O usuario acessa esta url \"([^\"]*)\"$")
    public void oUsuarioAcessaEstaUrl(String url) {
        SystemProperties.set("Scenario", "Visualizar produto");
        openBrowser("GoogleChrome", "Sim");
        acessarUrl(url);
    }

    @When("^Preenche o nome completo \"([^\"]*)\"$")
    public void preencheONomeCompleto(String ownerName) {
        Home home = new Home();
        home.preencherCampoDigiteNomeCompleto(ownerName);
        home.clicarBotaoCheck();
    }

    @And("^Clica no botão check$")
    public void clicaNoBotaoCheck() {
        Home home = new Home();
        home.clicarBotaoCheck();
    }

    @And("^Escolhe um produto aleatorio$")
    public void escolheUmProdutoAleatorio() {
        Listt listt = new Listt();
        listt.pegarDescricaoItemAleatorio();
    }

    @When("^Preencher o campo busca$")
    public void preencherOCampoBusca() {
        Listt listt = new Listt();
        listt.preencherCampoBuscaNome(SystemProperties.get("ProdutoAleatorio").substring(46, 50));
    }

    @And("^Clica em buscar$")
    public void clicaEmBuscar() {
        Listt listt = new Listt();
        listt.clicarBotaoBuscar();
    }

    @And("^Clica no botao Visualizar$")
    public void clicaNoBotaoVisualizar() {
        Listt listt = new Listt();
        listt.pegarIDProduto();
        listt.clicarBotaoVisualizar();
    }

    @Then("^O Sistema exibe as informacoes do produto$")
    public void oSistemaExibeAsInformacoesDoProduto() {
        Visualize visualize = new Visualize();
        visualize.assertIDProdutoVisualizado(SystemProperties.get("ProdutoId"));
        visualize.assertDescricaoProdutoVisualizado(SystemProperties.get("ProdutoAleatorio"));
        visualize.clicarBotaoVoltar();
    }

    // ------------------------------ END OF METHODS ------------------------------
    @After
    public void tearDown() {
        closeDriver();
    }
}
