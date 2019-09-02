package ${package}.model;

import io.nebula.kernel.entity.BaseEntity;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "employee")
public class Employee extends BaseEntity {
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 年龄
     */
    @Column(name = "age")
    private Integer age;

    @Column(name = "sex")
    private Boolean sex;

    /**
     * 身份证
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 工号
     */
    @Column(name = "work_num")
    private String workNum;

    /**
     * 所在企业
     */
    @Column(name = "comp")
    private String comp;

    /**
     * 所在部门
     */
    @Column(name = "dept")
    private String dept;

    /**
     * 职位
     */
    @Column(name = "position")
    private String position;

    /**
     * 职级
     */
    @Column(name = "level")
    private String level;

    /**
     * 手机号
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 入职时间
     */
    @Column(name = "hire_date")
    private Date hireDate;

    /**
     * 状态
     */
    @Column(name = "status")
    private String status;

    /**
     * 备注
     */
    @Column(name = "description")
    private String description;

    /**
     * 修改人
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 修改时间
     */
    @Column(name = "updated")
    private Date updated;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created")
    private Date created;
}
