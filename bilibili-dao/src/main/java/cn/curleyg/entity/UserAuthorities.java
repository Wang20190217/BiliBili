package cn.curleyg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: Wang
 * @since: 2022/5/18 12:22 <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthorities {
    List<AuthRoleElementOperation> roleElementOperationList;

    List<AuthRoleMenu> roleMenuList;
}
