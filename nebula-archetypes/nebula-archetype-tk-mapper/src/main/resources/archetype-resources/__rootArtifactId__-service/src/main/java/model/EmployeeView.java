package ${package}.model;

import lombok.Data;

/**
 * 返回前端的View
 */
@Data
public class EmployeeView extends Employee {

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 描述
     */
    private String description;

}
