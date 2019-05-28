package wad.project.core.service.impl;

import org.hibernate.exception.ConstraintViolationException;
import wad.project.core.dto.ListenGuidelineDTO;
import wad.project.core.persistence.entity.ListenGuidelineEntity;
import wad.project.core.service.ListenGuidelineService;
import wad.project.core.service.utils.SingletonDaoUtil;
import wad.project.core.utils.ListenGuidelineBeanUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListenGuidelineServiceImpl implements ListenGuidelineService {
    public Object[] findListenGuidelineByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<ListenGuidelineDTO> result = new ArrayList<ListenGuidelineDTO>();
        Object[] objects = SingletonDaoUtil.getListenGuidelineDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit);
        for (ListenGuidelineEntity item: (List<ListenGuidelineEntity>)objects[1]) {
            ListenGuidelineDTO dto = ListenGuidelineBeanUtil.entity2Dto(item);
            result.add(dto);
        }
        objects[1] = result;
        return objects;
    }

    public ListenGuidelineDTO findByListenGuidelineId(String property, Integer listenGuideLineId) {
        ListenGuidelineEntity entity = SingletonDaoUtil.getListenGuidelineDaoInstance().findEqualUnique(property, listenGuideLineId);
        ListenGuidelineDTO dto = ListenGuidelineBeanUtil.entity2Dto(entity);
        return dto;
    }

    public void saveListenGuideline(ListenGuidelineDTO dto) throws ConstraintViolationException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        dto.setCreatedDate(timestamp);
        ListenGuidelineEntity entity = ListenGuidelineBeanUtil.dto2Entity(dto);
        SingletonDaoUtil.getListenGuidelineDaoInstance().save(entity);
    }

    public ListenGuidelineDTO updateListenGuideline(ListenGuidelineDTO dto) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        dto.setModifiedDate(timestamp);
        ListenGuidelineEntity entity = ListenGuidelineBeanUtil.dto2Entity(dto);
        entity = SingletonDaoUtil.getListenGuidelineDaoInstance().update(entity);
        dto = ListenGuidelineBeanUtil.entity2Dto(entity);
        return dto;
    }

    public Integer delete(List<Integer> ids) {
        Integer result = SingletonDaoUtil.getListenGuidelineDaoInstance().delete(ids);
        return result;
    }
}
