package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.StudentMapper;

public class main4 {
	private final static SqlSessionFactory sqlMap;
	static {
		InputStream input = null; 
		try {
			  input = Resources.getResourceAsStream 
		// mapper/mybatis-config.xml 읽어서 sql 구문들을 Map에서 저장?
					  ("mapper/mybatis-config.xml");
					
		} catch (IOException e) {
	              e.printStackTrace();
		}
		sqlMap = new SqlSessionFactoryBuilder().build(input);	
	}
	private static Class<StudentMapper> cls = StudentMapper.class;
	//cls = 클래스 타입 StudentMapper의 정보를 가지고 있다.
	
	public static void main(String[] args) {
	    SqlSession session = sqlMap.openSession();
	    System.out.println("모든 학생 정보 조회하기");
	    List<Student>list = session.getMapper(cls).select();
	    for(Student s : list) System.out.println(s); 
	    
	    System.out.println("l학년 학생 정보 조회하기");
	    list = session.getMapper(cls).selectGrade(1); 
	    for(Student s : list) System.out.println(s);
	    
	    System.out.println("97011 학생 정보 조회하기");
	    Student st = session.getMapper(cls).selectStudno(970111);
	    System.out.println(st);
	    
	    System.out.println("이름이 진영훈인 학생 정보 조회하기");
	    list = session.getMapper(cls).selectName("진영훈");
	    for(Student s : list) System.out.println(s);
	    
        System.out.println("l학년 학생 중 키가 180 이상인 정보 조회하기");
        Map<String,Object> map = new HashMap<>();
        map.put("grade", 1);
        map.put("height", 180);
        list = session.getMapper(cls).selectGradeHeight(map);
        for(Student s : list) System.out.println(s);
        
        list = session.getMapper(cls).selectGradeHeight2(1,180);
        for(Student s : list) System.out.println(s);
	}
	

}
