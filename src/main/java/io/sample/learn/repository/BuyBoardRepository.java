package io.sample.learn.repository;

import io.sample.learn.entity.Board;
import io.sample.learn.entity.BuyBoard;
import io.sample.learn.entity.Member;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Transactional

public interface BuyBoardRepository extends JpaRepository<BuyBoard, Long> {

    BuyBoard findBymember(Member member);

    Optional<BuyBoard> findBymemberAndBoard(Member member, Board board);
}
