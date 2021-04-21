package br.com.gamestorebr.form.vendedor;

import br.com.gamestorebr.GameStoreBrApplication;
import br.com.gamestorebr.model.pessoa.Vendedor;
import br.com.gamestorebr.repository.VendedorRepository;
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

public class VisualizarVendedorFormController implements Initializable {

  private VendedorRepository vendedorRepository;

  @FXML public TextField nomeField;
  @FXML public TextField saldoField;
  @FXML public TextField documentoField;

  @FXML private TableView<TableViewCatalogoItem> catalogoTabView;
  @FXML public TableColumn<TableViewCatalogoItem, Integer> catalogoIdCol;
  @FXML public TableColumn<TableViewCatalogoItem, String> catalogoNomeCol;
  @FXML public TableColumn<TableViewCatalogoItem, String> catalogoPrecoUnitarioCol;

  @FXML private TableView<TableViewVendasItem> vendasRealizadasTabView;
  @FXML public TableColumn<TableViewVendasItem, String> vendasClienteCol;
  @FXML public TableColumn<TableViewVendasItem, String> vendasItensCol;
  @FXML public TableColumn<TableViewVendasItem, String> vendasPagamentoCol;
  @FXML public TableColumn<TableViewVendasItem, Double> vendasValorTotalCol;

  @FXML private TableView<TableViewPagamentosPendentesItem> pagamentosPendentesTabView;
  @FXML public TableColumn<TableViewPagamentosPendentesItem, Double> pagamentosPendentesValorCol;
  @FXML public TableColumn<TableViewPagamentosPendentesItem, String> pagamentosPendentesTipoCol;

  @FXML
  public TableColumn<TableViewPagamentosPendentesItem, String> pagamentosPendentesVencimentoCol;

  @Override
  public void initialize(final URL url, final ResourceBundle resourceBundle) {

    vendedorRepository = DataBase.getVendedorRepository();

    catalogoIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    catalogoNomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
    catalogoPrecoUnitarioCol.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));

    vendasClienteCol.setCellValueFactory(new PropertyValueFactory<>("cliente"));
    vendasItensCol.setCellValueFactory(new PropertyValueFactory<>("itens"));
    vendasPagamentoCol.setCellValueFactory(new PropertyValueFactory<>("pagamento"));
    vendasValorTotalCol.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));

    pagamentosPendentesValorCol.setCellValueFactory(new PropertyValueFactory<>("valor"));
    pagamentosPendentesTipoCol.setCellValueFactory(new PropertyValueFactory<>("vencimento"));
    pagamentosPendentesVencimentoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));
  }

  @FXML
  public void handleVoltarButtonAction(final ActionEvent actionEvent) throws IOException {
    GameStoreBrApplication.changeScene("form/vendedor/vendedor_form.fxml");
  }

  @FXML
  public void carregarVendedor(final String documento) {

    final Vendedor vendedorVisualizar =
        vendedorRepository.listAll().stream()
            .filter(vendedor -> vendedor.getDocumento().equals(documento))
            .findFirst()
            .get();

    nomeField.setText(vendedorVisualizar.getNome());
    documentoField.setText(vendedorVisualizar.getDocumento());
    saldoField.setText(vendedorVisualizar.getSaldoFormatado());

    final ObservableList<TableViewCatalogoItem> tableViewCatalogoItems =
        FXCollections.observableArrayList(
            vendedorVisualizar.getCatalogo().values().stream()
                .map(
                    produto ->
                        new TableViewCatalogoItem(
                            produto.getCodigo(),
                            produto.getNome(),
                            produto.getPrecoUnitarioFormatado()))
                .collect(Collectors.toList()));

    catalogoTabView.setItems(tableViewCatalogoItems);
    catalogoTabView.refresh();

    final ObservableList<TableViewVendasItem> tableViewVendasItems =
        FXCollections.observableArrayList(
            vendedorVisualizar.getVendas().stream()
                .map(
                    venda -> {
                      final String produtosString =
                          venda.getTransacoesItens().stream()
                              .map(transacaoItem -> transacaoItem.getProduto().getNome())
                              .collect(Collectors.joining(", "));

                      return new TableViewVendasItem(
                          venda.getComprador().getNome(),
                          venda.getPagamento().toString(),
                          produtosString,
                          venda.getValorTotal());
                    })
                .collect(Collectors.toList()));

    vendasRealizadasTabView.setItems(tableViewVendasItems);
    vendasRealizadasTabView.refresh();

    final ObservableList<TableViewPagamentosPendentesItem> tableViewPagamentosPendentesItem =
        FXCollections.observableArrayList(
            vendedorVisualizar.getValoresAReceber().stream()
                .map(
                    pagamento ->
                        new TableViewPagamentosPendentesItem(
                            pagamento.getValor(),
                            pagamento.getDataVencimentoFormatada(),
                            pagamento.getTipo()))
                .collect(Collectors.toList()));

    pagamentosPendentesTabView.setItems(tableViewPagamentosPendentesItem);
    pagamentosPendentesTabView.refresh();
  }
}
