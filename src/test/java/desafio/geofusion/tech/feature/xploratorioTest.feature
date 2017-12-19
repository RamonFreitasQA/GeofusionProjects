Feature: Exploratorio Teste

  Scenario: Test Exploratorio
    Given O usuario esta url "http://desafio.geofusion.tech/"
    When O usuario clica na opcao sobre
    Then O sistema exibe um popup com sobre a aplicacao e o autor
    When O usuario clica no botao OK
    Then O Sistema exibe o campo owner
    When Preenche o campo com o nome completo "Ramon Freitas"
    And Clica no botao flag
    Then O sistema exibe a tela /list
    When O usuario clica no botao Novo Produto
    Then O sistema exibe a tela /add
    When O usuario clica no botao cancelar
    Then O Sistema retorna para tela /list
    When O usuario clica no link Inicio
    Then O sistema exibe a home
