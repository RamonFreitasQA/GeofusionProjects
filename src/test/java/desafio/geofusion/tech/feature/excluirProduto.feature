Feature: Excluir Produto.

  Scenario: Excluir produto

    Given O usuario acessa a url "http://desafio.geofusion.tech/"
    When O usuario preenche o nome completo "Ramon Freitas"
    And O usuario clicar no botão flag
    When O preenche o campo busca com um item aleatorio
    And Clica no botao buscar
    And Clica no botao Excluir
    Then O Sistema deve exibir a mensagem de confirmacao
    And Validar a mensagem de confirmacao "Produto excluído com sucesso!"
    When O usuario buscar novamente o produto
    Then O sistema nao deve exibir o item
