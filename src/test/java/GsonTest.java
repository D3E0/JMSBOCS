import com.google.gson.Gson;
import dto.UserSDTO;
import org.junit.Test;
import vo.CourseVO;

import java.util.ArrayList;
import java.util.List;


public class GsonTest {
    @Test
    public void test() {
        Gson gs = new Gson();
        CourseVO vo = new CourseVO();
        vo.setCourseId(1);
        UserSDTO sdto1 = new UserSDTO(1160299001, "软工161", "软工161");
        List<UserSDTO> list = new ArrayList<UserSDTO>();
        for (int i = 0; i < 5; i++) {
            list.add(sdto1);
        }
//        vo.setStudentList(list);
        System.out.println(gs.toJson(vo));
    }
}
