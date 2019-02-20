package com.hcy308.transaction.service;

import com.hcy308.transaction.input.WipeInputDto;
import com.hcy308.transaction.model.Wipe;
import com.hcy308.transaction.repository.WipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class WipeService {

    private final WipeRepository wipeRepository;

    @Autowired
    public WipeService(WipeRepository wipeRepository) {
        this.wipeRepository = wipeRepository;
    }

    public List<Wipe> getAll() {
        return wipeRepository.findAll();
    }

    public Wipe create(WipeInputDto dto) {
        Timestamp wipeDate = new Timestamp(System.currentTimeMillis());
        Wipe wipe = new Wipe();
        wipe.setLampId(dto.getLampId());
        wipe.setWiper(dto.getWiper());
        wipe.setUsed(0);
        wipe.setCapacity(dto.getCapacity());
        wipe.setWipeDate(wipeDate);
        return wipeRepository.save(wipe);
    }

}
