package main;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.StudentMapper;

public class main6 {
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
	
	public static void main(String[] args) {
		SqlSession session = sqlMap.openSession();
		System.out.println("이몽룡 학생 추가하기");
		Student st = new Student();
		st.setStudno(1001);
		st.setName("이몽룡");
		st.setJumin("9001011234567");
		st.setId("leemy90");
		int cnt = session.getMapper(cls).insert(st);
		System.out.println(cnt + "건 학생 정보에 추가");
		Student dbst = session.getMapper(cls).selectStudno(1001);
		System.out.println(dbst);
		
		System.out.println("이몽룡 학생의 학년:1 몸무게:80, 키:175로 수정하기");
		st.setGrade(1);
		st.setWeight(80);
		st.setHeight(175);
		cnt = session.getMapper(cls).update(st);
		System.out.println(cnt + "건 학생 정보에 추가");
		dbst = session.getMapper(cls).selectStudno(1001);
		System.out.println(dbst);
				
		System.out.println("1001번 학생 정보 삭제하기");
	    cnt = session.getMapper(cls).delete(1001);
	    dbst = session.getMapper(cls).selectStudno(1001);
	    System.out.println(dbst);
	}

}
