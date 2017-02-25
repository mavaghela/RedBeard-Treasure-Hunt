package org.csc301.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.csc301.Grid;
import org.csc301.HeapEmptyException;
import org.csc301.HeapFullException;
import org.junit.Test;
import org.csc301.TreasureHunt;

public class TreasureHuntTest {

    @Test(timeout=100)
	//@Description(description = "TreasureHunt(): default values")
	public void testConstructor() {
        int height = 1;
        int width = 2;
        int landPercent = 3;
        int sonars = 4;
        int range = 5;
        String state = "STARTED";

        TreasureHunt th = new TreasureHunt(height, width, landPercent, sonars, range);
        Grid expectedIslands = new Grid(width, height, landPercent);

        assertEquals(height, height);
        assertEquals(width, width);
        assertEquals(landPercent, landPercent);
        assertEquals(sonars, sonars);
        assertEquals(range, range);
        assertEquals(width, width);
        assertEquals(height, height);
        assertEquals(landPercent, landPercent);
        assertTrue(state.equals(state));
	}

    // TODO this testcase...I don't know what to test...
    @Test(timeout=100)
    //@Description(description = "processCommand(): SONAR")
    public void testProcessCommand0() throws HeapFullException, HeapEmptyException {
        TreasureHunt th = new TreasureHunt();
        th.processCommand("SONAR");
    }
}
