package desafio.geofusion.tech.steps;

import core.functions.Functions;
import core.util.SystemProperties;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import desafio.geofusion.tech.pageObjects.Add;
import desafio.geofusion.tech.pageObjects.Home;
import desafio.geofusion.tech.pageObjects.Listt;

public class XploratorioTest extends Functions {

    // ------------------------------ FIELDS ------------------------------
    private Home home = new Home();
    private Listt listt = new Listt();

    // ------------------------------ PUBLIC METHODS ------------------------------
    @Given("^O usuario esta url \"([^\"]*)\"$")
    public void oUsuarioEstaUrl(String url) {
        SystemProperties.set("Scenario", "Exploratorio test");
        openBrowser("GoogleChrome", "Sim");
        acessarUrl(url);
    }

    @When("^O usuario clica na opcao sobre$")
    public void oUsuarioClicaNaOpcaoSobre() {
        home.clicarLinkSobre();
    }

    @Then("^O sistema exibe um popup com sobre a aplicacao e o autor$")
    public void oSistemaExibeUmPopupComSobreAAplicacaoEOAutor() {
        home.pegarTextoSobre();
    }

    @When("^O usuario clica no botao OK$")
    public void oUsuarioClicaNoBotaoOK() {
        home.clicarBotaoOkSobre();
    }

    @Then("^O Sistema exibe o campo owner$")
    public void oSistemaExibeOCampoOwner() {
        home.assertCampoOwnerExibido();
    }

    @When("^Preenche o campo com o nome completo \"([^\"]*)\"$")
    public void preencheOCampoComONomeCompleto(String ownerName) {
        home.preencherCampoDigiteNomeCompleto(ownerName);
    }

    @And("^Clica no botao flag$")
    public void clicaNoBotaoFlag() {
        home.clicarBotaoCheck();
    }

    @Then("^O sistema exibe a tela /list$")
    public void oSistemaExibeATelaList() {
        listt.assertInformacoesPagina();
    }

    @When("^O usuario clica no botao Novo Produto$")
    public void oUsuarioClicaNoBotaoNovoProduto() {
        listt.clicarBotaoNovoProduto();
    }

    @Then("^O sistema exibe a tela /add$")
    public void oSistemaExibeATelaAdd() {
        Add add = new Add();
        add.assertAcessarUrlAdd();
    }

    @When("^O usuario clica no botao cancelar$")
    public void oUsuarioClicaNoBotaoCancelar() {
        Add add = new Add();
        add.clicarBotaoCancelar();
    }

    @Then("^O Sistema retorna para tela /list$")
    public void oSistemaRetornaParaTelaList() {
        listt.assertAcessarUrlList();
    }

    @When("^O usuario clica no link Inicio$")
    public void oUsuarioClicaNoLinkInicio() {
        listt.clicarLinkInicio();
    }

    @Then("^O sistema exibe a home$")
    public void oSistemaExibeAHome() {
        home.assertAcessarUrl();
    }

    // ------------------------------ END OF METHODS ------------------------------
    @After
    public void tearDown() {
        closeDriver();
    }
}
