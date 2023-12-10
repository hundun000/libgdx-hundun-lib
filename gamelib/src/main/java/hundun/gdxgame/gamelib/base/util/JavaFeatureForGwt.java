package hundun.gdxgame.gamelib.base.util;

import java.util.*;
import java.util.stream.Collector;

/**
 * @author hundun
 * Created on 2022/05/16
 */
public class JavaFeatureForGwt {

    public static void main(String[] args) {
        System.out.println(stringFormat("%s%s%s %s%s%s", "1", "2", "3", "4", "5", "6"));
    }
    
    public static class NumberFormat {
        int integerBit;
        int decimalBit;

        private NumberFormat(int integerBit, int decimalBit) {
            super();
            this.integerBit = integerBit;
            this.decimalBit = decimalBit;
        }
        
        public String format(double value) {
            String string = String.valueOf(value);
            String[] parts = string.split("\\.");
            String integerPart;
            String decimalPart;
            if (parts.length == 1) {
                integerPart = parts[0];
                decimalPart = "";
            } else {
                integerPart = parts[0];
                decimalPart = parts[1];
            }
            while (integerPart.length() < integerBit) {
                integerPart = "0" + integerPart;
            }
            while (decimalPart.length() < decimalBit) {
                decimalPart = decimalPart + "0";
            }
            if (decimalPart.length() > decimalBit) {
                decimalPart = decimalPart.substring(0, decimalBit);
            }
            if (!decimalPart.isEmpty()) {
                decimalPart = "." + decimalPart;
            }
            return integerPart + decimalPart;
        }
        
        public static void main(String[] args) {
            NumberFormat format;
            
            format = new NumberFormat(2, 2);
            System.out.println(format.format(42));
            System.out.println(format.format(0.1));
            System.out.println(format.format(114.514));
            
            format = new NumberFormat(1, 0);
            System.out.println(format.format(114.514));
        }

        public static NumberFormat getFormat(int integerBit, int decimalBit) {
            NumberFormat result = new NumberFormat(decimalBit, decimalBit);
            return result;
        }
    }

    
    public static String stringRepeat(String text, int time) {
        final StringBuilder buffer= new StringBuilder();
        for (int i = 0; i < time; i++) {
            buffer.append(text);
        }
        return buffer.toString();
    }
    
    public static String stringFormat(String format, Object... args) {
        
        // try type %s
        String delimiter = "%s";
        for (int i = 0; i < args.length; i++) {
            format = format.replaceFirst(delimiter, args[i] != null ? args[i].toString() : "null");
        }
        
        // try type {i}
        for (int i = 0; i < args.length; i++) {
            format = format.replace("{" + i + "}", args[i] != null ? args[i].toString() : "null");
        }

        return format;
    }
    
    public static Map<String, Integer> mapOf() {
        return new HashMap<>(0);
    }
    
    public static <K, V> Map<K, V> mapOf(K k1, V v1) {
        Map<K, V> map = new HashMap<>(2);
        map.put(k1, v1);
        return map;
    }

    public static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2) {
        Map<K, V> map = new HashMap<>(2);
        map.put(k1, v1);
        map.put(k2, v2);
        return map;
    }

    public static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3) {
        Map<K, V> map = new HashMap<>(3);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        return map;
    }

    public static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        Map<K, V> map = new HashMap<>(4);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        return map;
    }

    public static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        Map<K, V> map = new HashMap<>(5);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        return map;
    }
    
    @SafeVarargs
    public static <T> List<T> listOf(T... items) {
        List<T> list = new ArrayList<>(items.length);
        for (int i = 0; i < items.length; i++) {
            list.add(items[i]);
        }
        return list;
    }

    public static <T> List<T> arraysAsList(T item) {
        List<T> list = new ArrayList<>(1);
        list.add(item);
        return list;
    }

    public static <T> List<T> arraysAsList(T item1, T item2) {
        List<T> list = new ArrayList<>(2);
        list.add(item1);
        list.add(item2);
        return list;
    }

    public static <T> List<T> arraysAsList(T item1, T item2, T item3) {
        List<T> list = new ArrayList<>(3);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        return list;
    }

    public static <T> List<T> arraysAsList(T item1, T item2, T item3, T item4) {
        List<T> list = new ArrayList<>(4);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        return list;
    }

    public static <T> List<T> arraysAsList(T item1, T item2, T item3, T item4, T item5) {
        List<T> list = new ArrayList<>(5);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);
        return list;
    }

    public static <T> T requireNonNullElse(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static String stringFixedLength(int fixedLength, String text) {
        StringBuilder builder = new StringBuilder(text);
        while (builder.length() < fixedLength) {
            builder.append(" ");
        }
        return builder.toString();
    }

    public static <T> T requireNonNull(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
        return value;
    }

    public static <T> Collector<T, ?, List<T>> lastN(int n) {
        return Collector.<T, Deque<T>, List<T>>of(ArrayDeque::new, (acc, t) -> {
            if(acc.size() == n)
                acc.pollFirst();
            acc.add(t);
        }, (acc1, acc2) -> {
            while(acc2.size() < n && !acc1.isEmpty()) {
                acc2.addFirst(acc1.pollLast());
            }
            return acc2;
        }, ArrayList::new);
    }
}
