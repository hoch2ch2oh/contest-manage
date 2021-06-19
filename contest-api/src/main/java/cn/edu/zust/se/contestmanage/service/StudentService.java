package cn.edu.zust.se.contestmanage.service;

import cn.edu.zust.se.contestmanage.dto.StudentDto;
import cn.edu.zust.se.contestmanage.form.StudentPage;


import java.util.List;

public interface StudentService {
    
    public String saveStudents(List<StudentDto> students);

    public String updateStudent(StudentDto stu);

    public StudentPage listStudent(int pageNo, int pageSize);

    public StudentDto findStudentById(int id);

    public void delete(int id);

    public StudentPage searchContest(int type, String keyword, int pageNo, int pageSize);

    // TODO: 2021/6/16  查找学生 
}
