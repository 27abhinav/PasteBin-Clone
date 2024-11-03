package com.pastebin.PasteBin.controller;

import com.pastebin.PasteBin.service.PasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pastes")
public class RequestController {

    @Autowired
    private final PasteService pasteService;

    public RequestController(PasteService pasteService) {
        this.pasteService = pasteService;
    }
    @PostMapping
    public String postMessage(@RequestBody String content) throws NoSuchAlgorithmException {
        return pasteService.addPaste(content);

    }

    @GetMapping("/{hash}")
    public String getMessage(@PathVariable String hash) {
        return pasteService.getPaste(hash);

    }



}
