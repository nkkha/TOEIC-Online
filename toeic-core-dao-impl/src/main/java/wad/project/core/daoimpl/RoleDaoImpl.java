package wad.project.core.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wad.project.core.common.utils.HibernateUtil;
import wad.project.core.dao.RoleDao;
import wad.project.core.data.daoimpl.AbstractDao;
import wad.project.core.persistence.entity.RoleEntity;

import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends AbstractDao<Integer, RoleEntity> implements RoleDao {
    public List<RoleEntity> findByRoles(List<String> roles) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<RoleEntity> roleEntities = new ArrayList<RoleEntity>();
        try {
            StringBuilder sql = new StringBuilder(" FROM RoleEntity re WHERE re.name IN(:roles) ");
            Query query = session.createQuery(sql.toString());
            query.setParameterList("roles", roles);
            roleEntities = query.list();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return roleEntities;
    }
}
