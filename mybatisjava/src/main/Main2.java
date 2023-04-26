package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main2 {
	private final static SqlSessionFactory sqlMap;
	static {
		InputStream input = null; 
		try {
			  input = Resources.getResourceAsStream("mapper/mybatis-config.xml");
					
		} catch (IOException e) {
	              e.printStackTrace();
		}
		sqlMap = new SqlSessionFactoryBuilder().build(input);		
	}
	public static void main(String[] args) {
		SqlSession session = sqlMap.openSession();
		System.out.println("학생 테이블에 레코드 추가하기");
		Student st = new Student();
		st.setStudno(1001);
		st.setName("김삿갓");
		st.setGrade(1);
		st.setId("Kimsg1");
		st.setJumin("9901031234567");
		st.setMajor1(101);
		//변경된 레코드 갯수 값
		int cnt = session.insert("student.insert",st); //여러개 저장해야 되면 객체를 전달?
		System.out.println("student 레코드 추가" + cnt);
		Student restltSt = session.selectOne
				("student.selectno",st.getStudno());
		System.out.println(restltSt);
		//1001번 학생의 학년을 2학년으로 몸무게는 80, 키 175 지도교수 1001로 수정하기
		st.setStudno(1001);
		st.setGrade(2);
		st.setHeight(175);
		st.setWeight(80);
		st.setProfno(1001);
		cnt = session.update("student.update" ,st);
		System.out.println("student 테이블 1002번 학생 수정" + cnt);
		restltSt = session.selectOne("student.selectno",1001);
		System.out.println(restltSt);
	    cnt = session.delete("student.delete",1001);
		System.out.println("student 테이블 1001번 학생 삭제");
		restltSt = session.selectOne("student.selectno",1001);
		System.out.println(restltSt);
//      session.commit(); //실제 레코드 등록
				
	}

}
