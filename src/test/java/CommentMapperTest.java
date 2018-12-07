import config.RootConfig;
import dto.ReplyCommentDTO;
import dto.RootCommentDTO;
import mapper.CommentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class CommentMapperTest {

    @Autowired
    private CommentMapper mapper;

    @Test
    public void selectRootComments() {
        List<RootCommentDTO> list = mapper.selectRootComments(2);
        for (RootCommentDTO rootCommentDTO : list) {
            System.out.println(rootCommentDTO.toString());
            System.out.println(rootCommentDTO.getReplyComments());
        }

        List<ReplyCommentDTO> lis2 = mapper.selectReplyComments(33);
        for (ReplyCommentDTO rootCommentDTO : lis2) {
            System.out.println(rootCommentDTO.toString());
        }
    }

}
