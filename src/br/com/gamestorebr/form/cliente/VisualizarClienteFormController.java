package br.com.gamestorebr.form.cliente;

import br.com.gamestorebr.GameStoreBrApplication;
import br.com.gamestorebr.model.pessoa.Comprador;
import br.com.gamestorebr.repository.CompradorRepository;
import br.com.gamestorebr.util.DataBase;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class VisualizarClienteFormController implements Initializable {

  private CompradorRepository compradorRepository;

  @FXML public TextField nomeField;
  @FXML public TextField saldoField;
  @FXML public TextField documentoField;

  @FXML private TableView<TableViewComprasItem> comprasRealizadasTabView;
  @FXML public TableColumn<TableViewComprasItem, String> comprasVendedorCol;
  @FXML public TableColumn<TableViewComprasItem, String> comprasItensCol;
  @FXML public TableColumn<TableViewComprasItem, String> comprasPagamentoCol;
  @FXML public TableColumn<TableViewComprasItem, Double> comprasValorTotalCol;

  @FXML private TableView<TableViewContasAPagarItem> contasAPagarTabView;
  @FXML public TableColumn<TableViewContasAPagarItem, Double> contasAPagarValorCol;
  @FXML public TableColumn<TableViewContasAPagarItem, String> contasAPagarTipoCol;
  @FXML public TableColumn<TableViewContasAPagarItem, String> contasAPagarVencimentoCol;

  @Override
  public void initialize(final URL url, final ResourceBundle resourceBundle) {

    compradorRepository = DataBase.getCompradorRepository();

    comprasVendedorCol.setCellValueFactory(new PropertyValueFactory<>("vendedor"));
    comprasItensCol.setCellValueFactory(new PropertyValueFactory<>("itens"));
    comprasPagamentoCol.setCellValueFactory(new PropertyValueFactory<>("pagamento"));
    comprasValorTotalCol.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));

    contasAPagarValorCol.setCellValueFactory(new PropertyValueFactory<>("valor"));
    contasAPagarTipoCol.setCellValueFactory(new PropertyValueFactory<>("vencimento"));
    contasAPagarVencimentoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));
  }

  @FXML
  public void handleVoltarButtonAction(final ActionEvent actionEvent) throws IOException {
    GameStoreBrApplication.changeScene("form/cliente/cliente_form.fxml");
  }

  @FXML
  public void carregarCliente(final String documento) {

    final Comprador cliente =
        compradorRepository.listAll().stream()
            .filter(comprador -> comprador.getDocumento().equals(documento))
            .findFirst()
            .get();

    nomeField.setText(cliente.getNome());
    documentoField.setText(cliente.getDocumento());
    saldoField.setText(cliente.getSaldoFormatado());

    final ObservableList<TableViewComprasItem> tableViewComprasItems =
        FXCollections.observableArrayList(
            cliente.getCompras().stream()
                .map(
                    compra -> {
                      final String produtosString =
                          compra.getTransacoesItens().stream()
                              .map(transacaoItem -> transacaoItem.getProduto().getNome())
                              .collect(Collectors.joining(", "));

                      return new TableViewComprasItem(
                          compra.getVendedor().getNome(),
                          compra.getPagamento().toString(),
                          produtosString,
                          compra.getValorTotal());
                    })
                .collect(Collectors.toList()));

    comprasRealizadasTabView.setItems(tableViewComprasItems);
    comprasRealizadasTabView.refresh();

    final ObservableList<TableViewContasAPagarItem> tableViewPagamentosPendentesItem =
        FXCollections.observableArrayList(
            cliente.getValoresAPagar().stream()
                .map(
                    pagamento ->
                        new TableViewContasAPagarItem(
                            pagamento.getValor(),
                            pagamento.getDataVencimentoFormatada(),
                            pagamento.getTipo()))
                .collect(Collectors.toList()));

    contasAPagarTabView.setItems(tableViewPagamentosPendentesItem);
    contasAPagarTabView.refresh();
  }
}
