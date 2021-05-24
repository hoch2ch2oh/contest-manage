package cn.edu.zust.se.contestmanage.dao;

import cn.edu.zust.se.contestmanage.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zy 2021/5/24
 */
public interface TeacherDao extends JpaRepository<TeacherEntity, Integer> {

}
