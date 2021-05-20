public class ContaBancaria {
  
  private String ID;
  private String titular;
  protected double saldo;

  public ContaBancaria(String ID, String titular) {
    this.ID = ID;
    this.titular = titular;
    this.saldo = 0;
  }

  public String getID() {
    return ID;
  }

  public String getTitular() {
    return titular;
  }

  protected double getSaldo() {
    return saldo;
  }

  public void setID(String ID) {
    this.ID = ID;
  }

  public void setTitular(String titular) {
    this.titular = titular;
  }

  public void depositar(double valor) throws DepositoNegativoException {
    if (valor <= 0)
      throw new DepositoNegativoException("Digite um valor maior que zero para depósito.");
    saldo += valor;
  }

  public void sacar(double valor) throws SaqueNegativoException, SaldoNegativoException {
    if (valor <= 0)
      throw new SaqueNegativoException("Digite um valor válido de saque.");
    saldo -= valor;
  }

  public void transferir(double valor, ContaBancaria destinatario) throws TransferenciaNegativaException, SaldoNegativoException {
    if (valor <= 0)
      throw new TransferenciaNegativaException("Digite um valor maior que zero");
    this.saldo -= valor;
    destinatario.saldo += valor;
  } 
}