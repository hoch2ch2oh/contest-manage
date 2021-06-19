package cn.edu.zust.se.contestmanage.service;

import cn.edu.zust.se.contestmanage.dto.TeacherDto;
import cn.edu.zust.se.contestmanage.form.TeacherPage;

import java.util.List;

public interface TeacherService {
    public String saveTeachers(List<TeacherDto> teachers);

    public String updateTeacher(TeacherDto teacher);

    public TeacherPage listTeacher(int pageNo, int pageSize);

    public TeacherDto findTeacherById(int id);

    public void delete(int id);

    public TeacherPage searchContest(int type, String keyword, int pageNo, int pageSize);
}
