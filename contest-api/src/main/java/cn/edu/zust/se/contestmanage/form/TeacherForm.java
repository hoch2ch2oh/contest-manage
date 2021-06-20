package cn.edu.zust.se.contestmanage.form;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class TeacherForm implements Serializable {
    private int id;
    private String password;
    private String confirmPassword;
    private String trueName;
    private String gender;
    private String department;
    private String professionalTitle;
    private String phone;
}
