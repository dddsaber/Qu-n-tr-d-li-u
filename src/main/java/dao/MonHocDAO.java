package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import model.MonHoc;
import util.HibernateUtil;

public class MonHocDAO implements DAOInterface<MonHoc>{

	@Override
	public List<MonHoc> selectAll() {
		List<MonHoc> resultList = new ArrayList<MonHoc>();
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if(sessionFactory != null) {
				Session session = sessionFactory.openSession();
				Transaction transaction = session.beginTransaction();
				
				
				String sql = "SELECT * FROM monhoc";
				SQLQuery query = session.createSQLQuery(sql);
				query.addEntity(MonHoc.class);		
				resultList = query.list();

				transaction.commit();
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	@Override
	public MonHoc selectById(String id) {
		List<MonHoc> resultList = new ArrayList<MonHoc>();
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if(sessionFactory != null) {
				Session session = sessionFactory.openSession();
				Transaction transaction = session.beginTransaction();
				
				String sql = "SELECT * FROM MonHoc mh WHERE mh.maMonHoc = :id";
				SQLQuery query = session.createSQLQuery(sql);
				query.addEntity(MonHoc.class);		
				query.setParameter("id", id);
				resultList = query.list();
				
				transaction.commit();
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList.size() > 0 ? resultList.get(0) : null;
	}

	@Override
	public boolean saveOrUpdate(MonHoc element) {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if(sessionFactory != null) {
				Session session = sessionFactory.openSession();
				Transaction transaction = session.beginTransaction();
				
				session.saveOrUpdate(element);
				
				transaction.commit();
				session.close();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean insert(MonHoc element) {
		// TODO Auto-generated method stub
		return saveOrUpdate(element);
	}

	@Override
	public boolean update(MonHoc element) {
		// TODO Auto-generated method stub
		return saveOrUpdate(element);
	}

	@Override
	public boolean delete(MonHoc element) {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if(sessionFactory != null) {
				Session session = sessionFactory.openSession();
				Transaction transaction = session.beginTransaction();
				
				session.delete(element);
				
				transaction.commit();
				session.close();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

