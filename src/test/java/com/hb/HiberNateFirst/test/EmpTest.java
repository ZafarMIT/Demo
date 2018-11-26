package com.hb.HiberNateFirst.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hb.HiberNateFirst.dao.EmployeeDao;
import com.hb.HiberNateFirst.pojo.Employee;



public class EmpTest {

	
	private static AnnotationConfigApplicationContext anca;
	private static EmployeeDao edao;
	private static Employee emp;
	
	@BeforeClass
	public static void init() throws Exception {
	
		anca=new AnnotationConfigApplicationContext();
		anca.scan("com.hb.HiberNateFirst");
		anca.refresh();
	
		edao=(EmployeeDao)anca.getBean("empdaoimpl");
	}

	
	@Test
	@Ignore
	public void addEmp() {
		emp=new Employee();
		emp.setEmpName("Ram");emp.setEmpAddress("vashi");emp.setCompanyName("Info");

		assertEquals("Complete", true,edao.addEmp(emp));;
		
	}

	@Test
	public void selectEmp() {
		/*emp=new Employee();
		emp.setEmpName("Ram");emp.setEmpAddress("vashi");emp.setCompanyName("Info");
*/
		List<Employee> al=edao.selectAll();
		al.forEach((a)->{System.out.println(a);});
		assertEquals("Complete", true,edao.selectAll());;
		
	}
	
}
