package org.feather.skill.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: skill
 * @package: org.feather.skill.skill.domain
 * @className: AddUserRequest
 * @author: feather
 * @description:
 * @since: 13-Jan-24 7:22 PM
 * @version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddUserRequest {

    private String username;

    private String password;

}
