package biz.naderer;

import junit.framework.TestCase;

public class TestSimpleCache extends TestCase {

	public void testGet() {
		SimpleCache cache = new SimpleCache(10, 1000);
		cache.put(123, "First test");
		cache.put(234, "Second test");
		
		assertEquals("First test", cache.get(123));
		assertEquals(2, cache.size());
		try {
			Thread.sleep(1500);
			assertNull(cache.get(123));
			assertEquals(1, cache.size());
		} catch (InterruptedException e) {
			fail("Interrupted...");
		}
		
		cache = new SimpleCache(5, SimpleCache.CACHE_FOREVER);
		cache.put(123, "Val");
		cache.put(123, "Val");
		cache.put(234, "Val");
		cache.put(567, "Val");
		cache.put(891, "Val");
		cache.put(111, "Val");
		cache.put(112, "Val");
		cache.put(113, "Val");
		assertEquals(5, cache.size());
		assertNull(cache.get(123));
		assertNull(cache.get(234));
	}

	public void testPut() {
		SimpleCache cache = new SimpleCache(5, SimpleCache.CACHE_FOREVER);
		assertNull(cache.put(123, "Val 1"));
		assertEquals("Val 1", cache.put(123, "Val 2"));
	}

	public void testClearCache() {
		SimpleCache cache = new SimpleCache(5, SimpleCache.CACHE_FOREVER);
		cache.put(123, "Val");
		cache.put(234, "Val");
		cache.put(567, "Val");
		cache.put(891, "Val");
		cache.put(111, "Val");
		assertEquals(5, cache.size());
		cache.clearCache();
		assertEquals(0, cache.size());
	}

}
