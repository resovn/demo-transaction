package com.hcy308.transaction;


import com.hcy308.transaction.input.WishInputDto;
import com.hcy308.transaction.model.Wipe;
import com.hcy308.transaction.repository.WipeRepository;
import com.hcy308.transaction.repository.WishRepository;
import com.hcy308.transaction.service.WishService;
import com.hcy308.transaction.util.WishGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * @author Simon Huang
 */
@SpringBootTest(classes = TransactionApplicationBoot.class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class ApplicationTests {

    @Autowired
    protected WipeRepository wipeRepository;

    @Autowired
    protected WishService wishService;

    @Autowired
    protected WishRepository wishRepository;

    protected WishGenerator wishGenerator;

    @Before
    public void init() {
        wishGenerator = new WishGenerator(wishRepository);

        wishRepository.deleteAll();
        List<Wipe> wipes = wipeRepository.findAllByWiper("Aladdin");
        wipes.forEach(wipe -> {
            wipe.setUsed(0);
            wipeRepository.save(wipe);
        });
    }


    protected WishInputDto getWishInputDto() {
        Wipe wipe = getWipe();
        WishInputDto dto = new WishInputDto();
        dto.setWipeId(wipe.getId());
        dto.setSubject("Marry Princess");
        return dto;
    }

    protected Wipe getWipe() {
        List<Wipe> wipes = wipeRepository.findAllByWiper("Aladdin");
        Assert.assertEquals(wipes.size(), 1);
        return wipes.get(0);
    }
}

