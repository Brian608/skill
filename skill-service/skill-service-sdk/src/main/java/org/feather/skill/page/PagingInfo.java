package org.feather.skill.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * @projectName: skill
 * @package: org.feather.skill.page
 * @className: PagingInfo
 * @author: feather
 * @description: 分页对象
 * @since: 2024-02-07 11:17
 * @version: 1.0
 */
@Data
public class PagingInfo<T> {
    /**
     * 页码
     */
    private int pageNo = 1;
    /**
     * 每页条数
     */
    private int pageSize = 10;
    /**
     * 总条数
     */
    private Long total;

    /**
     * 起始位置
     */
    private Integer startPos = (pageNo-1)*pageSize;

    private List<T> list;

    public PagingInfo() {

    }

    public PagingInfo(Long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public static <T> PagingInfo toResponse(Page<T> pageResult){
        PagingInfo<T> pagingObj = new PagingInfo<>();
        pagingObj.setTotal(pageResult.getTotal());
        pagingObj.setList(pageResult.getRecords());
        pagingObj.setPageNo((int)pageResult.getCurrent());
        pagingObj.setPageSize((int)pageResult.getSize());
        return pagingObj;
    }
}
