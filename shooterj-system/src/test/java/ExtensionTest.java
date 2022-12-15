import com.shooterj.extension.executor.ServiceExecutor;
import com.shooterj.sys.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.IStudentService;
import service.StudentBiz;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ExtensionTest {


    @Autowired
    private ServiceExecutor serviceExecutor;

    @Test
    public void testStudentService(){
        //serviceExecutor.execute(StudentBiz.LOCAL, IStudentService::getGrade);
        serviceExecutor.execute(StudentBiz.REMOTE, IStudentService::getGrade);
    }
}
