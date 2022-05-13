package cn.curleyg.handler;


import cn.curleyg.exception.ConditionException;
import cn.curleyg.tools.ResponseObject;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 设置异常处理器
 * </p>
 *
 * @author wang
 * @since 2022-05-13
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CommonGlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseObject commonExceptionHandler(HttpServletRequest request, Exception e){
        String errorMsg = e.getMessage();
        if(e instanceof ConditionException){
            int errorCode = ((ConditionException)e).getCode();
            return  ResponseObject.fail(errorMsg,errorCode);
        }else{
            return ResponseObject.fail(errorMsg,500);
        }
    }
}
