Feature: Visualizar produto.

  Scenario: Visualizar produto
    Given O usuario acessa esta url "http://desafio.geofusion.tech/"
    When Preenche o nome completo "Ramon Freitas"
    And Clica no botão check
    And Escolhe um produto aleatorio
    When Preencher o campo busca
    And Clica em buscar
    And Clica no botao Visualizar
    Then O Sistema exibe as informacoes do produto