package jp.co.systena.tigerscave.shoppingcart.domain.model;

class Money {
  protected int amount;

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

}
