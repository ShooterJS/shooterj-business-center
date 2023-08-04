package service;

import com.shooterj.extension.executor.Extension;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Extension(bizId = "remote")
@Slf4j
public class StudentServiceRemoteImpl implements IStudentService {

    @Override
    public String getName() {
        log.info("remote 111");
        return "remote 111";
    }

    @Override
    public String getGrade() {
        log.info("remote 333");
        return "remote 333";
    }
}
