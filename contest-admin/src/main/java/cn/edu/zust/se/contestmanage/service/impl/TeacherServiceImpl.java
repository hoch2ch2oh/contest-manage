package cn.edu.zust.se.contestmanage.service.impl;


import cn.edu.zust.se.contestmanage.dao.TeacherDao;
import cn.edu.zust.se.contestmanage.dto.TeacherDto;
import cn.edu.zust.se.contestmanage.entity.TeacherEntity;
import cn.edu.zust.se.contestmanage.form.TeacherPage;
import cn.edu.zust.se.contestmanage.service.TeacherService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service(version = "${contest.service.version}",application = "${dubbo.application.id}")
public class TeacherServiceImpl implements TeacherService {
    
    @Autowired
    TeacherDao teacherDao;

    private TeacherDto e2d(TeacherEntity tTeacher) {
        TeacherDto teacher = new TeacherDto();
        BeanUtils.copyProperties(tTeacher,teacher);
        return teacher;
    }

    private List<TeacherDto> e2d(List<TeacherEntity> tTeacher) {
        if(tTeacher == null || tTeacher.size() == 0) {
            return new ArrayList<>();
        }
        List<TeacherDto> teacherDtos = new ArrayList<>();
        for(TeacherEntity course : tTeacher) {
            if(course != null) {
                teacherDtos.add(e2d(course));
            }
        }
        return teacherDtos;
    }

    @Override
    public String saveTeachers(List<TeacherDto> teacherDtos) {
        for(TeacherDto teacher:teacherDtos){
            TeacherEntity t = new TeacherEntity();
            t.setId(teacher.getId());
            t.setPassword(teacher.getPassword());
            t.setTrueName(teacher.getTrueName());
            t.setGender(teacher.getGender());
            t.setDepartment(teacher.getDepartment());
            t.setProfessionalTitle(teacher.getProfessionalTitle());
            teacherDao.save(t);
        }
        return "success";
    }

    @Override
    public String updateTeacher(TeacherDto teacher) {
        TeacherEntity t = teacherDao.findById(teacher.getId()).orElse(null);
        t.setPassword(teacher.getPassword());
        t.setDepartment(teacher.getDepartment());
        t.setProfessionalTitle(teacher.getProfessionalTitle());
        teacherDao.save(t);
        return "success";
    }

    @Override
    public TeacherPage listTeacher(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<TeacherEntity> teachers = teacherDao.findAll(pageable);
        TeacherPage tp = new TeacherPage();
        tp.setContents(e2d(teachers.getContent()));
        tp.setHasNext(teachers.hasNext());
        tp.setNext(teachers.nextPageable().getPageNumber()+1);
        tp.setHasPrevious(teachers.hasPrevious());
        tp.setPrevious(teachers.previousPageable().getPageNumber()+1);
        tp.setTotalPages(teachers.getTotalPages());
        return tp;
    }

    @Override
    public TeacherDto findTeacherById(int id) {
        TeacherEntity teacher = teacherDao.findById(id).orElse(null);
        return e2d(teacher);
    }

    @Override
    public void delete(int id) {
        teacherDao.deleteById(id);
    }

    @Override
    public TeacherPage searchContest(int type, String keyword, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<TeacherEntity> teachers = null;
        if(type==1)
            teachers = teacherDao.findByTrueNameLike("%"+keyword+"%",pageable);
        if(type==2)
            teachers = teacherDao.findByDepartmentLike("%"+keyword+"%",pageable);
        if(type==3)
            teachers = teacherDao.findByProfessionalTitleLike("%"+keyword+"%",pageable);
        TeacherPage tp = new TeacherPage();
        tp.setContents(e2d(teachers.getContent()));
        tp.setHasNext(teachers.hasNext());
        tp.setNext(teachers.nextPageable().getPageNumber()+1);
        tp.setHasPrevious(teachers.hasPrevious());
        tp.setPrevious(teachers.previousPageable().getPageNumber()+1);
        tp.setTotalPages(teachers.getTotalPages());
        return tp;
    }
}
