package org.feather.skill.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @projectName: skill
 * @package: org.feather.skill.config
 * @className: MybatisPlusConfig
 * @author: feather
 * @description:
 * @since: 2024-01-28 17:47
 * @version: 1.0
 */
@Configuration
@ConditionalOnClass(MetaObjectHandler.class)
public class MybatisPlusConfig {
    /**
     * description: 通用字段自动填充配置
     * @return {@link MetaObjectHandler}
     * @author: feather
     * @since: 13-Jan-24 9:27 PM
     **/
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
                this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
            }
        };
    }
    /**
     * description:  插件配置
     * @return {@link MybatisPlusInterceptor}
     * @author: feather
     * @since: 13-Jan-24 9:28 PM
     **/
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        // 分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}
