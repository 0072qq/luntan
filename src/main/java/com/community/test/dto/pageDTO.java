package com.community.test.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class pageDTO {
    private List<QuestionDTO> questions = new ArrayList<>();
    private boolean hasPrevious=true;//上一页
    private boolean hasNext=true;//下一页
    private boolean showFirstPage=true;//首页
    private boolean showEndPage=true;//尾页
    private Integer totalPage;//总页数

    private Integer nowPage;
    private List<Integer> pages = new ArrayList<>();

    public void setSinglePage(int page,int total){
        if(nowPage==1){
            hasPrevious=false;
        }
        if(nowPage==total){
            hasNext=false;
        }
        if(pages.contains(1)){
            showFirstPage = false;
        }
        if(pages.contains(total)){
            showEndPage = false;
        }

        pages.add(page);
        for(int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }
            if(page+i<=total){
                pages.add(page+i);
            }
        }
    }
}
