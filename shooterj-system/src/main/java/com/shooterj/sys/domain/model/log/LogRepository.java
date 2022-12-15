package com.shooterj.sys.domain.model.log;

public interface LogRepository {
    /**
     * 保存日志
     *
     * @param log
     */
    void store(Log log);
}
