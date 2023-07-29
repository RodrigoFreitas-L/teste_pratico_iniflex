package teste_pratico_iniflex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Principal {
  private static ArrayList<Funcionario> funcionarios =
      new ArrayList<Funcionario>();
  private final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

  public static void main(String[] args) {
    Principal principal = new Principal();

    // Inserir todos os funcionários, na mesma ordem e informações da tabela
    // acima.
    principal.setFuncionarios("Maria", "2000-10-18", "2009.44",
        "Operador");
    principal.setFuncionarios("João", "1990-05-12", "2284.38",
        "Operador");
    principal.setFuncionarios("Caio", "1961-05-02", "9836.14",
        "Coordenador");
    principal.setFuncionarios("Miguel", "1988-10-14", "19119.88",
        "Diretor");
    principal.setFuncionarios("Alice", "1995-01-05", "2234.68",
        "Recepcionista");
    principal.setFuncionarios("Heitor", "1999-11-19", "1582.72",
        "Operador");
    principal.setFuncionarios("Arthur", "1993-03-31", "4071.84",
        "Contador");
    principal.setFuncionarios("Laura", "1994-07-08", "3017.45",
        "Gerente");
    principal.setFuncionarios("Heloísa", "2003-05-24", "1606.85",
        "Eletricista");
    principal.setFuncionarios("Helena", "1996-09-02", "2799.93",
        "Gerente");

    // Remover o funcionário “João” da lista.
    principal.removeFuncionarioByName("João");

    // Imprimir todos os funcionários com todas suas informações, sendo que:
    // • informação de data deve ser exibido no formato dd/mm/aaaa;
    // • informação de valor numérico deve ser exibida no formatado com
    // separador de milhar como ponto e decimal como vírgula.
    for (Funcionario funcionario : funcionarios) {
      System.out.println(funcionario);
    }
    System.out.println();

    // Os funcionários receberam 10% de aumento de salário, atualizar a lista de
    // funcionários com novo valor.
    principal.increaseSalaries(10);

    // Agrupar os funcionários por função em um MAP, sendo a chave a “função” e
    // o valor a “lista de funcionários”. (referir-se ao método
    // groupFuncionarioByFuncao() se necessário)
    // Imprimir os funcionários, agrupados por função.
    Map<String, ArrayList<Funcionario>> funcionariosPorFuncao = principal
        .groupFuncionarioByFuncao();
    for (Map.Entry<String, ArrayList<Funcionario>> porFuncao : funcionariosPorFuncao
        .entrySet()) {
      String funcao = porFuncao.getKey();
      ArrayList<Funcionario> funcionariosDaFuncao = porFuncao.getValue();
      System.out.println(funcao + ":");
      for (Funcionario funcionario : funcionariosDaFuncao) {
        System.out.println(funcionario.getNome());
      }
      System.out.println();
    }

    // Imprimir os funcionários que fazem aniversário no mês 10 e 12.

    principal.printByMesAniversario(10);
    principal.printByMesAniversario(12);

    // Imprimir o funcionário com a maior idade, exibir os atributos: nome e
    // idade.
    principal.printFuncionarioMaisVelho();

    // Imprimir a lista de funcionários por ordem alfabética.
    principal.printOrdemAlfabetica();

    // Imprimir o total dos salários dos funcionários.
    principal.printSalarioTotal();

    // Imprimir quantos salários mínimos ganha cada funcionário, considerando
    // que o salário mínimo é R$1212.00.
    principal.calculateSalariosMinumos();
  }

  public ArrayList<Funcionario> getFuncionarios() {
    return funcionarios;
  }

  public void setFuncionarios(String nome, String data_nascimento,
      String salario, String funcao) {
    LocalDate dataNascimento = LocalDate.parse(data_nascimento);
    BigDecimal salarioDecimal = new BigDecimal(salario);
    Funcionario funcionario = new Funcionario(nome, dataNascimento,
        salarioDecimal,
        funcao);
    funcionarios.add(funcionario);
  }

  public void removeFuncionarioByName(String nome) {
    for (Funcionario funcionario : funcionarios) {
      if (funcionario.getNome().equals(nome)) {
        funcionarios.remove(funcionario);
        break;
      }
    }
  }

  public void increaseSalaries(int porcentagem) {
    BigDecimal getPercent = BigDecimal.valueOf(porcentagem).divide(BigDecimal
        .valueOf(100));
    for (Funcionario funcionario : funcionarios) {
      BigDecimal salaryIncrease = funcionario.getSalario().multiply(getPercent);
      funcionario.setSalario(funcionario.getSalario().add(salaryIncrease));
    }
  }

  public Map<String, ArrayList<Funcionario>> groupFuncionarioByFuncao() {
    Map<String, ArrayList<Funcionario>> funcionarioPorFuncao = new HashMap<>();

    for (Funcionario funcionario : funcionarios) {
      String funcao = funcionario.getFuncao();
      ArrayList<Funcionario> funcionariosDaFuncao = funcionarioPorFuncao
          .getOrDefault(funcao, new ArrayList<>());
      funcionariosDaFuncao.add(funcionario);
      funcionarioPorFuncao.put(funcao, funcionariosDaFuncao);
    }

    return funcionarioPorFuncao;
  }

  public void printByMesAniversario(int mes) {
    System.out.println("Aniversário no mês " + mes + ":");
    for (Funcionario funcionario : funcionarios) {
      if (funcionario.getData_nascimento().getMonthValue() == mes) {
        System.out.println(funcionario.getNome());
      }
    }
    System.out.println();
  }

  public void printFuncionarioMaisVelho() {
    Funcionario funcionarioMaisVelho = null;
    LocalDate dataMaisVelha = LocalDate.now();
    LocalDate dataAtual = LocalDate.now();
    int idadeFuncionario;

    for (Funcionario funcionario : funcionarios) {
      LocalDate dataNascimento = funcionario.getData_nascimento();
      if (dataNascimento.isBefore(dataMaisVelha)) {
        dataMaisVelha = dataNascimento;
        funcionarioMaisVelho = funcionario;
      }
    }

    idadeFuncionario = Period.between(dataMaisVelha, dataAtual).getYears();

    System.out.println("Funcionário mais velho:");
    System.out.println("Nome: " + funcionarioMaisVelho.getNome() + "\nIdade: "
        + idadeFuncionario);
    System.out.println();
  }

  public void printOrdemAlfabetica() {
    Collections.sort(funcionarios, (a, b) -> a.getNome().compareTo(b
        .getNome()));

    System.out.println("Funcionarios por ordem alfabética: ");
    for (Funcionario funcionario : funcionarios) {
      System.out.println(funcionario);
    }
    System.out.println();
  }

  public void printSalarioTotal() {
    BigDecimal total = BigDecimal.ZERO;

    for (Funcionario funcionario : funcionarios) {
      total = total.add(funcionario.getSalario());
    }

    System.out.println("Total de todos os salários: " + total);
  }

  public void calculateSalariosMinumos() {
    System.out.println("Quantidade de salários mínimos recebidos por pessoa: ");
    for (Funcionario funcionario : funcionarios) {
      BigDecimal salariosMinimos = funcionario.getSalario().divide(
          SALARIO_MINIMO, 2, RoundingMode.HALF_DOWN);
      System.out.println(funcionario.getNome() + ": " + salariosMinimos
          + " salários mínimos");
    }
  }

}
