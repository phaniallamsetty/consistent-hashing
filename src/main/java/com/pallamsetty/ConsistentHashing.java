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

import com.google.gson.Gson;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

public class ConsistentHashing {
    private TreeMap<String, HashRingItem> hashRing;
    private int servers;
    private int virtualNodesPerServer;


    public ConsistentHashing(int servers, int virtualNodesPerServer) {
        hashRing = new TreeMap<>();
        this.servers = servers;
        this.virtualNodesPerServer = virtualNodesPerServer;

        for(int i = 0; i < servers; i++) {
            addNode("server", i, null);
        }
    }

    public void addNode(String nodeType, int index, String name) {
        String fullName = getFullNodeName(nodeType, index, name);
        String nodeNameHash = getSHA1Hash(fullName);
        hashRing.put(nodeNameHash, new HashRingItem(nodeType, nodeNameHash, fullName));
    }

    private String getFullNodeName(String nodeType, int index, String name) {
        StringBuilder sb = new StringBuilder();
        if(name != null) {
            sb.append(name);
            sb.append('_');
        }

        sb.append(nodeType);
        sb.append('_');
        sb.append(index);

        return sb.toString();
    }

    public void removeNode(String hashKey) {

    }

    public String get(String hashKey) {
        return "";
    }

    public void saveData(String data) {

    }

    public void displayRingState() {
        Gson gson = new Gson();
        String json = gson.toJson(hashRing);
        System.out.println(json);
    }

    public int getRingItemCount() {
        return hashRing.size();
    }

    private String getSHA1Hash(String fullName) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hashBytes = md.digest(fullName.getBytes());

            StringBuilder hexString = new StringBuilder();
            for(byte b: hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(NoSuchAlgorithmException ex) {
            throw new RuntimeException("ex: " + ex.getMessage());
        }
    }
}
