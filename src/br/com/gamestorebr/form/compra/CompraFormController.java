package br.com.gamestorebr.form.compra;

import br.com.gamestorebr.GameStoreBrApplication;
import br.com.gamestorebr.model.pagamentos.Pagamento;
import br.com.gamestorebr.model.pagamentos.PagamentoFactory;
import br.com.gamestorebr.model.pessoa.Comprador;
import br.com.gamestorebr.model.pessoa.Vendedor;
import br.com.gamestorebr.model.produtos.Produto;
import br.com.gamestorebr.model.transacoes.Transacao;
import br.com.gamestorebr.model.transacoes.TransacaoItem;
import br.com.gamestorebr.repository.CompradorRepository;
import br.com.gamestorebr.repository.TransacaoRepository;
import br.com.gamestorebr.repository.VendedorRepository;
import br.com.gamestorebr.util.AlertHelper;
import br.com.gamestorebr.util.DataBase;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;

public class CompraFormController implements Initializable {

  @FXML public ChoiceBox vendedorField;
  @FXML public ChoiceBox compradorField;
  @FXML public ChoiceBox pagamentoField;
  @FXML public ChoiceBox produtoField;
  @FXML public Spinner quantidadeField;
  @FXML public Button adicionarProdutoButton;
  @FXML public TextField valorTotalField;
  @FXML public Button finalizarCompraButton;

  private VendedorRepository vendedorRepository;

  private CompradorRepository compradorRepository;

  private TransacaoRepository transacaoRepository;

  @FXML private TableView<TableViewProdutoVendaItem> carrinhoDeComprasTabView;
  @FXML private TableColumn<TableViewProdutoVendaItem, Integer> produtoCol;
  @FXML private TableColumn<TableViewProdutoVendaItem, Integer> quantidadeCol;
  @FXML private TableColumn<TableViewProdutoVendaItem, Double> precoUnitarioCol;
  @FXML private TableColumn<TableViewProdutoVendaItem, Double> valorTotalCol;

  @Override
  public void initialize(final URL url, final ResourceBundle resourceBundle) {

    vendedorRepository = DataBase.getVendedorRepository();
    compradorRepository = DataBase.getCompradorRepository();
    transacaoRepository = DataBase.getTransacaoRepository();

    final ObservableList<String> vendedores = FXCollections.observableArrayList();
    vendedorRepository
        .listAll()
        .forEach(
            vendedor -> {
              vendedores.addAll(vendedor.getNome());
            });
    vendedorField.setItems(vendedores);

    final ObservableList<String> compradores = FXCollections.observableArrayList();
    compradorRepository
        .listAll()
        .forEach(
            comprador -> {
              compradores.addAll(comprador.getNome());
            });
    compradorField.setItems(compradores);

    final ObservableList<String> pagamentos = FXCollections.observableArrayList();
    Arrays.asList("Boleto", "Crédito", "Débito", "Pix")
        .forEach(
            pagamento -> {
              pagamentos.addAll(pagamento);
            });
    pagamentoField.setItems(pagamentos);

    produtoCol.setCellValueFactory(new PropertyValueFactory<>("produto"));
    quantidadeCol.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    precoUnitarioCol.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
    valorTotalCol.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
  }

  public void handleVoltarCompraButtonAction(final ActionEvent actionEvent) throws IOException {
    GameStoreBrApplication.changeScene("form/menu/menu_form.fxml");
  }

  public void handleCadastrarCompraButtonAction(final ActionEvent actionEvent) throws IOException {

    final Window owner = finalizarCompraButton.getScene().getWindow();

    if (!validarCadastroCompra()) {
      return;
    }

    final Comprador comprador = compradorRepository.getByName(
        compradorField.getValue().toString());
    final Vendedor vendedor = vendedorRepository.getByName(
        vendedorField.getValue().toString());
    final String tipoPagamento = pagamentoField.getValue().toString();

    final List<TransacaoItem> transacaoItems =
        carrinhoDeComprasTabView.getItems().stream()
            .map(
                itemCarrinho ->
                    new TransacaoItem(
                        vendedor.getProdutoByName(itemCarrinho.getProduto()),
                        itemCarrinho.getQuantidade()))
            .collect(Collectors.toList());

    final Transacao transacao = new Transacao(comprador, vendedor, transacaoItems);

    final Pagamento pagamento =
        PagamentoFactory.newPagamento(
            comprador, vendedor, transacao.getValorTotal(), tipoPagamento);

    transacao.setPagamento(pagamento);

    final boolean transacaoFinanceiraRealizada = pagamento.realizarTransacaoFinanceira();

    if (!transacaoFinanceiraRealizada) {

      AlertHelper.showAlert(
          AlertType.ERROR,
          owner,
          "Erro",
          "Comprador não possui saldo necessário para concluir a operação.");

      return;
    }

    comprador.getCompras().add(transacao);

    vendedor.getVendas().add(transacao);

    compradorRepository.update(comprador);

    vendedorRepository.update(vendedor);

    transacaoRepository.add(transacao);

    AlertHelper.showAlert(
        AlertType.INFORMATION, owner, "Sucesso", "Transação Comercial realizada com sucesso!");

    GameStoreBrApplication.changeScene("form/menu/menu_form.fxml");
  }

  private boolean validarCadastroCompra() {

    final Window owner = finalizarCompraButton.getScene().getWindow();

    if (vendedorField.getValue() == null) {
      AlertHelper.showAlert(AlertType.ERROR, owner, "Erro", "Informe um vendedor!");
      return false;
    }

    if (compradorField.getValue() == null) {
      AlertHelper.showAlert(AlertType.ERROR, owner, "Erro", "Informe um cliente!");
      return false;
    }

    if (pagamentoField.getValue() == null) {
      AlertHelper.showAlert(AlertType.ERROR, owner, "Erro", "Informe uma forma de pagamento");
      return false;
    }

    if (carrinhoDeComprasTabView.getItems().isEmpty()) {
      AlertHelper.showAlert(AlertType.ERROR, owner, "Erro", "Informe ao menos um produto!");
      return false;
    }

    return true;
  }

  public void handleAdicionarProdutoButtonAction(final ActionEvent actionEvent) {

    final Window owner = adicionarProdutoButton.getScene().getWindow();
    final String produtoFieldValue = (String) produtoField.getValue();

    final Integer quantidade = (Integer) quantidadeField.getValue();

    if (produtoFieldValue == null || quantidade == null || quantidade == 0) {
      AlertHelper.showAlert(
          AlertType.INFORMATION, owner, "Atenção", "Informe um Produto e a Quantidade desejada!");
      return;
    }

    final Vendedor vendedor = vendedorRepository.getByName(
        vendedorField.getValue().toString());

    final Produto produto = vendedor.getProdutoByName(produtoFieldValue);

    carrinhoDeComprasTabView
        .getItems()
        .add(
            new TableViewProdutoVendaItem(
                produto.getNome(),
                quantidade,
                produto.getPrecoUnitario(),
                produto.getPrecoUnitario() * quantidade));

    final Double valorTotal =
        carrinhoDeComprasTabView.getItems().stream()
            .collect(Collectors.summingDouble(TableViewProdutoVendaItem::getValorTotal));

    valorTotalField.setText("R$ " + String.format("%.2f", valorTotal));

    carrinhoDeComprasTabView.refresh();

    produtoField.setValue(null);
    quantidadeField.getValueFactory().setValue(0);

    AlertHelper.showAlert(
        AlertType.INFORMATION,
        owner,
        "Sucesso",
        "Produto adicionado com sucesso ao carrinho de compras!");
  }

  public void handleChangeVendedor(final ActionEvent actionEvent) {

    final String vendedor = (String) vendedorField.getValue();

    final ObservableList<String> produtos = FXCollections.observableArrayList();

    vendedorRepository
        .getByName(vendedor)
        .getCatalogo()
        .values()
        .forEach(
            produto -> {
              produtos.add(produto.getNome());
            });

    produtoField.setItems(produtos);

    carrinhoDeComprasTabView.getItems().clear();

    valorTotalField.setText("");
  }
}
