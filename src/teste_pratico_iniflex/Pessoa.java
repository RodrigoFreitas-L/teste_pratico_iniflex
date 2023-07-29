package teste_pratico_iniflex;

import java.time.LocalDate;

public class Pessoa {

  protected String nome;
  protected LocalDate data_nascimento;

  Pessoa(String nome, LocalDate data_nascimento) {
    this.nome = nome;
    this.data_nascimento = data_nascimento;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public LocalDate getData_nascimento() {
    return data_nascimento;
  }

  public void setData_nascimento(LocalDate data_nascimento) {
    this.data_nascimento = data_nascimento;
  }

}
