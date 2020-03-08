package jp.co.systena.tigerscave.shoppingcart.application.viewmodel;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import jp.co.systena.tigerscave.shoppingcart.domain.model.Item;
import jp.co.systena.tigerscave.shoppingcart.domain.model.Order;

/**
 * CartView表示用のモデル.
 */
public class OrderList {

  private List<OrderView> orders;

  private int totalPrice;

  public OrderList(List<Order> orderList, Map<Integer, Item> itemListMap) {

    this.orders = orderList
        .stream()
        .map(e -> new OrderView(
            e.getItemId(),
            itemListMap.get(e.getItemId()).getName(),
            itemListMap.get(e.getItemId()).getPrice(),
            e.getNum()))
        .collect(Collectors.toList());

    // 合計金額計算
    int totalPrice = 0;
    for (Order order : orderList) {
      if (itemListMap.containsKey(order.getItemId())) {
        totalPrice += order.getNum() * itemListMap.get(order.getItemId()).getPrice();
      }
    }
    this.totalPrice = totalPrice;

  }

  public List<OrderView> getOrders() {
    return orders;
  }

  public int getTotalPrice() {
    return totalPrice;
  }

  public static class OrderView {

    public OrderView(int itemId, String name, int price, int num) {
      this.itemId = itemId;
      this.name = name;
      this.price = price;
      this.num = num;
    }

    /** 商品ID */
    private int itemId;

    /** 商品名 */
    private String name;

    /** 価格 */
    private int price;

    /** 個数 */
    private int num;

    public int getItemId() {
      return itemId;
    }

    public String getName() {
      return name;
    }

    public int getPrice() {
      return price;
    }

    public int getNum() {
      return num;
    }

  }

}
