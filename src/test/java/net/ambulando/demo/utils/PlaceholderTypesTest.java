package net.ambulando.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by mgerardi on 14.08.17.
 */
@Slf4j
public class PlaceholderTypesTest {

    @Test
    public void testLoad() throws Exception {
        PlaceholderTypes types = PlaceholderTypes.INSTANCE;
        Assert.assertNotNull(types);
        Assert.assertFalse(types.getTypes().isEmpty());
    }
}
