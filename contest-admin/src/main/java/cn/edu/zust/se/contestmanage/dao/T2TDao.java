package cn.edu.zust.se.contestmanage.dao;

import cn.edu.zust.se.contestmanage.entity.T2TEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface T2TDao extends JpaRepository<T2TEntity, Integer> {
    List<T2TEntity> findByTid(int id);
}
