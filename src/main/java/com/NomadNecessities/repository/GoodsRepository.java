package com.NomadNecessities.repository;

import com.NomadNecessities.model.Goods;
import com.NomadNecessities.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
  List<Goods> findByOrder(Order order);
}
