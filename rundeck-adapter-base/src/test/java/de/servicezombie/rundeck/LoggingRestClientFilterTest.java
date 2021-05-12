package de.servicezombie.rundeck;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.MultivaluedHashMap;

import org.junit.Test;

public class LoggingRestClientFilterTest {

	@Test
	public void testFormToString() {
		MultivaluedHashMap<String,Object> map = new MultivaluedHashMap<String, Object>();
		map.add("foo", "bar1");
		map.add("foo", "bar2");
		map.add("bar", "foo1");
		String s = map.toString();
		assertTrue(s, s.contains("foo=[bar1, bar2]"));
	}
}
