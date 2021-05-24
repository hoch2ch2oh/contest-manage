package cn.edu.zust.se.contestmanage.service;

import cn.edu.zust.se.contestmanage.dto.TeacherDto;

import java.util.List;

/**
 * @author zy 2021/5/24
 */
public interface TeacherServiceI {
    //新增教师用户（批量）
    public List<TeacherDto> addTeachers(List<TeacherDto> teacherDtos);

    //新增教师用户
    public TeacherDto addTeacher(TeacherDto teacherDto);

    //修改教师用户密码(密码和确认密码的操作在controller）
    public TeacherDto updateTeacherPassword(int teacherId, String password);

    /**********
        教师端：修改个人信息（密码修改在上面）
     **********/
}
