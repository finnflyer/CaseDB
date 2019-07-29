package com.demo.service.issue.impl;

import com.demo.dao.issue.IssueDao;
import com.demo.model.issue.IssueBean;
import com.demo.model.issue.IssuePhaseBean;
import com.demo.service.impl.BaseServiceImpl;
import com.demo.service.issue.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/9/8.
 */
@Service("issueService")
public class IssueServiceImpl extends BaseServiceImpl<IssueBean> implements IssueService {
    @Autowired
    private IssueDao issueDao;

    @Override
    public Map<Integer, String> findIssuePhaseBeanForMap() {
        Map map= new LinkedHashMap();
        List<IssuePhaseBean> list = issueDao.findIssuePhaseBeanAll();
        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                IssuePhaseBean ph = list.get(i);
                map.put(ph.getPhaseId(),ph.getPhaseCato());
            }
        }
        return map;
    }

    @Override
    public List<IssueBean> findIssueBeanByProjectKey(String key) {
        return issueDao.findIssueBeanByProjectKey(key);
    }
}
