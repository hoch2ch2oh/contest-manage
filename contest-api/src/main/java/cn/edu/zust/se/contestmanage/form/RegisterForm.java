package cn.edu.zust.se.contestmanage.form;

import cn.edu.zust.se.contestmanage.dto.StudentDto;
import cn.edu.zust.se.contestmanage.dto.TeacherDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class RegisterForm implements Serializable {
    //竞赛id
    private int cid;
    //团队id
    private int tid;
    //团队名称
    private String name;
    //团队成员
    private List<StudentDto> students;
    //联系方式
    private String phone;
    //指导老师
    private TeacherDto teacher;
    //成绩
    private String score;
}
