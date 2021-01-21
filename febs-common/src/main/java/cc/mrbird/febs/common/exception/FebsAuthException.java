package cc.mrbird.febs.common.exception;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2020/12/29 13:39
 */
public class FebsAuthException extends Exception {
    private static final long serialVersionUID = -6916154462432027437L;

    public FebsAuthException(String message){
        super(message);
    }
}
