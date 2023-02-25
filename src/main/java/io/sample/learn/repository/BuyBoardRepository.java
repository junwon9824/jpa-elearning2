package io.sample.learn.repository;

import io.sample.learn.entity.BuyBoard;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional

public interface BuyBoardRepository extends JpaRepository<BuyBoard,Long> {


}
