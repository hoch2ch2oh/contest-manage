package cn.edu.zust.se.contestmanage.form;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UploadForm implements Serializable {
    private String type;
    private int id;
    private String name;
    private String gender;
    private String department;
    private String major;
}
