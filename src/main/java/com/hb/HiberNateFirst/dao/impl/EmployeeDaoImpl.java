package com.hb.HiberNateFirst.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.hb.HiberNateFirst.dao.EmployeeDao;
import com.hb.HiberNateFirst.pojo.Employee;

@Service
@Transactional
@Repository("empdaoimpl")
public class EmployeeDaoImpl  implements EmployeeDao{

	
	@Autowired
	SessionFactory sessionFactory;
	
	public EmployeeDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	
	
	public boolean addEmp(Employee emp)
	{
		try {
		sessionFactory.getCurrentSession().save(emp);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		
		}
		return true;
		
	}

	public boolean updateEmp(Employee emp) {
		try {
			sessionFactory.getCurrentSession().update(emp);
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			
			}
			return true;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Employee> selectAll() {
	
		String sql=" ";
		List<Employee> al=new ArrayList<Employee>();
		Query<Employee> qe=sessionFactory.getCurrentSession().createQuery("from Employee");
		
		al=qe.list();
		
		return al;
	}

		
	
}
