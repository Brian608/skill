package org.feather.skill.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: skill
 * @package: org.feather.skill.page
 * @className: BasePage
 * @author: feather
 * @description: 分页基类
 * @since: 2024-02-07 10:54
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePage {

    private  Integer pageNo=1;

    private Integer pageSize=10;

}
