package common.rule.themis.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author haoran hou
 * @since 2022-10-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long companyId;

    private Long departmentId;

    private String roleName;

    private Integer del;

    private Long gmtCreate;

    private Long gmtModified;


}
