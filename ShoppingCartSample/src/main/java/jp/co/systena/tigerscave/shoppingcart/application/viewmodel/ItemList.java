package jp.co.systena.tigerscave.shoppingcart.application.viewmodel;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import jp.co.systena.tigerscave.shoppingcart.domain.model.Item;

/**
 * ListView表示用のモデル.
 */
public class ItemList {

  private List<ItemView> items;

  public ItemList(Map<Integer, Item> itemList) {
    this.items = itemList.values()
        .stream()
        .map(e -> new ItemView(e.getItemId(), e.getName(), e.getPrice()))
        .collect(Collectors.toList());
  }

  public List<ItemView> getItems() {
    return items;
  }

  public static class ItemView {

    public ItemView(int itemId, String name, int price) {
      this.itemId = itemId;
      this.name = name;
      this.price = price;
    }

    /** 商品ID */
    private int itemId;

    /** 商品名 */
    private String name;

    /** 価格 */
    private int price;

    public int getItemId() {
      return itemId;
    }

    public String getName() {
      return name;
    }

    public int getPrice() {
      return price;
    }

  }

}
