package org.csc301;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.csc301.Node;
import org.csc301.HeapTest;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class NodeTest {

    // Reflection tests
	@Test(timeout=100)
    //@Description(description = "The compareTo method")
    public void testReflectionMethodCompareTo(){
        try {
            Class<?> c = Node.class;
            Method m = c.getMethod("compareTo", new Class<?>[]{Object.class});
            Type t = m.getReturnType();
            assertTrue("The return type of compareTo() should be int",
                    t.equals(int.class));
        } catch(NoSuchMethodException e) {
            fail("The compareTo() method is either private or could not be found");
        }
    }

	@Test(timeout=100)
    //@Description(description = "The equals method")
    public void testReflectionMethodEquals(){
        try {
            Class<?> c = Node.class;
            Method m = c.getMethod("equals", new Class<?>[]{Object.class});
            Type t = m.getReturnType();
            assertTrue("The return type of equals() should be boolean",
                    t.equals(boolean.class));
        } catch(NoSuchMethodException e) {
            fail("The equals() method is either private or could not be found");
        }
    }

	@Test(timeout=100)
	//@Description(description = "compareTo: node 1 is greater than node 2")
	public void testCompareTo0() {
		Node n1 = new Node(true, 0, 0);
		n1.gCost = 10;
		n1.hCost = 10;

		Node n2 = new Node(true, 1, 1);
		n2.gCost = 0;
		n2.hCost = 10;

		int test = n1.compareTo(n2);
		int expected = 1;

		assertEquals(test, expected);
	}

	@Test(timeout=100)
	//@Description(description = "compareTo: node 1 is less than node 2")
	public void testCompareTo1() {
		Node n1 = new Node(true, 0, 0);
		n1.gCost = 0;
		n1.hCost = 10;

		Node n2 = new Node(true, 1, 1);
		n2.gCost = 10;
		n2.hCost = 10;

		int test = n1.compareTo(n2);
		int expected = -1;

		assertEquals(test, expected);
	}

	@Test(timeout=100)
	//@Description(description = "compareTo: node 1 is equal to node 2")
	public void testCompareTo2() {
		Node n1 = new Node(true, 0, 0);
		n1.gCost = 0;
		n1.hCost = 10;

		Node n2 = new Node(true, 1, 1);
		n2.gCost = 0;
		n2.hCost = 10;

		int test = n1.compareTo(n2);
		int expected = 0;

		assertEquals(test, expected);
	}

	@Test(timeout=100)
	//@Description(description = "equals: node 1 is equal to node 2")
	public void testEquals0() {
		Node n1 = new Node(true, 0, 0);

		Node n2 = new Node(true, 0, 0);

		boolean test = n1.equals(n2);
		boolean expected = true;

		assertEquals(test, expected);
	}

	@Test(timeout=100)
	//@Description(description = "equals: node 1 is not equal to node 2")
	public void testEquals1() {
		Node n1 = new Node(true, 0, 0);

		Node n2 = new Node(true, 1, 1);

		boolean test = n1.equals(n2);
		boolean expected = false;

		assertEquals(test, expected);
	}
}
