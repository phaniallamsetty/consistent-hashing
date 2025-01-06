package com.pallamsetty;

/*
* HashRing {
*   TreeMap<String, HashRingItem> hashRing
* }
*
* HashRingItem {
*   String itemType;
* }
*
* hashRingDataMap
* */

import java.util.TreeMap;

public class ConsistentHashing {
    private TreeMap<String, HashRingItem> hashRing;
    private TreeMap<String, String> dataLocationMap;
    private int servers;
    private int virtualNodesPerServer;


    public ConsistentHashing(int servers, int virtualNodesPerServer) {
        hashRing = new TreeMap<>();
        dataLocationMap = new TreeMap<>();
        this.servers = servers;
        this.virtualNodesPerServer = virtualNodesPerServer;
    }

    public void addNode(String nodeType) {

    }

    public void removeNode(String hashKey) {

    }

    public String get(String hashKey) {
        return "";
    }

    public void saveData(String data) {

    }

    public void displayRingState() {

    }
}
