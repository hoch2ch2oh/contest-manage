package cn.edu.zust.se.contestmanage.service;

import cn.edu.zust.se.contestmanage.dto.ContestDto;
import cn.edu.zust.se.contestmanage.dto.TeamDto;

import java.util.List;

/**
 * @author zy 2021/5/24
 */
public interface ContestServiceI {
    //创建比赛
    public ContestDto createContest(ContestDto contest);

    //修改比赛信息
    public ContestDto updateContest(ContestDto contest);

    /************************
        查找比赛（通过竞赛名称）
        管理、学生、教师端都会用到
     ************************/
    public List<ContestDto> findContest(String keyword);

    // TODO: 2021/5/24 查看xx，都要考虑 页面分类
    /************************
        查看比赛
        管理、学生、教师端都会用到
     *************************/
    public List<ContestDto> viewContest(int status);

    // TODO: 2021/5/24 查看xx，都要考虑 页面分类
    //查看参赛名单(type:报名顺序、竞赛成绩顺序）
    public List<TeamDto> viewTeam(int ContestId, int type);

    // TODO: 2021/5/24 具体实现方式还没想好
    //录入成绩
    public List<TeamDto> writeTeamScore(List<TeamDto> teamDtos);

}
