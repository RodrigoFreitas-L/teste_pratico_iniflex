package teste_pratico_iniflex;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Funcionario extends Pessoa {

  public Funcionario(String nome, LocalDate data_nascimento, BigDecimal salario,
      String funcao) {
    super(nome, data_nascimento);
    this.salario = salario;
    this.funcao = funcao;
  }

  private BigDecimal salario;
  private String funcao;

  public BigDecimal getSalario() {
    return salario;
  }

  public void setSalario(BigDecimal salario) {
    this.salario = salario;
  }

  public String getFuncao() {
    return funcao;
  }

  public void setFuncao(String funcao) {
    this.funcao = funcao;
  }

  @Override
  public String toString() {
    // Formatando data para dd/mm/yyyy e criando ponto e virgula nos valores do
    // salario;
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String formattedDate = data_nascimento.format(dateFormatter);

    NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale
        .getDefault());
    numberFormatter.setMaximumFractionDigits(2);
    numberFormatter.setMinimumFractionDigits(2);
    String formattedSalary = numberFormatter.format(salario);

    return "Nome: " + nome + ", Data de Nascimento: " + formattedDate
        + ", Salário: " + formattedSalary + ", Função: " + funcao;
  }
}
