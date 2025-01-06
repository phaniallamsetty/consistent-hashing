package com.pallamsetty;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConsistentHashingTest {
    private ConsistentHashing ch;

    @Test
    public void testAddNode1() {
        ch = new ConsistentHashing(3, 4);
        ch.displayRingState();
        assertEquals(3, ch.getRingItemCount());
    }
}
