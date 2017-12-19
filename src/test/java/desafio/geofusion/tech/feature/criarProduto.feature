Feature: Criar Produto

  Scenario: Criar produto
    Given O usuario acessa url "http://desafio.geofusion.tech/"
    When O usuario preenche nome completo "Ramon Freitas"
    And O usuario clicar no botao flag
    Then O usuario esta na tela '/list'
    And Clica no botao Novo Produto"
    When Preenche o campo Nome Produto com cinquenta caracteres
    And Preenche o campo Valor
    And Preenche o campo Data de Validade
    And Clica no botao Salvar
    Then O Sistema exibe a mensagem "Produto adicionado com sucesso!"
    And O usuario busca o produto na campo por nome
    And O usuario registra o ID do produto gerado pelo sistema
