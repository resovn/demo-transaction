package com.hcy308.transaction.service;

import com.hcy308.transaction.input.WishInputDto;
import com.hcy308.transaction.model.Wish;
import com.hcy308.transaction.repository.WipeRepository;
import com.hcy308.transaction.repository.WishRepository;
import com.hcy308.transaction.util.WishCreator;
import com.hcy308.transaction.util.WishCreatorWrapper;
import com.hcy308.transaction.util.WishGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final WipeRepository wipeRepository;
    private final WishRepository wishRepository;

    private final WishCreator wishCreator;

    @Autowired
    public WishService(WishRepository wishRepository, WishCreator wishCreator, WipeRepository wipeRepository) {
        this.wishRepository = wishRepository;
        this.wishCreator = wishCreator;
        this.wipeRepository = wipeRepository;
    }

    public List<Wish> getAll() {
        return wishRepository.findAll();
    }

    public List<Wish> getAllWishesOfWipe(long wipeId) {
        return wishRepository.findAllByWipeId(wipeId);
    }

    public void reset() {
        wishRepository.deleteAll();
        wipeRepository.findAll().forEach(wipe -> {
            wipe.setUsed(0);
            wipeRepository.save(wipe);
        });
    }

    public Wish wishWithError(WishInputDto dto) {
        return wishCreator.createWithError(dto);
    }

    public Wish wishWithErrorInTransaction(WishInputDto dto) {
        return wishCreator.createWithErrorInTransaction(dto);
    }

    public Wish wishUnsafe(WishInputDto dto) {
        return wishCreator.unsafeCreate(dto);
    }

    public Wish wishSafe(WishInputDto dto) {
        return wishCreator.timeConsumedSafeCreate(dto);
    }

    public Wish wishTimeConsumed(WishInputDto dto) {
        try {
            Wish result = wishCreator.timeConsumedSafeCreate(dto);
            logger.info("time consumed wish {} was created safely from wipe {}",
                    dto.getSubject(), dto.getWipeId());
            return result;
        } catch (Exception e) {
            logger.info("time consumed wish {} was failed to be created safely from wipe {}",
                    dto.getSubject(), dto.getWipeId());
            throw e;
        }
    }

    public Wish wishOptimized(WishInputDto dto) {
        wishCreator.doSomethingTimeConsume();

        try {
            Wish result = wishCreator.safeCreate(dto);
            logger.info("optimized wish {} was created from wipe {}",
                    dto.getSubject(), dto.getWipeId());
            return result;
        } catch (Exception e) {
            logger.info("optimized wish {} was failed to be created from wipe {}",
                    dto.getSubject(), dto.getWipeId());
            throw e;
        }
    }

    public Wish unrealSafeWish1(WishInputDto dto) {
        return wishCreator.unrealSafeCreate(dto);
    }

    public Wish unrealSafeWish2(WishInputDto dto) {
        return new WishCreatorWrapper(wishCreator).create(dto);
    }

    public Wish unrealSafeWish3(WishInputDto dto) {
        return wishCreator.looksLikeSafeSave(new WishGenerator(wishRepository).generateWithSign(dto));
    }

}
