package jp.co.systena.tigerscave.shoppingcart.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 購買履歴
 */
public class PurchaseHistory {

  private int userId;

  private LocalDateTime purchaseDateTime;

  private List<PurchaseHistoryDetail> purchaseHistoryDetails;

  public PurchaseHistory(int userId, LocalDateTime purchaseDateTime,
      List<PurchaseHistoryDetail> purchaseHistoryDetails) {
    this.userId = userId;
    this.purchaseDateTime = purchaseDateTime;
    this.purchaseHistoryDetails = purchaseHistoryDetails;
  }

  public PurchaseHistory(int userId, List<Order> orderList, Map<Integer, Item> itemListMap) {

    this.userId = userId;
    this.purchaseDateTime = LocalDateTime.now();

    this.purchaseHistoryDetails =
        orderList
        .stream()
        .map(e -> new PurchaseHistoryDetail(
            e.getItemId(),
            itemListMap.get(e.getItemId()).getPrice(),
            e.getNum()))
        .collect(Collectors.toList());

  }

  public int getUserId() {
    return userId;
  }

  public LocalDateTime getPurchaseDateTime() {
    return purchaseDateTime;
  }

  public List<PurchaseHistoryDetail> getPurchaseHistoryDetails() {
    return purchaseHistoryDetails;
  }

  public static class PurchaseHistoryDetail {

    /** 商品ID */
    private int itemId;

    /** 価格 */
    private int itemPrice;

    private int quantity;

    public PurchaseHistoryDetail(int itemId, int itemPrice, int quantity) {
      this.itemId = itemId;
      this.itemPrice = itemPrice;
      this.quantity = quantity;
    }

    public int getItemId() {
      return itemId;
    }

    public int getItemPrice() {
      return itemPrice;
    }

    public int getQuantity() {
      return quantity;
    }

  }

}
