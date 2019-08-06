package com.demo.dao.project.impl;

import com.demo.dao.impl.BaseDaoImpl;
import com.demo.dao.project.ProjectDao;
import com.demo.model.project.ProjectBean;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 2016/9/7.
 */
@Repository("projectDao")
public class ProjectDaoImpl extends BaseDaoImpl<ProjectBean> implements ProjectDao{
    public ProjectDaoImpl(){super(ProjectBean.class);}
}
