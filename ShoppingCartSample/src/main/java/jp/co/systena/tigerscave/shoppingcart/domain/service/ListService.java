package jp.co.systena.tigerscave.shoppingcart.domain.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.co.systena.tigerscave.shoppingcart.domain.model.Cart;
import jp.co.systena.tigerscave.shoppingcart.domain.model.Item;
import jp.co.systena.tigerscave.shoppingcart.domain.model.PurchaseHistory;
import jp.co.systena.tigerscave.shoppingcart.domain.repository.ItemRepository;
import jp.co.systena.tigerscave.shoppingcart.domain.repository.PurchaseHistoryRepository;

/**
 * The Class ListService.
 */
@Service
public class ListService {

  private final ItemRepository itemRepository;

  private final PurchaseHistoryRepository purchaseHistoryRepository;

  public ListService(ItemRepository itemRepository, PurchaseHistoryRepository purchaseHistoryRepository) {
    this.itemRepository = itemRepository;
    this.purchaseHistoryRepository = purchaseHistoryRepository;
  }

  /**
   * 商品一覧取得
   *
   * @return 商品一覧
   */
  public Map<Integer, Item> getItemList() {

    return itemRepository.itemList()
        .stream()
        .collect(Collectors.toMap(
            e -> e.getItemId(), e -> e, (e1, e2) -> e1, LinkedHashMap::new));

  }

  /**
   * カート内の商品を購入する
   *
   * @param userId ユーザID
   * @param cart {@link Cart}
   * @param itemListMap 商品一覧
   */
  @Transactional
  public void purchase(int userId, Cart cart, Map<Integer, Item> itemListMap) {

    purchaseHistoryRepository.addPurchaseHistory(
        new PurchaseHistory(userId, cart.getOrderList(), itemListMap));

  }

  /**
   * 購入履歴を取得する.
   *
   * @param userId ユーザID
   * @return 購入履歴
   */
  public List<PurchaseHistory> purchaseHistory(int userId) {
    return purchaseHistoryRepository.purchaseHistory(userId);
  }

}
