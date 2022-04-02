package life.gjq.community.service;

import life.gjq.community.dto.NotificationDTO;
import life.gjq.community.dto.PaginationDTO;
import life.gjq.community.enums.NotificationStatusEnum;
import life.gjq.community.enums.NotificationTypeEnum;
import life.gjq.community.exception.CustomizeErrorCode;
import life.gjq.community.exception.CustomizeException;
import life.gjq.community.mapper.NotificationMapper;
import life.gjq.community.model.Notification;
import life.gjq.community.model.NotificationExample;
import life.gjq.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    NotificationMapper notificationMapper;

    public PaginationDTO list(Long userId, Integer page, Integer size) {

        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId);
        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);

        //1、算出总页数，2、page越界验证
        paginationDTO.setPageNation(totalCount, page, size);
        Integer rightPage = paginationDTO.getPage();
        Integer offset = size * (rightPage - 1);
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        if (notifications.size() == 0) {
            return paginationDTO;
        }
        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }

        paginationDTO.setData(notificationDTOS);
        return paginationDTO;
    }

    public Long unReadCount(Long userId) {
        //得到所有未读的数量
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus())
                .andReceiverEqualTo(userId);
        return notificationMapper.countByExample(example);
    }

    public NotificationDTO read(Long id, User user) {

        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification.getReceiver() != user.getId()) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FALL);
        }
        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }

        //设置为已读
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));

        return notificationDTO;
    }
}
