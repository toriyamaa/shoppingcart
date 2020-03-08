package jp.co.systena.tigerscave.shoppingcart.domain.repository;

import java.util.List;
import jp.co.systena.tigerscave.shoppingcart.domain.model.Item;

/**
 * The Interface ItemRepository.
 */
public interface ItemRepository {

  List<Item> itemList();

}
