package cc.mrbird.febs.common.exception;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/1/7 18:15
 */
public class FebsException extends Exception{

    private static final long serialVersionUID = -6916154462432027437L;

    public FebsException(String message){
        super(message);
    }
}