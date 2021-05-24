package cn.edu.zust.se.contestmanage.service;

import cn.edu.zust.se.contestmanage.dto.StudentDto;


import java.util.List;

/**
 * @author zy 2021/5/24
 */
public interface StudentServiceI {
    //新增学生用户（批量）
    public List<StudentDto> addStudents(List<StudentDto> studentDtos);

    //新增学生用户
    public StudentDto addStudents(StudentDto studentDto);

    //修改学生用户密码(密码和确认密码的操作在controller）
    public StudentDto updateStudentPassword(int studentId, String password);
}
