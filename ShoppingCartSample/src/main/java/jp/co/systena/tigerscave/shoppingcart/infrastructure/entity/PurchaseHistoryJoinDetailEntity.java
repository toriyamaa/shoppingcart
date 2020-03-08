package jp.co.systena.tigerscave.shoppingcart.infrastructure.entity;

import java.time.LocalDateTime;

public class PurchaseHistoryJoinDetailEntity {

  private int purchaseHistoryId;

  private int userId;

  private LocalDateTime purchaseDateTime;

  private int itemId;

  private int itemPrice;

  private int quantity;

  public int getPurchaseHistoryId() {
    return purchaseHistoryId;
  }

  public void setPurchaseHistoryId(int purchaseHistoryId) {
    this.purchaseHistoryId = purchaseHistoryId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public LocalDateTime getPurchaseDateTime() {
    return purchaseDateTime;
  }

  public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
    this.purchaseDateTime = purchaseDateTime;
  }

  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public int getItemPrice() {
    return itemPrice;
  }

  public void setItemPrice(int itemPrice) {
    this.itemPrice = itemPrice;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

}
