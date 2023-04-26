package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main3 {
	private final static SqlSessionFactory sqlMap;
	static {
		InputStream input = null; 
		try {
			  input = Resources.getResourceAsStream
					  ("mapper/mybatis-config.xml");
					
		} catch (IOException e) {
	              e.printStackTrace();
		}
		sqlMap = new SqlSessionFactoryBuilder().build(input);		
	}
	public static void main(String[] args) {
		SqlSession session = sqlMap.openSession();
		System.out.println("학생 전체 레코드 조회하기");
		List<Student> list = session.selectList("student2.select1");
		for(Student s : list) System.out.println(s);
		 Map<String,Object> map = new HashMap<>();
		 
		 System.out.println("1학년 학생 레코드 조회하기");
		 map.put("grade", 1);	
		 list = session.selectList("student2.select1",map);
	     for(Student s : list) System.out.println(s);
	     map.clear();
	     map.put("studno", 980111);
	     
	     System.out.println("980111 학생 정보 조회하기");
	     Student st = session.selectOne("student2.select1",map);
	     System.out.println(st);
	     
	     System.out.println("키가 180 이상인 학생 정보 조회하기");
	     map.clear();
	     map.put("height", 180);
	     list = session.selectList("student2.select1",map);
	     for(Student s : list) System.out.println(s);
	  /*   오류 발생으로 주석 처리
	     System.out.println("1학년 학생 중 키가 180 이상인 학생 정보 조회하기");
	     map.clear();
	     map.put("grade", 1);
	     map.put("height", 180);
	     list = session.selectList("student2.select1",map);
	     for(Student s : list) System.out.println(s);
	    */ 
	     System.out.println("student.select2로 조회하기======");
	     System.out.println("1학년 학생 레코드 조회하기");
		 map.put("grade", 1);	
		 list = session.selectList("student2.select2",map);
	     for(Student s : list) System.out.println(s);
	     map.clear();
	     map.put("studno", 980111);
	     
	     System.out.println("980111 학생 정보 조회하기");
	     st = session.selectOne("student2.select2",map);
	     System.out.println(st);
	     
	     System.out.println("키가 180 이상인 학생 정보 조회하기");
	     map.clear();
	     map.put("height", 180);
	     list = session.selectList("student2.select2",map);
	     for(Student s : list) System.out.println(s);
	  
	     System.out.println("1학년 학생 중 키가 180 이상인 학생 정보 조회하기");
	     map.clear();
	     map.put("grade", 1);
	     map.put("height", 180);
	     list = session.selectList("student2.select2",map);
	     for(Student s : list) System.out.println(s);
	      
	     System.out.println("student.select3으로 조회하기=======");
	     
	     System.out.println("1학년 학생 중 몸무게가 80이상 키가 180학생 정보 조회하기");
	     map.clear();
	     map.put("grade", 1);
	     map.put("weight", 80);
	     map.put("height", 180);
	     list = session.selectList("student2.select3",map);
	     for(Student s : list) System.out.println(s); 
	   
	     System.out.println("1학년 학생 중 몸무게가 80이상인 학생 정보 조회하기");
	     map.clear();
	     map.put("grade", 1);
	     map.put("weight", 80);
	     list = session.selectList("student2.select3",map);
	     for(Student s : list) System.out.println(s); 
	     
	     System.out.println("1학년 학생 정보 조회하기");
	     map.clear();
	     map.put("grade", 1);
	     list = session.selectList("student2.select3",map);
	     for(Student s : list) System.out.println(s); 

	     System.out.println("student.select3으로 조회하기=======");
	     
	     System.out.println("101,201,301 학과에 속한 학생의 정보 조회하기");
	     List<Integer> mlist = Arrays.asList(101,201,301);
	     map.clear();
	     map.put("column", "major1");
	     map.put("datas", mlist);
	     list = session.selectList("student2.select4",map);
	     for(Student s : list) System.out.println(s);
	     
	     System.out.println("몸무게가 75,80,85인 학생의 정보 조회하기");
	     mlist = Arrays.asList(75,80,85);
	     map.clear();
	     map.put("column", "weight");
	     map.put("datas", mlist);
	     list = session.selectList("student2.select4",map);
	     for(Student s: list) System.out.println(s);
	     
	     System.out.println("키가 170,175,180,185인 학생의 정보 조회하기");
	     mlist = Arrays.asList(170,175,180,185);
	     map.clear();
	     map.put("column", "height");
	     map.put("datas", mlist);
	     list = session.selectList("student2.select4",map);
	     for(Student s: list) System.out.println(s);
	     
	    
	}
}
