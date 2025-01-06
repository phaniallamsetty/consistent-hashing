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
import java.util.TreeMap;

public class ConsistentHashing {
    private TreeMap<String, HashRingItem> hashRing;
    private TreeMap<String, String> virtualToServerMap;
    private int servers;
    private int virtualNodesPerServer;


    public ConsistentHashing(int servers, int virtualNodesPerServer) {
        hashRing = new TreeMap<>();
        virtualToServerMap = new TreeMap<>();
        this.servers = servers;
        this.virtualNodesPerServer = virtualNodesPerServer;

        for(int i = 0; i < servers; i++) {
            String serverHash = addServer("server", i, null);

            for(int j = 0; j < virtualNodesPerServer; j++) {
                addVirtualNode("virtual", j, null, serverHash);
            }
        }
    }

    public String addServer(String nodeType, int index, String name) {
        String fullName = getFullServerName(nodeType, index, name);
        String serverNameHash = getSHA1Hash(fullName);
        hashRing.put(serverNameHash, new HashRingItem(nodeType, serverNameHash, fullName));
        return serverNameHash;
    }

    public void addVirtualNode(String nodeType, int index, String name, String serverHash) {
        String fullName = getFullVirtualNodeName(nodeType, index, name, serverHash);
        String virtualNodeHash = getSHA1Hash(fullName);
        hashRing.put(virtualNodeHash, new HashRingItem(nodeType, virtualNodeHash, fullName));
        virtualToServerMap.put(serverHash, virtualNodeHash);
    }

    private String getFullServerName(String nodeType, int index, String name) {
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

    private String getFullVirtualNodeName(String nodeType, int index, String name, String serverHash) {
        StringBuilder sb = new StringBuilder();
        if(name != null) {
            sb.append(name);
            sb.append('_');
        }

        sb.append(nodeType);
        sb.append('_');
        sb.append(index);
        sb.append('_');
        sb.append(serverHash);

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

    public void displayVirtualToServerMapping() {
        Gson gson = new Gson();
        String json = gson.toJson(virtualToServerMap);
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
