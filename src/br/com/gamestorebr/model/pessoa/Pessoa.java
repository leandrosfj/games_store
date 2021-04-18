package br.com.gamestorebr.model.pessoa;

import java.text.DecimalFormat;

public abstract class Pessoa {

  protected static DecimalFormat df2 = new DecimalFormat("#.##");

  private String nome;

  private double saldo;

  private String documento;

  public Pessoa() {
    super();
  }

  public Pessoa (String nome, double saldo, String documento) {
    this.nome = nome;
    this.saldo = saldo;
    this.documento = documento;
  }

  public String getDocumento() {
    return this.documento;
  }

  public void setDocumento(final String documento) {
    this.documento = documento;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(final String nome) {
    this.nome = nome;
  }

  public Double getSaldo() {
    return this.saldo;
  }

  public void setSaldo(final Double saldo) {
    this.saldo = saldo;
  }

  public String getSaldoFormatado() {
    return df2.format(this.getSaldo());
  }
}
