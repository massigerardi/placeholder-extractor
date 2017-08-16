package net.ambulando.placeholder.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mgerardi on 14.08.17.
 */
@Slf4j
public class PlaceholderExtractorTest {

  private PlaceholderExtractor extractor;

  @Before
  public void before() {
    extractor = new PlaceholderExtractor();
  }

  @Test
  public void testExtractSimpleTemplate() throws Exception {
    Parameters results = extractor.getArguments(
        "help",
        "help");
    Assert.assertTrue(results.isEmpty());
  }

  @Test
  public void testExtractWrongInput() throws Exception {
    Parameters results = extractor.getArguments(
        "status",
        "status {id:string}");
    Assert.assertNull(results);
  }

  @Test
  public void testExtractWrongType() throws Exception {
    Parameters results = extractor.getArguments(
        "buy 100.01BTCEUR",
        "buy {amount:integer}{currencyPair:currencypair}");
    Assert.assertNull(results);
  }

  @Test
  public void testExtractDoubleAndCustomType() throws Exception {
    Parameters results = extractor.getArguments(
        "buy 100.01BTCEUR",
        "buy {amount:double}{currencyPair:currencypair}");
    Assert.assertFalse(results.isEmpty());
    Assert.assertEquals("BTCEUR", results.get("currencyPair").toString());
    double amount = results.get("amount");
    Assert.assertEquals(100.01d, amount, 0.0d);
  }

  @Test
  public void testExtractIntegerAndCustomType() throws Exception {
    Parameters results = extractor.getArguments(
        "buy 100BTCEUR",
        "buy {amount:integer}{currencyPair:currencypair}");
    Assert.assertFalse(results.isEmpty());
    Assert.assertEquals("BTCEUR", results.get("currencyPair").toString());
    int amount = results.get("amount");
    Assert.assertEquals(100, amount);
  }

  @Test
  public void testExtractIntegerAndCustomTypeVariation() throws Exception {
    Parameters results = extractor.getArguments(
        "buy 100BTC/EUR",
        "buy {amount:integer}{currencyPair:currencypair}");
    Assert.assertFalse(results.isEmpty());
    Assert.assertEquals("BTCEUR", results.get("currencyPair").toString());
    int amount = results.get("amount");
    Assert.assertEquals(100, amount);
  }

  @Test
  public void testExtractIntegerString() throws Exception {
    Parameters results = extractor.getArguments(
        "buy 100BTC",
        "buy {amount:integer}{currency:string}");
    Assert.assertFalse(results.isEmpty());
    Assert.assertEquals("BTC", results.get("currency"));
    int amount = results.get("amount");
    Assert.assertEquals(100, amount);
  }


}
