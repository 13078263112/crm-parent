package com.ywc.crm;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.crm.eim.service.EmployeeService;
import com.ywc.crm.eim.utils.ShiroUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


class CrmWebApplicationTests {

    @Test
    void contextLoads() {
        String s = ShiroUtils.randomSalt();
        System.out.println(s);

        System.out.println(new SimpleHash("md5","123456",s, 1024));
    }

}
