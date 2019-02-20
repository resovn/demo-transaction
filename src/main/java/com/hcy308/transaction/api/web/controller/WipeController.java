package com.hcy308.transaction.api.web.controller;

import com.hcy308.transaction.input.WipeInputDto;
import com.hcy308.transaction.model.Wipe;
import com.hcy308.transaction.repository.WipeRepository;
import com.hcy308.transaction.service.WipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/1/")
public class WipeController {

    private final WipeService wipeService;
    private final WipeRepository wipeRepository;

    @Autowired
    public WipeController(WipeService wipeService, WipeRepository wipeRepository) {
        this.wipeService = wipeService;
        this.wipeRepository = wipeRepository;
    }

    @GetMapping("/wipes")
    public List<Wipe> getAllWipes() {
        return wipeService.getAll();
    }

    @GetMapping("/lamp/{lampId}/wipes")
    public List<Wipe> getAllWipesOfLamp(@PathVariable(name = "lampId") String lampId) {
        return wipeRepository.findAllByLampId(lampId);
    }


    @PostMapping("/wipe")
    public Wipe create(WipeInputDto dto) {
        return wipeService.create(dto);
    }

}
