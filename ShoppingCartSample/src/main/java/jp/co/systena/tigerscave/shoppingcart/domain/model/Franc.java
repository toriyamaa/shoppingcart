package jp.co.systena.tigerscave.shoppingcart.domain.model;

class Franc {

  private int amount;
  Franc(int amount) {
    this.amount = amount;
  }

  Franc times(int multiplier) {
    return new Franc(amount * multiplier);
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
    Franc other = (Franc) obj;
    if (amount != other.amount)
      return false;
    return true;
  }

}
