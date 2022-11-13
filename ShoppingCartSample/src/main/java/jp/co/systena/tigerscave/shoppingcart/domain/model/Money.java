package jp.co.systena.tigerscave.shoppingcart.domain.model;

class Money implements Expression {

  protected int amount;
  protected String currency;

  Money(int amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  Expression times(int multiplier) {
    return new Money(amount * multiplier, currency);
  }

  String currency() {
    return currency;
  }

  static Money dollar(int amount) {
    return new Money(amount, "USD");
  }

  static Money franc(int amount) {
    return new Money(amount, "CHF");
  }

  public Expression plus(Expression addend) {
    return new Sum(this, addend);
  }

  public Money reduce(Bank bank, String to) {
    int rate = bank.rate(currency, to);
    return new Money(amount / rate, to);
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
    Money money = (Money) obj;
    return amount == money.amount
        && currency().equals(money.currency());
  }

  @Override
  public String toString() {
    return amount + " " + currency;
  }

}
