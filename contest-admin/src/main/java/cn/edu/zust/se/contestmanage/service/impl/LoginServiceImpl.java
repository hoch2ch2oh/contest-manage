package cn.edu.zust.se.contestmanage.service.impl;

import cn.edu.zust.se.contestmanage.dao.StudentDao;
import cn.edu.zust.se.contestmanage.dao.TeacherDao;
import cn.edu.zust.se.contestmanage.service.LoginService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "${contest.service.version}",application = "${dubbo.application.id}")
public class LoginServiceImpl implements LoginService {

    @Autowired
    StudentDao studentDao;
    @Autowired
    TeacherDao teacherDao;

    @Override
    public boolean login(String loginName, String password, int t) {
        if(t==1){
            if(studentDao.findByTrueNameAndPassword(loginName,password)==null)  return false;
            return true;
        }
        if(t==1){
            if(teacherDao.findByTrueNameAndPassword(loginName,password)==null)  return false;
            return true;
        }
        return false;
    }
}
