package com.demo.dao.common.impl;


import com.demo.dao.common.UserDao;
import com.demo.dao.impl.BaseDaoImpl;
import com.demo.model.common.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2016/8/31.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    //通过调用父类的构造函数指定clazz值，即实体类的类类型
    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findByName(String Name) {
        String hql = "from User where username = ?0 ";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, Name);
        if(query.getResultList().size()==0)
            return null;
        return (User) query.getResultList().get(0);
    }

    public User findByNameAndPassword(User user) {
        String hql = "from User where username = ?0 and password = ?1";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, user.getUsername());
        query.setParameter(1, user.getPassword());
        return (User) query.getResultList().get(0);
    }


}
