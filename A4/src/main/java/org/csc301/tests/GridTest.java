package org.csc301;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.csc301.Grid;

public class GridTest {

    @Test(timeout=100)
	//@Description(description = "Grid(): default values")
	public void testConstructor() {
        int height = 1;
        int width = 2;
        int landPercent = 3;

        Grid testGrid = new Grid();
        // TODO how do I access private final variables?
	}
}
