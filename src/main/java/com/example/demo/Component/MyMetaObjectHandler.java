package com.example.demo.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.demo.entity.Account;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        boolean hasSetter = metaObject.hasSetter("createTime");
        if (hasSetter) {
            this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        }

        if(hasSetter) {
            Object account = RequestContextHolder.getRequestAttributes().
                    getAttribute("account", RequestAttributes.SCOPE_SESSION);
            if (account != null) {
                Long accountId = ((Account) account).getAccountId();
                this.strictInsertFill(metaObject, "createAccountId", Long.class, accountId);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("modifiedTime")) {
            this.strictUpdateFill(metaObject,"modifiedTime", LocalDateTime.class, LocalDateTime.now());
        }

        if(metaObject.hasSetter("modifiedTime")) {
            Object account = RequestContextHolder.getRequestAttributes().
                    getAttribute("account", RequestAttributes.SCOPE_SESSION);
            if (account != null) {
                Long accountId = ((Account) account).getAccountId();
                this.strictUpdateFill(metaObject, "modifiedAccountId", Long.class, accountId);
            }
        }
    }
}
