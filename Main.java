import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    HashMap<String, ContaBancaria> contas = new HashMap<String, ContaBancaria>();
    int opcao = 0;

    do {
      System.out.print("\n");
      System.out.println("Digite a opção desejada:");
      System.out.println("1 - Criar conta");
      System.out.println("2 - Acessar conta");
      System.out.println("0 - Sair do sistema");
      System.out.print("Sua opção: ");
      opcao = scanner.nextInt();

      try {
        if (opcao == 1) {
          int tipoConta = 0;
          do {
            System.out.print("\n");
            System.out.println("Informe o tipo de conta:");
            System.out.println("1 - Conta Corrente");
            System.out.println("2 - Conta Poupança");
            System.out.println("0 - Voltar");
            System.out.print("Sua opção: ");
            tipoConta = scanner.nextInt();

            if (tipoConta != 0) {
              System.out.print("Digite o CPF: ");
              String cadastroCPF = scanner.next();
              System.out.print("Digite o nome do titular da conta: ");
              String cadastroTitular = scanner.next();
              if (tipoConta == 1) {
                ContaCorrente cc = new ContaCorrente(cadastroCPF, cadastroTitular);
                contas.put(cadastroCPF, cc);
                System.out.println("Conta corrente cadastrada com sucesso!");
                break;
              }
              if (tipoConta == 2) {
                ContaPoupanca cp = new ContaPoupanca(cadastroCPF, cadastroTitular);
                contas.put(cadastroCPF, cp);
                System.out.println("Conta poupança cadastrada com sucesso!");
                break;
              }
            }
          } while (tipoConta != 0);
        }
        if (opcao == 2) {
          int opcaoAcesso = 0;
          System.out.println("Digite o CPF do titular da conta: ");
          String buscaCPF = scanner.next();
          ContaBancaria conta = contas.get(buscaCPF);
          if (conta != null) {
            System.out.print("\n");
            System.out.println("Bem vindo(a), " + conta.getTitular() + "!");
            do {
              System.out.println("Escolha uma opção:");
              System.out.println("1 - Saque");
              System.out.println("2 - Depósito");
              System.out.println("3 - Transferência");
              System.out.println("4 - Checar Saldo");
              System.out.println("5 - Encerrar conta");
              System.out.println("0 - Voltar");
              System.out.print("Sua opção: ");
              opcaoAcesso = scanner.nextInt();

              try {
                if (opcaoAcesso == 1) {
                  System.out.print("Digite o valor do saque: ");
                  Double valorSaque = scanner.nextDouble();
                  conta.sacar(valorSaque);
                  System.out.println("Saque concluído!");
                }
                if (opcaoAcesso == 2) {
                  System.out.print("Digite o valor do depósito: ");
                  Double valorDeposito = scanner.nextDouble();
                  conta.depositar(valorDeposito);
                  System.out.println("Depósito concluído!");
                }
                if (opcaoAcesso == 3) {
                  System.out.print("Digite o CPF da conta beneficiária: ");
                  String transferenciaCPF = scanner.next();
                  ContaBancaria beneficiario = contas.get(transferenciaCPF);
                  if (beneficiario != null) {
                    System.out.print("Digite o valor a ser transferido: ");
                    Double valorTransferencia = scanner.nextDouble();
                    conta.transferir(valorTransferencia, beneficiario);
                    System.out.println("Transferência para " + beneficiario.getTitular() + " concluída!");
                  } else {
                    System.out.println("Conta não existente!");
                  }
                }
                if (opcaoAcesso == 4) {
                  System.out.println("Saldo da conta de " + conta.getTitular() + ": " + conta.getSaldo());
                }
                if (opcaoAcesso == 5) {
                  String confirma;
                  System.out.println("Tem certeza que deseja encerrar a conta? Caso sim, Digite 'S'.");
                  confirma = scanner.next();

                  if (confirma.equals("S")) {
                    contas.remove(conta.getCPF());
                    System.out.println("Conta encerrada com sucesso!");
                    opcaoAcesso = 0;
                  } else {
                    System.out.println("Conta não encerrada!");
                  }
                }
              } catch (SaqueNegativoException e) {
                System.out.println(e.getMessage());
              } catch (SaldoNegativoException e) {
                System.out.println(e.getMessage());
              } catch (DepositoNegativoException e) {
                System.out.println(e.getMessage());
              } catch (TransferenciaNegativaException e) {
                System.out.println(e.getMessage());
              }
            } while (opcaoAcesso != 0);
          } else {
            System.out.println("Conta não cadastrada com esse CPF.");
          }
        }
      } catch (Exception e) {
        System.out.println("Ocorreu um erro: " + e.getMessage());
      }
    } while (opcao != 0);
    System.out.println("Saindo...");
    scanner.close();
  }
}