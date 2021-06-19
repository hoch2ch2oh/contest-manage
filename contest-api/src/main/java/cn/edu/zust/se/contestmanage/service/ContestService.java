package cn.edu.zust.se.contestmanage.service;


import cn.edu.zust.se.contestmanage.dto.ContestDto;
import cn.edu.zust.se.contestmanage.form.ContestPage;
import cn.edu.zust.se.contestmanage.form.RegisterForm;

import java.util.List;

public interface ContestService {

    public String createContest(ContestDto contestDto);

    public ContestPage getContestList(int pageNum, int pageSize);
    //public List<ContestDto> findContestByName(String name);

    public ContestDto findById(int id);

    public String updateContest(ContestDto contestDto);

    public List<RegisterForm> getRegisterForm(int id);

    public String updateRegisterForm(List<RegisterForm> register);

    ContestPage searchContest(int type, String keyword, int pageNo, int pageSize);

    ContestPage listContestByTime(int status, int pageNo, int pageSize);
}
