package jp.co.systena.tigerscave.shoppingcart.domain.repository;

import java.util.List;
import jp.co.systena.tigerscave.shoppingcart.domain.model.PurchaseHistory;

/**
 * The Interface PurchaseHistoryRepository.
 */
public interface PurchaseHistoryRepository {

  /**
   * 購入履歴を登録する.
   *
   * @param purchaseHistory {@link PurchaseHistory}
   */
  void addPurchaseHistory(PurchaseHistory purchaseHistory);

  /**
   * 購入履歴取得.
   *
   * @param userId ユーザID
   * @return 購入履歴リスト
   */
  List<PurchaseHistory> purchaseHistory(int userId);

}
