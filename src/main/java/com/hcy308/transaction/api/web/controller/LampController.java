package com.hcy308.transaction.api.web.controller;

import com.hcy308.transaction.input.LampInputDto;
import com.hcy308.transaction.model.MagicLamp;
import com.hcy308.transaction.service.LampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/api/1/")
public class LampController {

    private final LampService lampService;

    @Autowired
    public LampController(LampService lampService) {
        this.lampService = lampService;
    }

    @GetMapping("/lamps")
    public List<MagicLamp> getAllLamps() {
        return lampService.getAll();
    }

    @PostMapping("/lamp")
    public MagicLamp create(LampInputDto dto) {
        return lampService.create(dto);
    }
}
