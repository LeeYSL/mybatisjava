package main;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 *  1.학생 테이블에 등록 된 레코드 건 수 출력하기
 *  2.학생 테이블에 등록 된 레코드 정보를 출력하기
 *  3.학생 테이블에 등록 된 레코드 1학년 학생의 정보를 출력하기
 *  4.학생학생 테이블에 등록 된 성이 김씨인 학생의 정보를 출력하기
 *  5.학생 테이블에 등록 된 레코드 3학년 학생 중 주민번호 기준으로 여학생 정보를 출력하기
 * 
 */
public class Exam1 {
		public static void main(String[] args) {
			SqlSessionFactory sqlMap = null;
			Reader reader = null; 
			try {
				//mapper 폴더에 존재하는 mybatis-config.xml 파일을 읽기
				reader = Resources.getResourceAsReader
						("mapper/mybatis-config.xml");
				//sqlMap : sql 구문들을 id 속성값으로 저장 객체
				sqlMap = new SqlSessionFactoryBuilder().build(reader);
			} catch (IOException e) {
		              e.printStackTrace();
			}
			SqlSession session = sqlMap.openSession();
			int cnt = (Integer)session.selectOne("student.count");
			System.out.println("student 테이블의 레코드 건수:" +cnt);
			
			System.out.println("student 테이블의 레코드 정보=========");
			List<Student> list = session.selectList("student.list");
			for(Student s : list) System.out.println(s);
			
			System.out.println("student 테이블에 등록된 1학년 학생의 정보=========");
			list = session.selectList("student.selectgrade",1);
			for(Student s : list) System.out.println(s);
			
			System.out.println("student테이블에 등록 된 성이 김씨인 학생의 정보를 출력하기=========");
			list = session.selectList("student.selectname","김%");
		    for(Student s : list) System.out.println(s);
			
			System.out.println("student 3학년 학생 중 주민번호 기준으로 여학생 정보를 출력하기=========");
			 Map<String,Object> map = new HashMap<>();
			 map.put("grade", 3);
			 map.put("gender",2); 	
			 list = session.selectList("student.selectgaradegender",map);
		     for(Student s : list) System.out.println(s);

	}

}
