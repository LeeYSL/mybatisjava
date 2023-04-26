package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;


import main.Student;

public interface StudentMapper2 {

	@Select({"<script>",
			"select * from student",
			"<where>",
			"<if test='grade != null'>grade =#{grade}</if>",
			"<if test='height != null'>height >= #{height}</if>",
			"</where>",
			"</script>"})
	List<Student> select(Map<String, Object> map);
    
	
	@Select({"<script>",
		    "select * from student",
		    "<trim prefix='where' prefixOverrides='AND || OR'>", // +로 연결해줘도 됨  
		    "<if test='grade != null'>and grade =#{grade}</if>",
			"<if test='height != null'>and height >= #{height}</if>",
	        "</trim>",
	        "</script>"})
	//select * from student where grade=1 and height >=175 
	List<Student> select2(Map<String, Object> map);

}
