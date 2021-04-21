# Game Store BR - Venda de jogos

### Sistema de cadastro de compras, utilizando conceito de vendedor/cliente e carrinho de compras. As compras podem ser realizadas por diferentes formas de pagamento.

**Funcionalidades do sistema:**

- **Cadastrar Compra:** Registra uma compra ao informar o comprador, o vendedor, a forma de
  pagamento e escolher os itens do carrinho de compras. Neste caso, de acordo com o meio de
  pagamento, a transação atualiza o saldo, os valores a pagar e as compras realizadas do cliente
  informado, assim como o saldo, os pagamentos pendentes e as vendas realizadas do vendedor
  informado.

- **Visualizar Vendedores:** Exibe uma tela contendo uma lista com informações resumidas dos
  vendedores cadastrados no sistema, contendo os seus dados pessoais resumidos. É possível filtrar
  vendedores com o CNPJ ou parte dele. Ao clicar no botão “Visualizar”, o sistema mostra uma tela
  com informações detalhadas do vendedor, incluindo o seu catálogo, vendas e pagamentos pendentes.

- **Visualizar Clientes:** Exibe uma tela contendo uma lista com informações resumidas dos clientes
  cadastrados no sistema, contendo os seus dados pessoais resumidos. É possível filtrar clientes com
  o CPF ou parte dele. Ao clicar no botão “Visualizar”, o sistema mostra uma tela com informações
  detalhadas do cliente, incluindo as suas compras e valores a pagar pendentes.

- **Formas de Pagamento:**

    - PIX: Instantaneamente debita o valor da conta do comprador e deposita na conta do vendedor.

    - Boleto: Verifica se a data de vencimento do boleto não foi excedida. Instantaneamente debita o
      valor da conta do comprador e adiciona nos valores a receber do vendedor, descontado do valor
      para emissão do boleto.

    - Débito: Instantaneamente debita o valor da conta do comprador e deposita na conta do vendedor,
      descontado da taxa percentual de cobrança da operadora do cartão.

    - Crédito: Coloca o valor na lista de valores a pagar do comprador e adiciona nos valores a
      receber do vendedor, descontado da taxa percentual de cobrança da operadora do cartão.


