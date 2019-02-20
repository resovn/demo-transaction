package com.hcy308.transaction.util;

import com.hcy308.transaction.input.WishInputDto;
import com.hcy308.transaction.model.Wish;
import com.hcy308.transaction.repository.WishRepository;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

public class WishGenerator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final WishRepository wishRepository;

    public WishGenerator(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public Wish generateWithSign(WishInputDto dto) {
        return sign(generate(dto));
    }

    Wish generate(WishInputDto dto) {
        Timestamp wishDate = new Timestamp(System.currentTimeMillis());
        Wish wish = new Wish();
        wish.setWipeId(dto.getWipeId());
        wish.setSubject(dto.getSubject());
        wish.setWishDate(wishDate);
        return wish;
    }

    Wish sign(Wish wish) {
        if (wish.getId() == null) {
            // no id yet, so save it into db to generate it
            wish = wishRepository.save(wish);
        }
        wish.setSign(generateSign(wish));
        return wish;
    }

    String generateSign(Wish wish) {
        String s = wish.getId() + wish.getSubject() + wish.getWipeId() + wish.getWishDate();
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            logger.error("failed to get instance of message digest");
            return null;
        }
        return new String(Hex.encode(digest.digest(s.getBytes(StandardCharsets.UTF_8))));
    }
}
