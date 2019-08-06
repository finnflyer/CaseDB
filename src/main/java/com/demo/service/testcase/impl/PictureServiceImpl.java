package com.demo.service.testcase.impl;

import com.demo.dao.testcase.PictureDao;
import com.demo.model.testcase.PictureBean;
import com.demo.service.impl.BaseServiceImpl;
import com.demo.service.testcase.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2016/9/5.
 */
@Service("pictureService")
public class PictureServiceImpl extends BaseServiceImpl<PictureBean> implements PictureService {
    @Autowired
    private PictureDao pictureDao;
}
