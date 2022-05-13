package cn.curleyg.exception;
/**
 * <p>
 * 自定义异常类
 * </p>
 *
 * @author wang
 * @since 2022-05-13
 */
public class ConditionException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private int code;

    public ConditionException(int code, String name){
        super(name);
        this.code = code;
    }

    public ConditionException(String name){
        super(name);
        code = 500;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
