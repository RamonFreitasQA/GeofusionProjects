package desafio.geofusion.tech.steps;

import core.functions.Functions;
import core.util.SystemProperties;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import desafio.geofusion.tech.pageObjects.Edit;
import desafio.geofusion.tech.pageObjects.Home;
import desafio.geofusion.tech.pageObjects.Listt;

public class EditarProduto extends Functions {

    // ------------------------------ PUBLIC METHODS ------------------------------
    @Given("^O usuario esta na url \"([^\"]*)\"$")
    public void oUsuarioEstaNaUrl(String url) {
        SystemProperties.set("Scenario", "Editar produto");
        openBrowser("GoogleChrome", "Não");
        acessarUrl(url);
    }

    @When("^O usuario inseri o nome completo \"([^\"]*)\"$")
    public void oUsuarioInseriONomeCompleto(String ownerName) {
        Home home = new Home();
        home.preencherCampoDigiteNomeCompleto(ownerName);
    }

    @And("^O usuario clica no botao flag$")
    public void oUsuarioClicaNoBotaoFlag() {
        Home home = new Home();
        home.clicarBotaoCheck();
    }

    @Then("^O usuario esta novamente na tela '/list'$")
    public void oUsuarioEstaNovamenteNaTelaList() {
        Listt listt = new Listt();
        listt.assertInformacoesPagina();
    }

    @When("^Clica no botao Editar$")
    public void clicaNoBotaoEditar() {
        Listt listt = new Listt();
        listt.clicarBotaoEditar();
    }

    @And("^Altera o campo Nome Produto$")
    public void alteraOCampoNomeProduto() {
        Edit edit = new Edit();
        edit.preencherCampoNomeProduto();
    }

    @And("^Altera o campo Valor$")
    public void alteraOCampoValor() {
        Edit edit = new Edit();
        edit.preencherCampoValor();
    }

    @And("^Altera o campo Data de Validade$")
    public void alteraOCampoDataDeValidade() {
        Edit edit = new Edit();
        edit.clicarCampoDataValidade();
    }

    @And("^O usuario clica no botao Salvar$")
    public void oUsuarioClicaNoBotaoSalvar() {
        Edit edit = new Edit();
        edit.clicarBotaoSalvar();
    }

    @Then("^O Sistema apos a edicao exibe a mensagem \"([^\"]*)\"$")
    public void oSistemaAposAEdicaoExibeAMensagem(String mensagemEsperadaEdicao) {
        Edit edit = new Edit();
        edit.pegarTextoConfirmacaoEdicaoProduto();
        edit.validarMensagemConfirmacaoEdicaoFoiExibida(mensagemEsperadaEdicao);
        edit.clicarBotaoOkMensagem();
    }

    @And("^O usuario busca o produto editado no campo busca$")
    public void oUsuarioBuscaOProdutoEditadoNoCampoBusca() {
        Listt listt = new Listt();
        listt.preencherCampoBuscaNome(SystemProperties.get("ProdutoNomeNovo"));
        listt.clicarBotaoBuscar();
    }

    @And("^O usuario valida o ID produto, descricao e o valor$")
    public void oUsuarioValidaOIDProdutoDescricaoEOValor() {
        Listt listt = new Listt();
        listt.pegarIDProduto();
        listt.assertDescricaoProdutoEditado(SystemProperties.get("ProdutoNomeNovo"));
    }

    // ------------------------------ END OF METHODS ------------------------------
    @After
    public void tearDown() {
        closeDriver();
    }
}
