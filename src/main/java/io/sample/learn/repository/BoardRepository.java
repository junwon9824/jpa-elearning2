package io.sample.learn.repository;

import io.sample.learn.entity.Board;
import io.sample.learn.entity.BuyBoard;
import io.sample.learn.entity.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

@Transactional

public interface BoardRepository extends JpaRepository<Board, Long> {

    BuyBoard findBymember(Member member);

    Optional<BuyBoard> findBymemberAndBoard(Member member, Board board);

    Collection<Object> findBytitleContaining(String searchkeyword);

    Board findBytitle(String title);
}
