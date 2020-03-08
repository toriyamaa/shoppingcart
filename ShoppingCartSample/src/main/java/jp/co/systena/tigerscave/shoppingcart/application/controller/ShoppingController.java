package jp.co.systena.tigerscave.shoppingcart.application.controller;

import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.shoppingcart.application.form.DeleteForm;
import jp.co.systena.tigerscave.shoppingcart.application.form.ListForm;
import jp.co.systena.tigerscave.shoppingcart.application.viewmodel.ItemList;
import jp.co.systena.tigerscave.shoppingcart.application.viewmodel.OrderList;
import jp.co.systena.tigerscave.shoppingcart.application.viewmodel.PurchaseHistoryView;
import jp.co.systena.tigerscave.shoppingcart.domain.model.Cart;
import jp.co.systena.tigerscave.shoppingcart.domain.model.Item;
import jp.co.systena.tigerscave.shoppingcart.domain.service.ListService;

/**
 * The Class ShoppingController.
 */
@Controller // Viewあり。Viewを返却するアノテーション
public class ShoppingController {

  private final HttpSession session; // セッション管理

  private final ListService listService; // サービスクラス

  public ShoppingController(HttpSession session, ListService listService) {
    this.session = session;
    this.listService = listService;
  }

  /**
   * 商品一覧画面表示
   *
   * @param mav the mav
   * @return the model and view
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET) // URLとのマッピング
  public ModelAndView list(ModelAndView mav) {

    // 商品一覧取得
    Map<Integer, Item> itemList = listService.getItemList();

    // テンプレートに渡す内容を設定
    mav.addObject("itemList", new ItemList(itemList));

    // Viewのテンプレート名を設定
    mav.setViewName("ListView");

    return mav;
  }

  /**
   * 注文内容をカートに追加する
   *
   * @param mav the mav
   * @param listForm the list form
   * @param bindingResult the binding result
   * @return the model and view
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST) // URLとのマッピング
  public ModelAndView order(ModelAndView mav, @Validated ListForm listForm,
      BindingResult bindingResult) {

    if (bindingResult.getAllErrors().size() > 0) {
      // リクエストパラメータにエラーがある場合は商品一覧画面を表示

      Map<Integer, Item> itemListMap = listService.getItemList();
      mav.addObject("itemList", new ItemList(itemListMap));

      // Viewのテンプレート名を設定
      mav.setViewName("ListView");

      return mav;

    }

    // 注文内容をカートに追加
    Cart cart = getCart();
    cart.addOrder(listForm.getItemId(), listForm.getNum());

    // データをセッションへ保存
    session.setAttribute("cart", cart);

    return new ModelAndView("redirect:/cart"); // リダイレクト
  }

  /**
   * カートの内容を表示する
   *
   * @param mav the mav
   * @return the model and view
   */
  @RequestMapping(value = "/cart", method = RequestMethod.GET) // URLとのマッピング
  public ModelAndView cart(ModelAndView mav) {

    // セッションからカートを取得し、テンプレートに渡す
    // 商品一覧をテンプレートに渡す。※商品名、価格を表示するため
    mav.addObject(
        "orderList",
        new OrderList(getCart().getOrderList(), listService.getItemList()));

    // Viewのテンプレート名設定
    mav.setViewName("CartView");

    return mav;
  }

  /**
   * 注文内容削除する
   *
   * @param mav the mav
   * @param deleteForm the delete form
   * @param bindingResult the binding result
   * @return the model and view
   */
  @RequestMapping(value = "/cart", method = RequestMethod.POST) // URLとのマッピング
  public ModelAndView deleteOrder(ModelAndView mav, @Validated DeleteForm deleteForm,
      BindingResult bindingResult) {

    if (bindingResult.getAllErrors().size() == 0) {
      //リクエストパラメータにエラーがなければ、削除処理行う

      // カートから商品を削除
      Cart cart = getCart();
      cart.deleteOrder(deleteForm.getItemId());

      // データをセッションへ保存
      session.setAttribute("cart", cart);
    }

    return new ModelAndView("redirect:/cart"); // リダイレクト
  }

  /**
   * 商品購入.
   *
   * @param mav the mav {@link ModelAndView}
   * @return the model and view {@link ModelAndView}
   */
  @RequestMapping(value = "/purchase", method = RequestMethod.POST) // URLとのマッピング
  public ModelAndView purchaseOrder(ModelAndView mav) {

    Cart cart = getCart();
    Map<Integer, Item> itemListMap = listService.getItemList();

    // カートの商品を購入
    listService.purchase(10000, cart, itemListMap);

    // セッションのカート情報初期化
    session.setAttribute("cart", null);

    // 購入一覧をテンプレートに渡す。
    mav.addObject(
        "orderList",
        new OrderList(cart.getOrderList(), itemListMap));

    // Viewのテンプレート名設定
    mav.setViewName("PurchaseCompletedView");

    return mav;

  }

  @RequestMapping(value = "/purchaseHistory", method = RequestMethod.GET) // URLとのマッピング
  public ModelAndView purchaseHistory(ModelAndView mav) {

    Map<Integer, Item> itemListMap = listService.getItemList();

    // 購入履歴一覧をテンプレートに渡す。
    mav.addObject(
        "purchaseHistoryList",
        listService.purchaseHistory(10000)
        .stream()
        .map(e -> new PurchaseHistoryView(e, itemListMap))
        .collect(Collectors.toList()));

    // Viewのテンプレート名設定
    mav.setViewName("PurchaseHistoryView");

    return mav;

  }

  /**
   * セッションからカートを取得する
   *
   * @return the cart
   */
  private Cart getCart() {
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
      cart = new Cart();
      session.setAttribute("cart", cart);
    }

    return cart;
  }

}
