package com.hcy308.transaction.util;

import com.hcy308.transaction.input.WishInputDto;
import com.hcy308.transaction.model.Wipe;
import com.hcy308.transaction.model.Wish;
import com.hcy308.transaction.repository.WipeRepository;
import com.hcy308.transaction.repository.WishRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Component
public class WishCreator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final WipeRepository wipeRepository;
    private final WishRepository wishRepository;

    @Autowired
    public WishCreator(WipeRepository wipeRepository, WishRepository wishRepository) {
        this.wipeRepository = wipeRepository;
        this.wishRepository = wishRepository;
    }

    @Transactional
    public Wish createWithErrorInTransaction(WishInputDto dto) {
        return createWithError(dto);
    }

    public Wish createWithError(WishInputDto dto) {
        Wish result = null;
        long wipeId = dto.getWipeId();
        Wipe wipe = wipeRepository.findById(wipeId).orElse(null);
        if (wipe != null) {
            int capacity = wipe.getCapacity();
            int lastUsed = wipe.getUsed();
            if (capacity - lastUsed > 0) {
                wipe.setUsed(lastUsed + 1);
                wipeRepository.save(wipe);
                // make some error here
                @SuppressWarnings({"NumericOverflow", "divzero", "unused"})
                int i = 1 / 0;
                result = new WishGenerator(wishRepository).generate(dto);
            }
        }
        //noinspection ConstantConditions
        return result;
    }

    public Wish unsafeCreate(WishInputDto dto) {
        return timeConsumedCreate(dto);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Wish timeConsumedSafeCreate(WishInputDto dto) {
        return timeConsumedCreate(dto);
    }

    private Wish timeConsumedCreate(WishInputDto dto) {
        return doMakeWish(dto, true);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Wish safeCreate(WishInputDto dto) {
        return doMakeWish(dto);
    }

    public Wish unrealSafeCreate(WishInputDto dto) {
        return safeCreate(dto);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Wish looksLikeSafeSave(Wish wish) {
        return doSaveWish(wish);
    }

    @SuppressWarnings("WeakerAccess")
    public Wish doMakeWish(WishInputDto dto) {
        return doMakeWish(dto, false);
    }

    private Wish doMakeWish(WishInputDto dto, boolean timeConsumed) {
        return doSaveWish(new WishGenerator(wishRepository).generate(dto), timeConsumed);
    }

    private Wish doSaveWish(Wish wish) {
        return doSaveWish(wish, false);
    }

    private Wish doSaveWish(Wish wish, boolean timeConsumed) {
        Wish result = null;
        long wipeId = wish.getWipeId();
        Wipe wipe = wipeRepository.findById(wipeId).orElse(null);
        if (wipe != null) {
            int capacity = wipe.getCapacity();
            int lastUsed = wipe.getUsed();
            if (timeConsumed) {
                doSomethingTimeConsume();
            }
            if (capacity - lastUsed > 0) {
                result = wishRepository.save(wish);
                wipe.setUsed(lastUsed + 1);
                wipeRepository.save(wipe);
                logger.info("try to create wish {} from wipe {} {}/{}(used/capacity)",
                        wish.getSubject(), wish.getWipeId(), lastUsed, capacity);
            } else {
                logger.warn("we need at least {} remains for creating wish {}, but it is {}/{}(used/capacity)",
                        1, wish.getSubject(), lastUsed, capacity);
            }
        } else {
            logger.warn("wipe {} is not found.", wipeId);
        }
        return result;
    }

    public void doSomethingTimeConsume() {
        sleep(RandomUtils.randomLong() % 300 + 300);
    }

    private void sleep(long l) {
        try {
            TimeUnit.MICROSECONDS.sleep(l);
        } catch (InterruptedException e) {
            logger.warn("sleeping was interrupted", e);
        }
    }

}
