package net.ambulando.placeholder.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by mgerardi on 14.08.17.
 */
public class CurrencyPairTest {

    @Test(expected = IllegalArgumentException.class)
    public void testValueOfError() {
        CurrencyPair.valueOf("BTC");
    }

    @Test
    public void testValueOfBtcEur() {
        CurrencyPair currencyPair = CurrencyPair.valueOf("BTCEUR");
        Assert.assertNotNull(currencyPair);
        Assert.assertEquals("BTC", currencyPair.getBase());
        Assert.assertEquals("EUR", currencyPair.getCounter());
    }

    @Test
    public void testValueOfBtcAtEur() {
        CurrencyPair currencyPair = CurrencyPair.valueOf("BTC@EUR");
        Assert.assertNotNull(currencyPair);
        Assert.assertEquals("BTC", currencyPair.getBase());
        Assert.assertEquals("EUR", currencyPair.getCounter());
    }

    @Test
    public void testValueOfBtcSlashEur() {
        CurrencyPair currencyPair = CurrencyPair.valueOf("BTC/EUR");
        Assert.assertNotNull(currencyPair);
        Assert.assertEquals("BTC", currencyPair.getBase());
        Assert.assertEquals("EUR", currencyPair.getCounter());
    }

}
