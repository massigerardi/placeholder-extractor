package net.ambulando.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by mgerardi on 14.08.17.
 */
@Slf4j
public class PlaceholderExtractorTest {

    private PlaceholderExtractor extractor;

    @Before
    public void before(){
        extractor = new PlaceholderExtractor();
    }

    @Test
    public void testExtractHelpTemplate() throws Exception {
        Map<String, Object> results = extractor.getArguments(
            "help",
            "help");
        Assert.assertFalse(results.isEmpty());
        Assert.assertEquals("help", results.get("command"));
    }

    @Test
    public void testExtractStatusTemplate() throws Exception {
        Map<String, Object> results = extractor.getArguments(
            "status",
            "status");
        Assert.assertFalse(results.isEmpty());
        Assert.assertEquals("status", results.get("command"));
    }

    @Test
    public void testExtractStatusClosedTemplate() throws Exception {
        Map<String, Object> results = extractor.getArguments(
            "status closed",
            "status closed");
        Assert.assertFalse(results.isEmpty());
        Assert.assertEquals("status closed", results.get("command"));
    }

    @Test
    public void testExtractStatusWrongTemplate() throws Exception {
        Map<String, Object> results = extractor.getArguments(
            "status",
            "status {id:alfanumeric}");
        Assert.assertTrue(results.isEmpty());
        Assert.assertNull(results.get("id"));
    }

    @Test
    public void testExtractLimitWithBtcEurSimpleForce() throws Exception {
        Map<String, Object> results = extractor.getArguments(
            "buy 100.01BTC@10.0EUR!",
            "buy {amount:double}{base:currency}@{price:double}{counter:currency}!");
        Assert.assertFalse(results.isEmpty());
        Assert.assertEquals("BTC", results.get("base"));
        Assert.assertEquals("EUR", results.get("counter"));
        Assert.assertEquals(10.0d, (double)results.get("price"), 0.0d);
        Assert.assertEquals(100.01d, (double)results.get("amount"), 0.0d);
    }

    @Test
    public void testExtractLimitWithBtcEurSimpleMarket() throws Exception {
        Map<String, Object> results = extractor.getArguments(
            "buy 100.01BTCEUR",
            "buy {amount:double}{currencyPair:currencyPair}");
        Assert.assertFalse(results.isEmpty());
        Assert.assertEquals("BTCEUR", results.get("currencyPair").toString());
        Assert.assertEquals(100.01d, (double)results.get("amount"), 0.0d);
    }

    @Test
    public void testExtractLimitWithBtcEurSimpleForceWrong() throws Exception {
        Map<String, Object> results = extractor.getArguments(
            "buy 100.01BTC@10.0EUR",
            "buy {amount:double}{base:currency}@{price:double}{counter:currency}!");
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void testExtractLimitWithBtcSlashEur() throws Exception {
        Map<String, Object> results = extractor.getArguments(
            "buy 100.01BTC/EUR @ limit 10.0",
            "buy {amount:DOUBLE}{pair:CURRENCYPAIR} @ limit {price:DOUBLE}");
        Assert.assertFalse(results.isEmpty());
        Assert.assertEquals("BTCEUR", results.get("pair").toString());
        Assert.assertEquals(10.0d, (double)results.get("price"), 0.0d);
        Assert.assertEquals(100.01d, (double)results.get("amount"), 0.0d);
    }

    @Test
    public void testExtractLimitWithBtcAtEur() throws Exception {
        Map<String, Object> results = extractor.getArguments(
                "buy 100.01BTC@EUR @ limit 10.0",
                "buy {amount:DOUBLE}{pair:CURRENCYPAIR} @ limit {price:DOUBLE}");
        Assert.assertFalse(results.isEmpty());
        Assert.assertEquals("BTCEUR", results.get("pair").toString());
        Assert.assertEquals(10.0d, (double)results.get("price"), 0.0d);
        Assert.assertEquals(100.01d, (double)results.get("amount"), 0.0d);
    }

    @Test
    public void testExtractLimitWithBtcEur() throws Exception {
        Map<String, Object> results = extractor.getArguments(
                "buy 100.01BTCEUR @ limit 10.0",
                "buy {amount:DOUBLE}{pair:CURRENCYPAIR} @ limit {price:DOUBLE}");
        Assert.assertFalse(results.isEmpty());
        Assert.assertEquals("BTCEUR", results.get("pair").toString());
        Assert.assertEquals(10.0d, (double)results.get("price"), 0.0d);
        Assert.assertEquals(100.01d, (double)results.get("amount"), 0.0d);
    }

    @Test
    public void testExtractMarket() throws Exception {
        Map<String, Object> results = extractor.getArguments(
                "buy 100.01BTCEUR @ market",
                "buy {amount:double}{pair:currencyPair} @ market");
        Assert.assertFalse(results.isEmpty());
        Assert.assertEquals("BTCEUR", results.get("pair").toString());
        Assert.assertNull(results.get("price"));
        Assert.assertEquals(100.01d, (double)results.get("amount"), 0.0d);
    }

    @Test
    public void testExtractStrategy() throws Exception {
        Map<String, Object> results = extractor.getArguments(
                "run strategy abcre-abd-hgng",
                "run strategy {strategy:ALFANUMERIC}");
        Assert.assertFalse(results.isEmpty());
        Assert.assertEquals("abcre-abd-hgng", results.get("strategy"));
    }


}
