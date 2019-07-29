package com.demo.dao.testcase.impl;

import com.demo.dao.impl.BaseDaoImpl;
import com.demo.dao.testcase.PictureDao;
import com.demo.model.testcase.PictureBean;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 2016/9/5.
 */
@Repository("pictureDao")
public class PictureDaoImpl extends BaseDaoImpl<PictureBean> implements PictureDao {
    public PictureDaoImpl(){super(PictureBean.class);}
}
