package io.kuo.tahoe.resource;

import io.kuo.tahoe.test.BaseTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by nikog on 1/30/2015.
 */
public class UserResourceTest extends BaseTest {
    @Test
    public void testGetUser() {
        assertEquals("Got it!", target("user").request().get(String.class));
    }
}
