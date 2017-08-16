package net.ambulando.placeholder.utils;

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
    Assert.assertFalse(types.isEmpty());
    Assert.assertEquals(4, types.size());
    Assert.assertEquals("([a-zA-Z0-9]+)", types.get("string").getRegex());
  }
}
