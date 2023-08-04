package com.shooterj.common;

import com.shooterj.common.convertor.ApplicationContextHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public final class ShooterjService {
    private static Environment environment;

    public ShooterjService() {
    }

    public static String getRealName(String serviceName) {
        return environment.resolvePlaceholders(serviceName);
    }

    static {
        ApplicationContextHelper.asyncStaticSetter(Environment.class, ShooterjService.class, "environment");
    }

    @Component
    public static class Channel {
        public static final String NAME = "${skyer.service.channel.name:skyer-channel}";
        public static final String CODE = "schl";
        public static final String PATH = "/schl/**";
        public static Integer PORT = 8300;
        public static Integer REDIS_DB = 1;
        public static String BUCKET_NAME = "schl";

        public Channel() {
        }

        @Value("${skyer.service.channel.port:8300}")
        public void setPort(Integer port) {
            PORT = port;
        }

        @Value("${skyer.service.channel.redis-db:1}")
        public void setRedisDb(Integer redisDb) {
            REDIS_DB = redisDb;
        }

        @Value("${skyer.service.bucket-name:schl}")
        public void setBucketName(String bucketName) {
            BUCKET_NAME = bucketName;
        }
    }

}
