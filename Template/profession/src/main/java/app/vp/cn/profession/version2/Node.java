package app.vp.cn.profession.version2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Node {
    private HashSet<Integer> values;

    public Node() {
        values = new HashSet<>();
    }

    public void add(int value) {
        values.add(value);
    }

    public boolean contain(int value) {
        return values.contains(value);
    }

    public int size() {
        return values.size();
    }

    public List<Integer> get() {
        return new ArrayList<>(values);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (Integer value : values) {
            stringBuilder.append(value).append(", ");
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
