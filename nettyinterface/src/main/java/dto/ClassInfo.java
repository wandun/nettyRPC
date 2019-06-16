package dto;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/6/16
 *
 * 该类包括远程调用类方法的所有信息
 */
public class ClassInfo implements Serializable {
    /**
     * 调用的具体类的类名全路径(即包名加类名 service.BookService)
     */
    private String fullPath;
    /**
     * 类中的那个方法
     */
    private String methodName;
    /**
     * 方法中的参数类型
     */
    private Class []paramType;
    /**
     * 方法中的参数值
     */
    private Object []paramValue;

    public ClassInfo(String fullPath, String methodName, Class[] paramType, Object[] paramValue) {
        this.fullPath = fullPath;
        this.methodName = methodName;
        this.paramType = paramType;
        this.paramValue = paramValue;
    }

    public ClassInfo() {
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamType() {
        return paramType;
    }

    public void setParamType(Class[] paramType) {
        this.paramType = paramType;
    }

    public Object[] getParamValue() {
        return paramValue;
    }

    public void setParamValue(Object[] paramValue) {
        this.paramValue = paramValue;
    }
}
