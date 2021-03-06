package cn.edu.zust.se.contestmanage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author zy 2021/5/24
 */
@Setter
@Getter
@Entity
@Table(name = "s2t", schema = "keshe", catalog = "")
public class S2TEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int tid;
    @Column
    private int sid;
    @Column
    private int check;

    @Override
    public String toString() {
        return "S2TEntity{" +
                "id=" + id +
                ", tid=" + tid +
                ", sid=" + sid +
                ", check=" + check +
                '}';
    }
}
