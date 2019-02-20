package com.hcy308.transaction.util;

import com.hcy308.transaction.ApplicationTests;
import com.hcy308.transaction.input.WishInputDto;
import com.hcy308.transaction.model.Wish;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class WishGeneratorTests extends ApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void generateWithSign() {
        WishInputDto dto = getWishInputDto();
        Date dateBefore = new Date();
        Wish wish = wishGenerator.generateWithSign(dto);
        Date dateAfter = new Date();
        logger.info("generate with sign - before: {}, after: {}, use time in ms: {}",
                dateBefore, dateAfter, dateAfter.getTime() - dateBefore.getTime());
        dateBefore = new Date();
        wishGenerator.generateSign(wish);
        dateAfter = new Date();
        logger.info("generate sign - before: {}, after: {}, use time in ms: {}",
                dateBefore, dateAfter, dateAfter.getTime() - dateBefore.getTime());
    }
}
