package com.pastebin.PasteBin.service;

import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PasteService {

    private final Map<String, String> pasteStore = new HashMap<>();

    public String addPaste(String content) throws NoSuchAlgorithmException {
        String uuid = UUID.randomUUID().toString();
        String hash = generateHash(uuid);
        pasteStore.put(hash,content);
        return hash;
    }

    public String getPaste(String hash) {
        return pasteStore.getOrDefault(hash,"Invalid id, please enter valid hash");
    }

    private String generateHash(String uuid) throws NoSuchAlgorithmException {
        StringBuilder resHash = new StringBuilder();
        MessageDigest hashAlgo = MessageDigest.getInstance("SHA-256");
        hashAlgo.update(uuid.getBytes());

        for (byte b: hashAlgo.digest()) {
            resHash.append(String.format("%02x",b));
        }

        return resHash.substring(0,10);

    }


}
