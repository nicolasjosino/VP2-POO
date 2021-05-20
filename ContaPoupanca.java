public class ContaPoupanca extends ContaBancaria {

  public ContaPoupanca(String ID, String titular) {
    super(ID, titular);
  }

  public void sacar(double valor) throws SaqueNegativoException, SaldoNegativoException {
      if (valor <= 0)
        throw new SaqueNegativoException("Digite um valor válido de saque.");
      if (valor > getSaldo())
        throw new SaldoNegativoException("Saldo insuficiente para realizar o saque.");  
      saldo -= valor;
  }

  public void transferir(double valor, ContaBancaria destinatario) throws TransferenciaNegativaException, SaldoNegativoException {
    if (valor <= 0)
      throw new TransferenciaNegativaException("Digite um valor válido de transferência.");
    if (valor > getSaldo())
      throw new SaldoNegativoException("Saldo insuficiente para realizar a transferência.");  
    this.saldo -= valor;
    destinatario.saldo += valor;
  }
}