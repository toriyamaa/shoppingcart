package jp.co.systena.tigerscave.shoppingcart.domain.model;

class Dollar extends Money {

  Dollar(int amount) {
    this.amount = amount;
  }

   Money times(int multiplier) {
    return new Dollar(amount * multiplier);
  }

}
