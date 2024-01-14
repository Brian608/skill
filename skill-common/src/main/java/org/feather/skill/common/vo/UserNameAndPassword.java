package org.feather.skill.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: skill
 * @package: org.feather.skill.common.vo
 * @className: UserNameAndPassword
 * @author: feather
 * @description:
 * @since: 14-Jan-24 11:18 AM
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNameAndPassword {

    private String username;


    private String password;
}
