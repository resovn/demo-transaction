package com.hcy308.transaction.service;

import com.hcy308.transaction.ApplicationTests;
import com.hcy308.transaction.input.WishInputDto;
import com.hcy308.transaction.model.Wish;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class WishServiceTests extends ApplicationTests {

    @Test
    public void getAll() {
        List<Wish> wishes = wishService.getAll();
        Assert.assertEquals(wishes.size(), 0);
    }

    @Test
    public void wishWithError() {
        WishInputDto dto = getWishInputDto();
        try {
            wishService.wishWithError(dto);
        } catch (Exception e) {
            // do nothing
        }

        assertWipeUsed(1);
    }

    @Test
    public void wishWithErrorInTransaction() {
        WishInputDto dto = getWishInputDto();
        try {
            wishService.wishWithErrorInTransaction(dto);
        } catch (Exception e) {
            // do nothing
        }

        assertWipeUsed(0);
    }

    @Test
    public void wish() {
        WishInputDto dto = getWishInputDto();
        try {
            wishService.wishOptimized(dto);
        } catch (Exception e) {
            // do nothing
        }

        assertWipeUsed(1);
    }

    @Test
    public void wishWithSign() {
        wishService.unrealSafeWish3(getWishInputDto());
    }

    private void assertWipeUsed(int used) {
        Assert.assertEquals((int) getWipe().getUsed(), used);
    }
}
