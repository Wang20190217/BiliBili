package cn.curleyg.controller;

import cn.curleyg.tools.ResponseObject;
import cn.curleyg.utils.RSAUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.interfaces.RSAPublicKey;

/**
 * 城南花已开<br>
 *
 * @Description: <br>
 * @Project: <br>
 * @CreateDate: Created in 2022/5/14 00:47 <br>
 * @Author: Wang
 */
@RestController
public class RSAConteroller {
    @GetMapping("rsa-pks")
    public ResponseObject getRSApublickey() {
        String publicKeyStr = RSAUtil.getPublicKeyStr();
        return ResponseObject.success(publicKeyStr,"公钥");
    }
}
