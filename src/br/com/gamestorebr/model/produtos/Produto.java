package br.com.gamestorebr.model.produtos;

import java.util.Objects;

public class Produto {

  private int codigo;
  private String nome;
  private Double precoUnitario;

  public Produto(final String nome, final Double precoUnitario) {

    this.nome = nome;
    this.precoUnitario = precoUnitario;
  }

  public Produto() {
    super();
  }

  @Override
  public String toString() {

    return "    [" + codigo + "] - " + nome + " - Preço: R$ " + precoUnitario + "\n";
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(final Integer codigo) {
    this.codigo = codigo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(final String nome) {
    this.nome = nome;
  }

  public Double getPrecoUnitario() {
    return precoUnitario;
  }

  public String getPrecoUnitarioFormatado() {
    return "R$ " + String.format("%.2f", precoUnitario);
  }

  public void setPrecoUnitario(final Double precoUnitario) {
    this.precoUnitario = precoUnitario;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Produto produto = (Produto) o;
    return nome.equals(produto.nome);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome);
  }
}
