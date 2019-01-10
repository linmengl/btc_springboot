package com.blockchain.test;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

public class Josephus {
    public static void kill(int n) {
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int i = 0;
        while (list.size() >= 3) {
            i=(i+2)%(list.size());
            list.remove(list.get(i));
        }
        System.out.println(JSON.toJSON(list));
    }

    public static void main(String[] args) {
        kill(41);
    }

}

