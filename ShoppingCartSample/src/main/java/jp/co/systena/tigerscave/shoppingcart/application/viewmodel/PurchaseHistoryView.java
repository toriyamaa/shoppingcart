package jp.co.systena.tigerscave.shoppingcart.application.viewmodel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import jp.co.systena.tigerscave.shoppingcart.domain.model.Item;
import jp.co.systena.tigerscave.shoppingcart.domain.model.PurchaseHistory;

/**
 * 購買履歴の表示用クラス
 */
public class PurchaseHistoryView {

  private LocalDateTime purchaseDateTime;

  private List<PurchaseHistoryDetailView> purchaseHistoryDetails;

  private int purchaseHistoryDetailCount;

  public PurchaseHistoryView(PurchaseHistory purchaseHistory, Map<Integer, Item> itemListMap) {

    this.purchaseDateTime = purchaseHistory.getPurchaseDateTime();

    this.purchaseHistoryDetails =
        purchaseHistory.getPurchaseHistoryDetails()
        .stream()
        .map(e -> new PurchaseHistoryDetailView(
            e.getItemId(),
            itemListMap.get(e.getItemId()).getName(),
            itemListMap.get(e.getItemId()).getPrice(),
            e.getQuantity()))
        .collect(Collectors.toList());

    this.purchaseHistoryDetailCount = this.purchaseHistoryDetails.size();

  }

  public LocalDateTime getPurchaseDateTime() {
    return purchaseDateTime;
  }

  public List<PurchaseHistoryDetailView> getPurchaseHistoryDetails() {
    return purchaseHistoryDetails;
  }

  public int getPurchaseHistoryDetailCount() {
    return purchaseHistoryDetailCount;
  }

  public static class PurchaseHistoryDetailView {

    /** 商品ID */
    private int itemId;

    private String itemName;

    /** 価格 */
    private int itemPrice;

    private int quantity;

    public PurchaseHistoryDetailView(int itemId, String itemName, int itemPrice, int quantity) {
      this.itemId = itemId;
      this.itemName = itemName;
      this.itemPrice = itemPrice;
      this.quantity = quantity;
    }

    public int getItemId() {
      return itemId;
    }

    public String getItemName() {
      return itemName;
    }

    public int getItemPrice() {
      return itemPrice;
    }

    public int getQuantity() {
      return quantity;
    }

  }

}
