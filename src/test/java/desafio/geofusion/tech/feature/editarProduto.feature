Feature: Editar Produto

  Scenario: Editar produto
    Given O usuario esta na url "http://desafio.geofusion.tech/"
    When O usuario inseri o nome completo "Ramon Freitas"
    And O usuario clica no botao flag
    Then O usuario esta novamente na tela '/list'
    When Clica no botao Editar
    And Altera o campo Nome Produto
    And Altera o campo Valor
    And Altera o campo Data de Validade
    And O usuario clica no botao Salvar
    Then O Sistema apos a edicao exibe a mensagem "Produto editado com sucesso!"
    And O usuario busca o produto editado no campo busca
    And O usuario valida o ID produto, descricao e o valor

