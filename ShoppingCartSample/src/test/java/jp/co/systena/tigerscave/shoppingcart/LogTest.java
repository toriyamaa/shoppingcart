package jp.co.systena.tigerscave.shoppingcart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogTest {

  @BeforeAll
  static void setUpBeforeClass() throws Exception {}

  @AfterAll
  static void tearDownAfterClass() throws Exception {}

  @BeforeEach
  void setUp() throws Exception {}

  @AfterEach
  void tearDown() throws Exception {}

  @Test
  void testLog() {
    //https://www.purin-it.com/spring-boot-log4j2
    //https://qiita.com/12345/items/192ff718c3942b85daed

    //log4j2.xmlの記載内容について
    //https://doc.support-dreamarts.com/SmartDB/V50/SmartDB_Ver.5.0_%E9%81%8B%E7%94%A8%E3%82%AC%E3%82%A4%E3%83%89/settings/77000/index.html
    //log4j2のバージョンについて
    //https://qiita.com/tnoho/items/5969a044ad0b914c2243
    // JSON形式のログ出力について
    //https://blog.kakakikikeke.com/2015/05/log4j2-json.html
    Logger logger = LogManager.getLogger();
    logger.debug("DEBUG LOG");
    logger.info("INFO LOG");
    logger.warn("WARNING LOG");
    logger.error("ERROR LOG");

    try {
      throw new Exception("例外のログ確認");
    } catch (Exception e) {
      logger.fatal("FATAL LOG", e);
    }
  }

}
