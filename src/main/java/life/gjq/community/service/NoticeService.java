package life.gjq.community.service;

import life.gjq.community.mapper.NoticeMapper;
import life.gjq.community.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeService {

@Autowired
private NoticeMapper noticeMapper;
    public Notice selectTopNotice() {
        long nCount = noticeMapper.countByExample(null);
      return noticeMapper.selectByPrimaryKey(nCount);
    }

    public List<Notice> selectBottomNotice() {
        long nCount = noticeMapper.countByExample(null)-1;
        List<Notice> notices=new ArrayList<>();
        int i=5;
        while (nCount>0||i>0){
            notices.add(noticeMapper.selectByPrimaryKey(nCount));
            nCount--;
            i--;
        }
        return notices;
    }
}
