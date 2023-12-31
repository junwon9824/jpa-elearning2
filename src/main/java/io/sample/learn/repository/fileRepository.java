package io.sample.learn.repository;

import io.sample.learn.entity.File;
 import io.sample.learn.entity.Buyfile;
import io.sample.learn.entity.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

@Transactional

public interface fileRepository extends JpaRepository<File, Long> {

    Buyfile findBymember(Member member);

    Optional<Buyfile> findBymemberAndfile(Member member, File file);

    Optional<File> findBytitleContaining(String searchkeyword);

    File findBytitle(String title);
}
