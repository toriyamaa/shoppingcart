package jp.co.systena.tigerscave.shoppingcart.infrastructure.entity;

/**
 * The Class ItemEntity.
 */
public class ItemEntity {

  /** 商品ID */
  private int itemId;

  /** 商品名 */
  private String itemName;

  /** 価格 */
  private int price;

  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

}
