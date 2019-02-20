package com.hcy308.transaction.api.web.controller;

import com.hcy308.transaction.input.WishInputDto;
import com.hcy308.transaction.model.Wish;
import com.hcy308.transaction.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/1/")
public class WishController {

    private final WishService wishService;

    @Autowired
    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping("/wishes")
    public List<Wish> getAllWishes() {
        return wishService.getAll();
    }

    @GetMapping("/wipe/{wipeId}/wishes")
    public List<Wish> getAllWishesOfWipe(@PathVariable(name = "wipeId") long wipeId) {
        return wishService.getAllWishesOfWipe(wipeId);
    }

    // We usually don't do modification in get method, but just for convenience here
    @GetMapping("/reset")
    public String reset() {
        wishService.reset();
        return "OK";
    }

    @PostMapping("/wishWithError")
    public Wish wishWithError(@RequestBody WishInputDto dto) {
        return wishService.wishWithError(dto);
    }

    @PostMapping("/wishWithErrorInTransaction")
    public Wish wishWithErrorInTransaction(@RequestBody WishInputDto dto) {
        return wishService.wishWithErrorInTransaction(dto);
    }

    @PostMapping("/wishUnsafe")
    public Wish wishUnsafe(@RequestBody WishInputDto dto) {
        return wishService.wishUnsafe(dto);
    }

    @PostMapping("/wishSafe")
    public Wish wishSafe(@RequestBody WishInputDto dto) {
        return wishService.wishSafe(dto);
    }

    @PostMapping("/wishTimeConsumed")
    public Wish wishTimeConsumed(@RequestBody WishInputDto dto) {
        return wishService.wishTimeConsumed(dto);
    }

    @PostMapping("/wishOptimized")
    public Wish wishOptimized(@RequestBody WishInputDto dto) {
        return wishService.wishOptimized(dto);
    }

    @PostMapping("/unrealSafeWish1")
    public Wish unrealSafeWish1(@RequestBody WishInputDto dto) {
        return wishService.unrealSafeWish1(dto);
    }

    @PostMapping("/unrealSafeWish2")
    public Wish unrealSafeWish2(@RequestBody WishInputDto dto) {
        return wishService.unrealSafeWish2(dto);
    }

    @PostMapping("/unrealSafeWish3")
    public Wish unrealSafeWish3(@RequestBody WishInputDto dto) {
        return wishService.unrealSafeWish3(dto);
    }

}
