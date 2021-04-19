package com.huan.distributed;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.BitSet;

public class BitSetTests {

    @Test
    public void testBitSet() {
        BitSet bitSet = new BitSet(100);
        bitSet.set(10);
        for (int i = 0; i < 100; i++) {
            System.out.println(bitSet.get(i));
        }
    }

    @Test
    public void  testBooleanArray() {
        boolean[] bits = new boolean[1024];
        System.out.println(ClassLayout.parseInstance(bits).toPrintable());
    }

    @Test
    public void  testBitSetLayout() {
        BitSet bitSet = new BitSet(1024);
        System.out.println(GraphLayout.parseInstance(bitSet).toPrintable());
    }
}
