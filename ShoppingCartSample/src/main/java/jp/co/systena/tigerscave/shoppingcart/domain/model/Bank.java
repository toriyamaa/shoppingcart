package jp.co.systena.tigerscave.shoppingcart.domain.model;

class Bank {

  Money reduce(Expression source, String to) {
    return source.reduce(to);
  }

}
