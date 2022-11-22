package common.rule.themis.entity;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
public class NeedPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private String psm;

    /**
     * 方法签名或数据名
     */
    private String methorOrData;

    @NotNull
    private Long companyId;

    @NotNull
    private Long departmentId;

}
