package ${package}.model;

import io.nebula.kernel.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * <p>
 *
 * </p>
 *
 * @author xubulong2
 * @since 2019-08-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Employee extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    private Boolean sex;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 工号
     */
    private String workNum;

    /**
     * 所在企业
     */
    private String comp;

    /**
     * 所在部门
     */
    private String dept;

    /**
     * 职位
     */
    private String position;

    /**
     * 职级
     */
    private String level;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 入职时间
     */
    private LocalDate hireDate;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String description;


}
