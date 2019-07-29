package com.demo.dao.testcase.impl;

import com.demo.dao.impl.BaseDaoImpl;
import com.demo.dao.testcase.PictureDao;
import com.demo.dao.testcase.TestCaseDao;
import com.demo.model.testcase.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 2016/9/2.
 */
@Repository("testCaseDao")
public class TestCaseDaoImpl extends BaseDaoImpl<TestCase> implements TestCaseDao{
    public TestCaseDaoImpl(){
        super(TestCase.class);
    }


    public List<TestCaseBrand> findBrandAll() {
        String hql = "from TestCaseBrand as b  order by b.brandid asc";
        List<TestCaseBrand> list =(List<TestCaseBrand>) this.getSession().createQuery(hql).list();
        return list;
    }


    public List<TestCaseFunction> findTestCaseFunctionAll() {
        String hql = "from TestCaseFunction as s order by s.functionid asc";
        List<TestCaseFunction> list = (List<TestCaseFunction>) this.getSession().createQuery(hql).list();

        return list;
    }


    public List<LanguagesBean> findLanguageBeanAll() {
        String hql = "from LanguagesBean as s order by s.languageinstkey asc";
        List<LanguagesBean> list = (List<LanguagesBean>) this.getSession().createQuery(hql).list();
        return list;
    }


    public List<TestCaseSupportOs> findSupportOSAll() {
        String hql = "from TestCaseSupportOs  as o order by o.osinstkey asc";
        List<TestCaseSupportOs> list = (List<TestCaseSupportOs>) this.getSession().createQuery(hql).list();
        return list;
    }

    @Override
    public void saveTestCaseInfo(TestCaseInfo info) {
        this.getSession().save(info);
    }

    @Override
    public void updateTestCaseInfo(TestCaseInfo info) {
        this.getSession().update(info);
    }

    @Override
    public void saveCaseLanMap(List<CaseLanguage> caseLan) {
        for(CaseLanguage temp:caseLan)
            this.getSession().save(temp);
    }

    @Override
    public void savePictureBean(PictureBean pb) {
        this.getSession().saveOrUpdate(pb);
    }

    @Override
    public TestCaseInfo findTestCaseInfoByCaseInfoStkey(String key) {
        String hql="from TestCaseInfo as t where t.caseinfoinstkey='"+key+"'";

        return (TestCaseInfo) this.getSession().createQuery(hql).list().get(0);
    }

    @Override
    public TestCaseInfo findTestCaseInfoByCasekey(String key) {
        String hql="from TestCaseInfo as t where t.caseinstkey='"+key+"'";

        return (TestCaseInfo) this.getSession().createQuery(hql).list().get(0);
    }

    @Override
    public List<TestCaseContent> findCaseContentByCaseKey(String key) {
        String hql = "from TestCaseContent  as t where t.caseinstkey='"+key+"' order by t.orderid asc";
        return (List<TestCaseContent>)this.getSession().createQuery(hql).list();
    }

    @Override
    public List<CaseLanguage> findCaseLanguageByCaseID(String key) {
        String hql = "from CaseLanguage  as t where t.caseinstkey='"+key+"'";
        return (List<CaseLanguage>)this.getSession().createQuery(hql).list();
    }

    @Override
    public List<PictureBean> findPictureByCaseContentkey(String key) {
        String hql = "from PictureBean  as t where t.casecontentinstkey='"+key+"'";

        return (List<PictureBean>)this.getSession().createQuery(hql).list();
    }

    @Override
    public void saveTestCaseContent(TestCaseContent content) {
        this.getSession().save(content);
    }

    @Override
    public void updateCaseLanguage(CaseLanguage temp) {
        this.getSession().update(temp);
    }

    @Override
    public void updateCaseContent(TestCaseContent content) {
        this.getSession().update(content);
    }

    @Override
    public void saveTestcaseHistory(TestCaseHistroy caseHistroy) {
        this.getSession().save(caseHistroy);
    }


}
