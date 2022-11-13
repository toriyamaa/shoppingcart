package jp.co.systena.tigerscave.shoppingcart.domain.model;

abstract class Money {

  protected int amount;
  protected String currency;

  Money(int amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  abstract Money times(int multiplier);

  String currency() {
    return currency;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + amount;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Money other = (Money) obj;
    if (amount != other.amount)
      return false;
    return true;
  }

  static Money dollar(int amount) {
    return new Dollar(amount, "USD");
  }

  static Money franc(int amount) {
    return new Franc(amount, "CHF");
  }

}
