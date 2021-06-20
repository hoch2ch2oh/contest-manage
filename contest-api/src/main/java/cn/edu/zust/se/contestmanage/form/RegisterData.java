package cn.edu.zust.se.contestmanage.form;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class RegisterData implements Serializable {
    private List<RegisterForm> list;
    private int cid;
}
