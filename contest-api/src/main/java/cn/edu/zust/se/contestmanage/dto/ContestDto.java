package cn.edu.zust.se.contestmanage.dto;

/**
 * @author zy 2021/5/24
 */
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.persistence.*;
import java.util.Date;

@Data
@ApiModel("具体赛事")
public class ContestDto {
    @ApiModelProperty("竞赛ID")
    private Integer id;
    @ApiModelProperty("竞赛名称")
    private String name;
    @ApiModelProperty("主办方")
    private String sponsor;
    @ApiModelProperty("人数限制")
    private int memberLimit;
    @ApiModelProperty("报名开始时间")
    private Date registerStartTime;
    @ApiModelProperty("报名结束时间")
    private Date registerEndTime;
    @ApiModelProperty("比赛开始时间")
    private Date contestStartTime;
    @ApiModelProperty("比赛结束时间")
    private Date contestEndTime;
    @ApiModelProperty("比赛介绍")
    private String description;

    @Override
    public String toString() {
        return "ContestDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", number_limit=" + memberLimit +
                ", register_start_time=" + registerStartTime +
                ", register_end_time=" + registerEndTime +
                ", contest_start_time=" + contestStartTime +
                ", contest_end_time=" + contestEndTime +
                ", description='" + description + '\'' +
                '}';
    }
}
