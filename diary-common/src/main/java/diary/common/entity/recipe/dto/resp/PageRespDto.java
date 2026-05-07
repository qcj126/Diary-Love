package diary.common.entity.recipe.dto.resp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

/**
 * 分页响应结果
 */
@Data
public class PageRespDto<T> {
    private Long total;          // 总记录数
    private Long pages;          // 总页数
    private Long current;        // 当前页码
    private Long size;           // 每页大小
    private List<T> records;     // 数据列表

    /**
     * 将 MyBatis-Plus 的 IPage 转换为统一的 PageRespDto
     */
    public static <T> PageRespDto<T> of(IPage<T> page) {
        PageRespDto<T> dto = new PageRespDto<>();
        dto.setTotal(page.getTotal());
        dto.setPages(page.getPages());
        dto.setCurrent(page.getCurrent());
        dto.setSize(page.getSize());
        dto.setRecords(page.getRecords());
        return dto;
    }
}
