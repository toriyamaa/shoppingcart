package jp.co.systena.tigerscave.shoppingcart.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import jp.co.systena.tigerscave.shoppingcart.domain.model.PurchaseHistory;
import jp.co.systena.tigerscave.shoppingcart.domain.model.PurchaseHistory.PurchaseHistoryDetail;
import jp.co.systena.tigerscave.shoppingcart.domain.repository.PurchaseHistoryRepository;
import jp.co.systena.tigerscave.shoppingcart.infrastructure.entity.PurchaseHistoryJoinDetailEntity;

@Repository
public class JdbcPurchaseHistoryRepository implements PurchaseHistoryRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcPurchaseHistoryRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void addPurchaseHistory(PurchaseHistory purchaseHistory) {

    //購入履歴登録
    Integer id = jdbcTemplate.queryForObject(
        "INSERT INTO purchase_history (user_id, purchase_date_time) VALUES (?, ?) RETURNING purchase_history_id",
        Integer.class,
        purchaseHistory.getUserId(),
        purchaseHistory.getPurchaseDateTime());

    //購入履歴詳細登録
    for(PurchaseHistoryDetail detail : purchaseHistory.getPurchaseHistoryDetails()) {

      jdbcTemplate.update(
          "INSERT INTO purchase_history_detail (purchase_history_id, item_id, item_price, quantity) VALUES (?, ?, ?, ?) ",
          id,
          detail.getItemId(),
          detail.getItemPrice(),
          detail.getQuantity());

    }

  }

  @Override
  public List<PurchaseHistory> purchaseHistory(int userId) {

    //購入履歴取得
    List<PurchaseHistoryJoinDetailEntity> histories = jdbcTemplate.query(
        "SELECT * "
        + "FROM purchase_history ph "
        + "INNER JOIN purchase_history_detail phd "
        + "ON ph.purchase_history_id = phd.purchase_history_id "
        + "WHERE ph.user_id = ? "
        + "ORDER BY ph.purchase_date_time DESC ",
        new BeanPropertyRowMapper<PurchaseHistoryJoinDetailEntity>(PurchaseHistoryJoinDetailEntity.class),
        userId);

    Map<LocalDateTime, List<PurchaseHistoryJoinDetailEntity>> historyMap = histories.stream()
        .collect(Collectors.groupingBy(PurchaseHistoryJoinDetailEntity::getPurchaseDateTime,
            LinkedHashMap::new, Collectors.toList()));

    List<PurchaseHistory> purchaseHistories = new ArrayList<PurchaseHistory>();
    for(Map.Entry<LocalDateTime, List<PurchaseHistoryJoinDetailEntity>> entry: historyMap.entrySet()) {

      purchaseHistories.add(
          new PurchaseHistory(
              userId,
              entry.getKey(),
              entry.getValue().stream()
              .map(
                  e -> new PurchaseHistoryDetail(
                      e.getItemId(), e.getItemPrice(), e.getQuantity()))
              .collect(Collectors.toList()))
          );

    }

    return purchaseHistories;
  }

}
