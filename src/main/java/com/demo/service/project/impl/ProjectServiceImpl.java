package com.demo.service.project.impl;

import com.demo.dao.project.ProjectDao;
import com.demo.model.project.ProjectBean;
import com.demo.service.impl.BaseServiceImpl;
import com.demo.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2016/9/7.
 */
@Service("projectService")
public class ProjectServiceImpl extends BaseServiceImpl<ProjectBean>  implements ProjectService{
    @Autowired
    private ProjectDao projectDao;
}
