package jp.co.systena.tigerscave.shoppingcart.domain.model;

interface Expression {
  Money reduce(Bank bank, String to);
}
