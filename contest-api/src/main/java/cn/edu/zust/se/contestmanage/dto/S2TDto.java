package cn.edu.zust.se.contestmanage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zy 2021/5/24
 */
@Data
@ApiModel("学生收到的组队申请")
public class S2TDto implements Serializable {
    @ApiModelProperty("申请ID")
    private int id;
    @ApiModelProperty("队伍ID")
    private int tid;
    @ApiModelProperty("学生ID")
    private int sid;
    @ApiModelProperty("确认状态ID")
    private int check;

    @Override
    public String toString() {
        return "S2TDto{" +
                "id=" + id +
                ", tid=" + tid +
                ", sid=" + sid +
                ", check=" + check +
                '}';
    }
}
