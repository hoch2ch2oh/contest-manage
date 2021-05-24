package cn.edu.zust.se.contestmanage.dao;

import cn.edu.zust.se.contestmanage.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zy 2021/5/24
 */
public interface TeamDao extends JpaRepository<TeamEntity, Integer> {
}
