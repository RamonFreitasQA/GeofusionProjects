package desafio.geofusion.tech.steps;

import core.functions.Functions;
import core.util.SystemProperties;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import desafio.geofusion.tech.pageObjects.Add;
import desafio.geofusion.tech.pageObjects.Home;
import desafio.geofusion.tech.pageObjects.Listt;

public class CriarProduto extends Functions {

    // ------------------------------ PUBLIC METHODS ------------------------------
    @Given("^O usuario acessa url \"([^\"]*)\"$")
    public void oUsuarioAcessaUrl(String url) {
        SystemProperties.set("Scenario", "Criar produto");
        openBrowser("GoogleChrome", "Não");
        acessarUrl(url);
    }

    @When("^O usuario preenche nome completo \"([^\"]*)\"$")
    public void oUsuarioPreencheNomeCompleto(String ownerName){
        Home home = new Home();
        home.preencherCampoDigiteNomeCompleto(ownerName);
    }


    @And("^O usuario clicar no botao flag$")
    public void oUsuarioClicarNoBotaoFlag() {
        Home home = new Home();
        home.clicarBotaoCheck();
    }

    @Then("^O usuario esta na tela '/list'$")
    public void oUsuarioEstaNaTelaList() {
        Listt listt = new Listt();
        listt.assertInformacoesPagina();
    }

    @And("^Clica no botao Novo Produto\"$")
    public void clicaNoBotaoNovoProduto() {
        Listt listt = new Listt();
        listt.clicarBotaoNovoProduto();
    }

    @And("^Preenche o campo Nome Produto com cinquenta caracteres$")
    public void preencheOCampoNomeProdutoComCinquentaCaracteres() {
        Add add = new Add();
        add.preencherCampoNomeProduto();
    }

    @And("^Preenche o campo Valor$")
    public void preencheOCampoValor() {
        Add add = new Add();
        add.preencherCampoValor();
    }

    @And("^Preenche o campo Data de Validade$")
    public void preencheOCampoDataDeValidade() {
        Add add = new Add();
        add.clicarCampoDataValidade();
    }

    @And("^Clica no botao Salvar$")
    public void clicaNoBotaoSalvar() {
        Add add = new Add();
        add.clicarBotaoSalvar();
        add.pegarTextoConfirmacaoProduto();
    }

    @Then("^O Sistema exibe a mensagem \"([^\"]*)\"$")
    public void oSistemaExibeAMensagem(String mensagemConfirmacao) {
        Add add = new Add();
        add.assertMensagemConfirmacaoFoiExida(mensagemConfirmacao);
        add.clicarBotaoOkMensagem();
    }

    @And("^O usuario busca o produto na campo por nome$")
    public void oUsuarioBuscaOProdutoNaCampoPorNome() {
        Listt listt = new Listt();
        listt.preencherCampoBuscaNome(SystemProperties.get("ProdutoNome"));
        listt.clicarBotaoBuscar();
    }

    @And("^O usuario registra o ID do produto gerado pelo sistema$")
    public void oUsuarioRegistraOIDDoProdutoGeradoPeloSistema() {
        Listt listt = new Listt();
        listt.pegarIDProduto();
    }

    // ------------------------------ END OF METHODS ------------------------------
    @After
    public void tearDown() {
        closeDriver();
    }
}
