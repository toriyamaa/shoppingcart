package jp.co.systena.tigerscave.shoppingcart.infrastructure.repository;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import jp.co.systena.tigerscave.shoppingcart.domain.model.Item;
import jp.co.systena.tigerscave.shoppingcart.domain.repository.ItemRepository;
import jp.co.systena.tigerscave.shoppingcart.infrastructure.entity.ItemEntity;

/**
 * The Class JdbcItemRepository.
 */
@Repository
public class JdbcItemRepository implements ItemRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcItemRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /**
   * 商品リストを返却する.
   *
   * @return 商品リスト
   */
  @Override
  public List<Item> itemList() {

    List<ItemEntity> itemEntities = jdbcTemplate.query("SELECT * FROM items ORDER BY item_id",
        new BeanPropertyRowMapper<ItemEntity>(ItemEntity.class));

    return itemEntities
        .stream()
        .map(e -> new Item(e.getItemId(), e.getItemName(), e.getPrice()))
        .collect(Collectors.toList());
  }

}
