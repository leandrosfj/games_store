package br.com.gamestorebr.model.pessoa;

import br.com.gamestorebr.model.produtos.Produto;
import br.com.gamestorebr.model.transacoes.Transacao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Vendedor extends Pessoa {

  private final Map<Integer, Produto> catalogo = new HashMap();

  private final List<ContaPagarReceber> valoresAReceber = new ArrayList<>();

  private final Set<Transacao> vendas = new HashSet<>();

  public Vendedor() {

    super();
  }

  public Vendedor(final String nome, final String documento) {

    super(nome, 0., documento);
  }

  public List<ContaPagarReceber> getValoresAReceber() {

    return valoresAReceber;
  }

  public Map<Integer, Produto> getCatalogo() {

    return catalogo;
  }

  public Set<Transacao> getVendas() {

    return vendas;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Vendedor vendedor = (Vendedor) o;
    return getDocumento().equals(vendedor.getDocumento());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getDocumento());
  }

  @Override
  public String toString() {

    final StringBuilder vendas = new StringBuilder();

    this.vendas.forEach(
        transacao -> {
          vendas
              .append("    [")
              .append(transacao.getCodigo())
              .append("] - ")
              .append("Comprador: ")
              .append(transacao.getComprador().getNome())
              .append(" - Total Bruto R$ ")
              .append(transacao.getValorTotal())
              .append(" - ")
              .append(transacao.getPagamento().toString())
              .append("\n");
        });

    final StringBuilder valoresReceber = new StringBuilder();

    valoresAReceber.forEach(conta -> valoresReceber.append(conta.toString()));

    final StringBuilder catalogoString = new StringBuilder();

    catalogo.values().forEach(item -> catalogoString.append(item.toString()));

    return "Nome: "
        + getNome()
        + "\nCNPJ: "
        + getDocumento()
        + "\nSaldo: R$ "
        + getSaldo()
        + "\nVendas:\n"
        + vendas.toString()
        + "\nValores a receber:\n"
        + valoresReceber.toString()
        + "\nCatálogo:\n"
        + catalogoString.toString();
  }

  public Produto getProdutoByName(final String nomeProduto) {
    return getCatalogo().values().stream()
        .filter(produto -> produto.getNome().equals(nomeProduto))
        .findFirst()
        .orElse(null);
  }
}
