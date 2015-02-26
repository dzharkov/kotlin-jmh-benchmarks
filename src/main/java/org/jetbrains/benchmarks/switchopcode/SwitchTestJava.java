package org.jetbrains.benchmarks.switchopcode;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SwitchTestJava {
    @Param({"100", "1000", "1000000"})
    int iterations;

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private int sparseSwitch(int u) {
        switch (u) {
            case 1:
                return 1;
            case 100500:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 1;
            case 8:
                return 9;
            case 9:
                return 1;
            case 10:
                return 2;
            case 11:
                return 3;
            case 12:
                return 4;
            case 13:
                return 4;
            case 14:
                return 4;
            case 15:
                return 435;
            case 16:
                return 31;
            case 17:
                return 1;
            case 18:
                return 1;
            case 19:
                return 1;
            case 20:
                return 1;
            default:
                return 5;
        }
    }

    @Benchmark
    public void testSparseSwitch(Blackhole bh) {
        int n = iterations;
        for (int i = 0; i < n; i++) {
            bh.consume(sparseSwitch(i));
        }
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private int denseSwitch(int u) {
        switch (u) {
            case 1:
                return 1;
            case (-1):
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 1;
            case 8:
                return 9;
            case 9:
                return 1;
            case 10:
                return 2;
            case 11:
                return 3;
            case 12:
                return 4;
            case 13:
                return 4;
            case 14:
                return 4;
            case 15:
                return 435;
            case 16:
                return 31;
            case 17:
                return 1;
            case 18:
                return 1;
            case 19:
                return 1;
            case 20:
                return 1;
            default:
                return 5;
        }
    }

    @Benchmark
    public void testDenseSwitch(Blackhole bh) {
        int n = iterations;
        for (int i = 0; i < n; i++) {
            bh.consume(denseSwitch(i));
        }
    }

    MyEnum[] enums;

    @Setup
    public void initEnums() {
        enums = new MyEnum[iterations];
        for (int i = 0; i < iterations; i++) {
            enums[i] = MyEnum.values()[i % MyEnum.values().length];
        }
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private int enumSwitch(MyEnum x) {
        switch (x) {
            case ITEM2: return 1;
            case ITEM4: return 2;
            case ITEM6: return 3;
            case ITEM8: return 4;
            case ITEM10: return 5;
            case ITEM12: return 6;
            case ITEM14: return 7;
            case ITEM16: return 8;
            case ITEM18: return 9;
            case ITEM20: return 10;
            case ITEM22: return 11;
            case ITEM24: return 12;
            case ITEM26: return 13;
            case ITEM28: return 14;
            case ITEM30: return 15;
            case ITEM32: return 16;
            case ITEM34: return 17;
            case ITEM36: return 18;
            case ITEM38: return 19;
            case ITEM40: return 20;
            case ITEM42: return 21;
            case ITEM44: return 22;
            case ITEM46: return 23;
            case ITEM48: return 24;
            case ITEM50: return 25;
            case ITEM52: return 26;
            case ITEM54: return 27;
            case ITEM56: return 28;
            case ITEM58: return 29;
            case ITEM60: return 30;
            case ITEM62: return 31;
            case ITEM64: return 32;
            case ITEM66: return 33;
            case ITEM68: return 34;
            case ITEM70: return 35;
            case ITEM72: return 36;
            case ITEM74: return 37;
            case ITEM76: return 38;
            case ITEM78: return 39;
            case ITEM80: return 40;
            case ITEM82: return 41;
            case ITEM84: return 42;
            case ITEM86: return 43;
            case ITEM88: return 44;
            case ITEM90: return 45;
            case ITEM92: return 46;
            case ITEM94: return 47;
            case ITEM96: return 48;
            case ITEM98: return 49;
            case ITEM100: return 50;
            default:
                return -1;
        }
    }

    @Benchmark
    public void testEnumSwitch(Blackhole bh) {
        MyEnum[] localEnums = enums;
        int n = iterations;
        for (int i = 0; i < n; i++) {
            bh.consume(enumSwitch(localEnums[i]));
        }
    }

    String[] strings;

    @Setup
    public void initStrings() {
        strings = new String[iterations];
        for (int i = 0; i < iterations; i++) {
            strings[i] = "ABCDEFGHIJKLMNO" + ((i % 50) + 1);
        }
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private int stringSwitch(String s) {
        switch(s) {
            case "ABCDEFGHIJKLMNO1": return 1;
            case "ABCDEFGHIJKLMNO2": return 2;
            case "ABCDEFGHIJKLMNO3": return 3;
            case "ABCDEFGHIJKLMNO4": return 4;
            case "ABCDEFGHIJKLMNO5": return 5;
            case "ABCDEFGHIJKLMNO6": return 6;
            case "ABCDEFGHIJKLMNO7": return 7;
            case "ABCDEFGHIJKLMNO8": return 8;
            case "ABCDEFGHIJKLMNO9": return 9;
            case "ABCDEFGHIJKLMNO10": return 10;
            case "ABCDEFGHIJKLMNO11": return 11;
            case "ABCDEFGHIJKLMNO12": return 12;
            case "ABCDEFGHIJKLMNO13": return 13;
            case "ABCDEFGHIJKLMNO14": return 14;
            case "ABCDEFGHIJKLMNO15": return 15;
            case "ABCDEFGHIJKLMNO16": return 16;
            case "ABCDEFGHIJKLMNO17": return 17;
            case "ABCDEFGHIJKLMNO18": return 18;
            case "ABCDEFGHIJKLMNO19": return 19;
            case "ABCDEFGHIJKLMNO20": return 20;
            case "ABCDEFGHIJKLMNO21": return 21;
            case "ABCDEFGHIJKLMNO22": return 22;
            case "ABCDEFGHIJKLMNO23": return 23;
            case "ABCDEFGHIJKLMNO24": return 24;
            case "ABCDEFGHIJKLMNO25": return 25;
            case "ABCDEFGHIJKLMNO26": return 26;
            case "ABCDEFGHIJKLMNO27": return 27;
            case "ABCDEFGHIJKLMNO28": return 28;
            case "ABCDEFGHIJKLMNO29": return 29;
            case "ABCDEFGHIJKLMNO30": return 30;
            case "ABCDEFGHIJKLMNO31": return 31;
            case "ABCDEFGHIJKLMNO32": return 32;
            case "ABCDEFGHIJKLMNO33": return 33;
            case "ABCDEFGHIJKLMNO34": return 34;
            case "ABCDEFGHIJKLMNO35": return 35;
            case "ABCDEFGHIJKLMNO36": return 36;
            case "ABCDEFGHIJKLMNO37": return 37;
            case "ABCDEFGHIJKLMNO38": return 38;
            case "ABCDEFGHIJKLMNO39": return 39;
            case "ABCDEFGHIJKLMNO40": return 40;
            case "ABCDEFGHIJKLMNO41": return 41;
            case "ABCDEFGHIJKLMNO42": return 42;
            case "ABCDEFGHIJKLMNO43": return 43;
            case "ABCDEFGHIJKLMNO44": return 44;
            case "ABCDEFGHIJKLMNO45": return 45;
            case "ABCDEFGHIJKLMNO46": return 46;
            case "ABCDEFGHIJKLMNO47": return 47;
            case "ABCDEFGHIJKLMNO48": return 48;
            case "ABCDEFGHIJKLMNO49": return 49;
            case "ABCDEFGHIJKLMNO50": return 50;
            default: return -1;
        }
    }

    @Benchmark
    public void testStringSwitch(Blackhole bh) {
        String localStrings[] = strings;
        int n = iterations;
        for (int i = 0; i < n; i++) {
            bh.consume(stringSwitch(localStrings[i]));
        }
    }
}
