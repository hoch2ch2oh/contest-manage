package cn.edu.zust.se.contestmanage.service.impl;

import cn.edu.zust.se.contestmanage.dao.StudentDao;
import cn.edu.zust.se.contestmanage.dao.TeamDao;
import cn.edu.zust.se.contestmanage.dto.StudentDto;
import cn.edu.zust.se.contestmanage.entity.StudentEntity;
import cn.edu.zust.se.contestmanage.form.ContestPage;
import cn.edu.zust.se.contestmanage.form.StudentPage;
import cn.edu.zust.se.contestmanage.service.StudentService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service(version = "${contest.service.version}",application = "${dubbo.application.id}")
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;

    @Autowired
    TeamDao teamDao;

    private StudentDto e2d(StudentEntity tStudent) {
        StudentDto student = new StudentDto();
        BeanUtils.copyProperties(tStudent,student);
        return student;
    }

    private List<StudentDto> e2d(List<StudentEntity> tStudent) {
        if(tStudent == null || tStudent.size() == 0) {
            return new ArrayList<>();
        }
        List<StudentDto> studentDtos = new ArrayList<>();
        for(StudentEntity course : tStudent) {
            if(course != null) {
                studentDtos.add(e2d(course));
            }
        }
        return studentDtos;
    }

    @Override
    public String saveStudents(List<StudentDto> studentDtos) {
        for(StudentDto student:studentDtos){
            StudentEntity stu = new StudentEntity();
            stu.setId(student.getId());
            stu.setPassword(student.getPassword());
            stu.setTrueName(student.getTrueName());
            stu.setGender(student.getGender());
            stu.setDepartment(student.getDepartment());
            stu.setMajor(student.getMajor());
            stu.setClazz(student.getClazz());
            studentDao.save(stu);
        }
        return "success";
    }

    @Override
    public String updateStudent(StudentDto stu) {
        StudentEntity student = studentDao.findById(stu.getId()).orElse(null);
        student.setPassword(stu.getPassword());
        student.setDepartment(stu.getDepartment());
        student.setMajor(stu.getMajor());
        student.setClazz(stu.getClazz());
        studentDao.save(student);
        return "success";
    }

    @Override
    public StudentPage listStudent(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<StudentEntity> students = studentDao.findAll(pageable);
        StudentPage sp = new StudentPage();
        sp.setContents(e2d(students.getContent()));
        sp.setHasNext(students.hasNext());
        sp.setNext(students.nextPageable().getPageNumber()+1);
        sp.setHasPrevious(students.hasPrevious());
        sp.setPrevious(students.previousPageable().getPageNumber()+1);
        sp.setTotalPages(students.getTotalPages());
        return sp;
    }

    @Override
    public StudentDto findStudentById(int id) {
        StudentEntity stu = studentDao.findById(id).orElse(null);
        return e2d(stu);
    }

    @Override
    public void delete(int id) {
        studentDao.deleteById(id);
    }

    @Override
    public StudentPage searchContest(int type, String keyword, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<StudentEntity> students = null;
        StudentPage sp = new StudentPage();
        if(type==1)
            students = studentDao.findByTrueNameLike("%"+keyword+"%",pageable);
        if(type==2)
            students = studentDao.findByDepartmentLike("%"+keyword+"%",pageable);
        if(type==3)
            students = studentDao.findByMajorLike("%"+keyword+"%",pageable);
        if(type==4)
            students = studentDao.findByClazzLike("%"+keyword+"%",pageable);
        sp.setContents(e2d(students.getContent()));
        sp.setHasNext(students.hasNext());
        sp.setNext(students.nextPageable().getPageNumber()+1);
        sp.setHasPrevious(students.hasPrevious());
        sp.setPrevious(students.previousPageable().getPageNumber()+1);
        sp.setTotalPages(students.getTotalPages());
        return sp;
    }


}
