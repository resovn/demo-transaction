package com.hcy308.transaction.service;

import com.hcy308.transaction.input.LampInputDto;
import com.hcy308.transaction.model.MagicLamp;
import com.hcy308.transaction.repository.LampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LampService {

    private final LampRepository lampRepository;

    @Autowired
    public LampService(LampRepository lampRepository) {
        this.lampRepository = lampRepository;
    }

    public List<MagicLamp> getAll() {
        return lampRepository.findAll();
    }

    public MagicLamp create(LampInputDto dto) {
        MagicLamp lamp = new MagicLamp();
        lamp.setLampId(dto.getId());
        lamp.setName(dto.getName());
        return lampRepository.save(lamp);
    }

}
