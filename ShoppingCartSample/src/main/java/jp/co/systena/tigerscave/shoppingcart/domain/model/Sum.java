package jp.co.systena.tigerscave.shoppingcart.domain.model;

public class Sum implements Expression {

  Expression augend;
  Expression addend;

  Sum(Expression augend, Expression addend) {
    this.augend = augend;
    this.addend = addend;
  }

  public Money reduce(Bank bank, String to) {
    int amount = augend.reduce(bank, to).amount
        + addend.reduce(bank, to).amount;
    return new Money(amount, to);
  }

  @Override
  public Expression plus(Expression addend) {
    return new Sum(this, addend);
  }

  public Expression times(int mulitiplier) {
    return new Sum(augend.times(mulitiplier), addend.times(mulitiplier));
  }

}
