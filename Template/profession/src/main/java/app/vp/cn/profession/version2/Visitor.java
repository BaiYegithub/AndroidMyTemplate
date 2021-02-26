package app.vp.cn.profession.version2;


import java.util.HashMap;
import java.util.HashSet;

public class Visitor {
    private HashMap<Node, HashSet<Integer>> records;
    
    public Visitor() {
        records = new HashMap<>();
    }
    
    //增加访问记录
    public void addRecord(Node node, int value) {
        if (!records.containsKey(node))
            records.put(node, new HashSet<Integer>());
        
        HashSet<Integer> visitedValues = records.get(node);
        visitedValues.add(value);
    }
    
    //查询访问记录
    public boolean contains(Node node, int value) {
        if (!records.containsKey(node))
            return false;
        
        HashSet<Integer> visitedValues = records.get(node);
        return visitedValues.contains(value);
    }
}
