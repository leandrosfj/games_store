package br.com.gamestorebr.model.pessoa;

public abstract class Pessoa {

  private String nome;

  private double saldo;

  private String documento;

  public Pessoa() {
    super();
  }

  public Pessoa(final String nome, final double saldo, final String documento) {
    this.nome = nome;
    this.saldo = saldo;
    this.documento = documento;
  }

  public String getDocumento() {
    return documento;
  }

  public void setDocumento(final String documento) {
    this.documento = documento;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(final String nome) {
    this.nome = nome;
  }

  public Double getSaldo() {
    return saldo;
  }

  public void setSaldo(final Double saldo) {
    this.saldo = saldo;
  }

  public String getSaldoFormatado() {
    return "R$ " + String.format("%.2f", getSaldo());
  }
}
