package common.rule.themis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleData {
    private List<Permission> targetPermissions;
    private Set<Permission> existPermissions;
}
