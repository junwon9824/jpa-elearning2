package io.sample.learn.repository;

import io.sample.learn.entity.File;
 import io.sample.learn.entity.Buyfile;
import io.sample.learn.entity.Member;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Transactional

public interface BuyfileRepository extends JpaRepository<Buyfile, Long> {

    Buyfile findBymember(Member member);

    Optional<Buyfile> findBymemberAndfile(Member member, File file);
}
