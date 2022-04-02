package life.gjq.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO<T> {
    private List<T> data;
    private boolean showLastPage;
    private boolean showFirstPage;
    private boolean showNextPage;
    private boolean showEndPage;
    private Integer page;
    private  Integer totalPage;
    private List<Integer> pages = new ArrayList<>();

    public void setPageNation(Integer totalCount, Integer page, Integer size) {

        //得到总页数
        if (totalCount % size == 0) {
            if(totalCount==0){
                totalPage=1;
            }else {
                totalPage = totalCount / size;
            }
        } else {
            totalPage = totalCount / size + 1;
        }
        //防止传递越界的值
       if(page > totalPage){
            page=totalPage;
        } else  if(page<1)
        {
            page=1;
        }
        this.page=page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0,page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        //是否展示上一页
        if (page == 1) {
            showLastPage = false;
        } else {
            showLastPage = true;
        }
        //是否展示下一页
        if (page == totalPage) {
            showNextPage = false;
        } else {
//            if(totalPage!=0){
            showNextPage = true;
//            }
        }
        //是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
                showEndPage = true;
        }

    }
}
