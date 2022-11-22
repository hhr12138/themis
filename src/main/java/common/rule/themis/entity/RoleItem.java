package common.rule.themis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleItem {
    private String requestId = "";
    private NeedPermission needPermission;
    private User user;
}
