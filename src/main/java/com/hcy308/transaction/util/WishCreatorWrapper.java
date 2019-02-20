package com.hcy308.transaction.util;

import com.hcy308.transaction.input.WishInputDto;
import com.hcy308.transaction.model.Wish;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public class WishCreatorWrapper {

    private final WishCreator creator;

    public WishCreatorWrapper(WishCreator creator) {
        this.creator = creator;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Wish create(WishInputDto dto) {
        return creator.doMakeWish(dto);
    }

}
