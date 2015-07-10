package ravensproject;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VisualToolsTest {
	
	VisualTools itsVT;
	@Before
	public void setUp() throws Exception {
		itsVT= new VisualTools();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCompareWithin() {
		assertEquals(itsVT.CompareWithin(5f, 5.1f, 10), 0);
		assertEquals(itsVT.CompareWithin(5f, 5.9f, 10), 1);
		assertEquals(itsVT.CompareWithin(5.9f, 5f, 10), -1);
	}

}
