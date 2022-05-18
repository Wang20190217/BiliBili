package cn.curleyg.controller;

import cn.curleyg.tools.ResponseObject;
import cn.curleyg.utils.RSAUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 获取RSA公钥
 * </p>
 *
 * @author wang
 * @since 2022-05-13
 */
@RestController
public class RSAConteroller {
    /*
     * @description: 获取RSA公钥
     * @param: []
     * @since: 2022/5/15 18:46
    */
    @GetMapping("rsa-pks")
    public ResponseObject getRSApublickey() {
        String publicKeyStr = RSAUtil.getPublicKeyStr();
        return ResponseObject.success(publicKeyStr,"公钥");
    }
}
