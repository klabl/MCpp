package mod.pwngu.common.main;

import mod.pwngu.lib.CollectionUtils;

import java.util.HashMap;

public class MapOptimizationTest {

    public static void main(String[] args) {

        HashMap<Test, Test> toOptimize = new HashMap<Test, Test>();

        Runtime runtime = Runtime.getRuntime();

        System.gc();
        long pre = runtime.maxMemory() - runtime.freeMemory();

        System.out.println("Used Memory pre allocating: " + pre);
        for(int i = 0; i < 5000; i++) {

            toOptimize.put(new Test("" + i), new Test("" + (i % 10)));
        }

        for(Test test : toOptimize.keySet()) {

            System.out.println("{" + test + " : " + toOptimize.get(test) + "}");
        }

        System.gc();
        long mid = runtime.maxMemory() - runtime.freeMemory();

        System.out.println("Used Memory post allocating: " + mid);
        System.out.println("Difference to pre allocation: " + (pre - mid));

        CollectionUtils.optimizeHashMap(toOptimize);

        for(Test test : toOptimize.keySet()) {

            System.out.println("{" + test + " : " + toOptimize.get(test) + "}");
        }

        System.gc();
        long post = runtime.maxMemory() - runtime.freeMemory();
        System.out.println("Used Memory post optimization: " + post);
        System.out.println("Difference to pre allocation: " + (pre - post));
        System.out.println("Optimized Memory usage: " + (mid - post));
    }

    private static class Test {

        public String value;

        private Test(String value) {

            this.value = value;
        }

        public boolean equals(Object other) {

            return value.equals(((Test)other).value);
        }

        public String toString() {

            return value + '@' + Integer.toHexString(hashCode());
        }
    }
}
