package org.csc301;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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
        expectedIslands = new Grid(width, height, landPercent);

        assertEquals(th.height, height);
        assertEquals(th.width, width);
        assertEquals(th.landPercent, landPercent);
        assertEquals(th.sonars, sonars);
        assertEquals(th.range, range);
        assertEquals(expectedIslands.width, width);
        assertEquals(expectedIslands.height, height);
        assertEquals(expectedIslands.landPercent, landPercent);
        assertTrue(this.state.equals(state));
	}

    // TODO this testcase...I don't know what to test...
    @Test(timeout=100)
    //@Description(description = "processCommand(): SONAR")
    public void testProcessCommand0() {
        TreasureHunt th = new TreasureHunt();
        th.processCommand("SONAR");
    }
}
