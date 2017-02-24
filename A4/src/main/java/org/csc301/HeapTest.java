package org.csc301;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.csc301.Heap;

public class HeapTest {

    // Reflection tests
	@Test(timeout=100)
    //@Description(description = "The sortUp method")
    public void testReflectionMethodSortUp(){
        try {
            Class<?> c = Heap.class;
            Method m = c.getMethod("sortUp", new Class<?>[]{Object.class});
            Type t = m.getReturnType();
            assertTrue("The return type of sortUp() should be void",
                    t.equals(void.class));
        } catch(NoSuchMethodException e) {
            fail("The sortUp() method is either private or could not be found");
        }
    }

	@Test(timeout=100)
    //@Description(description = "The sortDown method")
    public void testReflectionMethodSortDown(){
        try {
            Class<?> c = Heap.class;
            Method m = c.getMethod("sortDown", new Class<?>[]{Object.class});
            Type t = m.getReturnType();
            assertTrue("The return type of sortDown() should be void",
                    t.equals(void.class));
        } catch(NoSuchMethodException e) {
            fail("The sortDown() method is either private or could not be found");
        }
    }

	@Test(timeout=100)
	//@Description(description = "sortUp: parent is less than child")
	public void testSortUp0() {
		Node n1 = new Node(true, 0, 0);
		n1.gCost = 0;
		n1.hCost = 10;

		Node n2 = new Node(true, 1, 1);
		n2.gCost = 10;
		n2.hCost = 10;

		Heap<Node> test = new Heap<>(2);
		Heap<Node> expected = new Heap<>(2);

		test.add(n1);
		test.add(n2);

		expected.items[0] = n1;
		expected.items[1] = n2;

		assertEquals(test, expected);
	}

	@Test(timeout=100)
	//@Description(description = "sortUp: parent is greater than child")
	public void testSortUp1() {
		Node n1 = new Node(true, 0, 0);
		n1.gCost = 10;
		n1.hCost = 10;

		Node n2 = new Node(true, 1, 1);
		n2.gCost = 0;
		n2.hCost = 10;

		Heap<Node> test = new Heap<>(2);
		Heap<Node> expected = new Heap<>(2);

		test.add(n1);
		test.add(n2);

		expected.items[0] = n2;
		expected.items[1] = n1;

		assertEquals(test, expected);
	}

	@Test(timeout=100)
	//@Description(description = "sortUp: parent is equal to child")
	public void testSortUp2() {
		Node n1 = new Node(true, 0, 0);
		n1.gCost = 10;
		n1.hCost = 10;

		Node n2 = new Node(true, 1, 1);
		n2.gCost = 10;
		n2.hCost = 10;

		Heap<Node> test = new Heap<>(2);
		Heap<Node> expected = new Heap<>(2);

		test.add(n1);
		test.add(n2);

		expected.items[0] = n1;
		expected.items[1] = n2;

		assertEquals(test, expected);
	}

	@Test(timeout=100)
	//@Description(description = "sortDown: left child is less than")
	public void testSortDown0() {
		Node n50 = new Node(true, 0, 0);
		n50.gCost = 0;
		n50.hCost = 50;

		Node n60 = new Node(true, 1, 1);
		n60.gCost = 10;
		n60.hCost = 50;

		Node n70 = new Node(true, 2, 2);
		n70.gCost = 20;
		n70.hCost = 50;

		Node n80 = new Node(true, 3, 3);
		n80.gCost = 80;
		n80.hCost = 50;

		Heap<Node> test = new Heap<>(4);
		Heap<Node> expected = new Heap<>(4);

		test.add(n50);
		test.add(n60);
		test.add(n70);
		test.add(n80);

		test.removeFirst();
		expected.items[0] = n60;
		expected.items[0] = n70;
		expected.items[0] = n80;

		assertEquals(test, expected);
	}

	@Test(timeout=100)
	//@Description(description = "sortDown: right child is less than")
	public void testSortDown1() {
		Node n50 = new Node(true, 0, 0);
		n50.gCost = 0;
		n50.hCost = 50;

		Node n60 = new Node(true, 1, 1);
		n60.gCost = 10;
		n60.hCost = 50;

		Node n70 = new Node(true, 2, 2);
		n70.gCost = 20;
		n70.hCost = 50;

		Node n80 = new Node(true, 3, 3);
		n80.gCost = 80;
		n80.hCost = 50;

		Heap<Node> test = new Heap<>(4);
		Heap<Node> expected = new Heap<>(4);

		test.add(n50);
		test.add(n70);
		test.add(n60);
		test.add(n80);

		test.removeFirst();
		expected.items[0] = n60;
		expected.items[0] = n70;
		expected.items[0] = n80;

		assertEquals(test, expected);
	}

	@Test(timeout=100)
	//@Description(description = "sortDown: children are equal")
	public void testSortDown2() {
		Node n50 = new Node(true, 0, 0);
		n50.gCost = 0;
		n50.hCost = 50;

		Node n60 = new Node(true, 1, 1);
		n60.gCost = 10;
		n60.hCost = 50;

		Node n70 = new Node(true, 2, 2);
		n70.gCost = 20;
		n70.hCost = 50;

		Heap<Node> test = new Heap<>(4);
		Heap<Node> expected = new Heap<>(4);

		test.add(n50);
		test.add(n60);
		test.add(n60);
		test.add(n70);

		test.removeFirst();
		expected.items[0] = n60;
		expected.items[0] = n70;
		expected.items[0] = n60;

		assertEquals(test, expected);
	}
}
