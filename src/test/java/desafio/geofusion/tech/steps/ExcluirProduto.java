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

public class ExcluirProduto extends Functions {

    // ------------------------------ FIELDS ------------------------------
    private Listt listt = new Listt();

    // ------------------------------ PUBLIC METHODS ------------------------------
    @Given("^O usuario acessa a url \"([^\"]*)\"$")
    public void oUsuarioAcessaAUrl(String url) {
        SystemProperties.set("Scenario", "Excluir produto");
        openBrowser("GoogleChrome", "Não");
        acessarUrl(url);
    }

    @When("^O usuario preenche o nome completo \"([^\"]*)\"$")
    public void oUsuarioPreencheONomeCompleto(String ownerName) {
        Home home = new Home();
        home.preencherCampoDigiteNomeCompleto(ownerName);
    }

    @And("^O usuario clicar no botão flag$")
    public void oUsuarioClicarNoBotaoFlag() {
        Home home = new Home();
        home.clicarBotaoCheck();
    }

    @When("^O preenche o campo busca com um item aleatorio$")
    public void oPreencheOCampoBuscaComUmItemAleatorio() {
        listt.pegarDescricaoItemAleatorio();
        listt.preencherCampoBuscaNome(SystemProperties.get("ProdutoAleatorio").substring(46, 50));
    }

    @And("^Clica no botao buscar$")
    public void clicaNoBotaoBuscar() {
        listt.clicarBotaoBuscar();
    }

    @And("^Clica no botao Excluir$")
    public void clicaNoBotaoExcluir() {
        listt.pegarIDProduto();
        listt.clicarBotaoExcluir();
        listt.clicarbotaoSim();
    }

    @Then("^O Sistema deve exibir a mensagem de confirmacao$")
    public void oSistemaDeveExibirAMensagemDeConfirmacao() {
        listt.gravarMsgClicarBotaoAlert();
    }

    @And("^Validar a mensagem de confirmacao \"([^\"]*)\"$")
    public void validarAMensagemDeConfirmacao(String mensagemExclusao) {
        listt.assertMensagemExclusaoItem(mensagemExclusao);
    }

    @When("^O usuario buscar novamente o produto$")
    public void oUsuarioBuscarNovamenteOProduto() {
        listt.preencherCampoBuscaNome(SystemProperties.get("ProdutoAleatorio"));
        listt.clicarBotaoBuscar();
    }

    @Then("^O sistema nao deve exibir o item$")
    public void oSistemaNaoDeveExibirOItem() {
        listt.assertProdutoExcluidoNaoPodeExibido();
    }

    // ------------------------------ END OF METHODS ------------------------------
    @After
    public void tearDown() {
        closeDriver();
    }
}
