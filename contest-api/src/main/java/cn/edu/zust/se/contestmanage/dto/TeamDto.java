package cn.edu.zust.se.contestmanage.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author zy 2021/5/24
 */
@Setter
@Getter
@Entity
@Table(name = "team", schema = "keshe", catalog = "")
public class TeamDto {
    @ApiModelProperty("团队ID")
    private int id;
    @ApiModelProperty("团队名称")
    private String name;
    @ApiModelProperty("团队人数")
    private int teamNumber;
    @ApiModelProperty("团队描述")
    private String description;
    @ApiModelProperty("学生确认人数")
    private int sCheck;
    @ApiModelProperty("教师确认")
    private int tCheck;
    @ApiModelProperty("比赛成绩")
    private String score;
    //private ContestDto contestByCid;


    @Override
    public String toString() {
        return "TeamDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teamNumber=" + teamNumber +
                ", description='" + description + '\'' +
                ", sCheck=" + sCheck +
                ", tCheck=" + tCheck +
                ", score='" + score + '\'' +
                '}';
    }
}