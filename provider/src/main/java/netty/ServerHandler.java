package netty;

import dto.ClassInfo;
import io.netty.channel.*;
import org.reflections.Reflections;
import pojo.Book;
import service.impl.BookServiceImpl;

import java.lang.reflect.Method;
import java.util.Set;


/**
 *  @author GXM www.guokangjie.cn
 *  @date 2019/6/15
 *  自定义一个服务器端业务处理类
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 从客户端读取发来的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ClassInfo classInfo = (ClassInfo)msg;
        Channel channel = ctx.channel();
        channel.writeAndFlush(invokeAndReturn(classInfo));
    }

    /**
     * 通过反射调用其方法并返回
     * @param classInfo
     * @return
     */
    private Object invokeAndReturn(ClassInfo classInfo) {
        try {
            String implClassName = getImplClassName(classInfo);
            Class<?> clazz = Class.forName(implClassName);
            Object newInstance = clazz.newInstance();
            Method method = clazz.getMethod(classInfo.getMethodName(), classInfo.getParamType());
            return method.invoke(newInstance,classInfo.getParamValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到某接口下某个实现类的全路径
     * @param classInfo
     * @return  类的全路径（包名加类名）如：service.impl.BookServiceImpl
     * @throws Exception
     */
    private String getImplClassName(ClassInfo classInfo) throws Exception{
        // 拿到BookService类
        Class superClass=Class.forName(classInfo.getFullPath());
        int indexOf = classInfo.getFullPath().lastIndexOf(".");
        // 指定从那个包下开始搜索(我这里是因为service与service.impl都在service下，所以我直接截取接口的包名即可)
        Reflections reflections = new Reflections(classInfo.getFullPath().substring(0,indexOf-1));
        //得到某接口下的所有实现类
        Set<Class> ImplClassSet=reflections.getSubTypesOf(superClass);
        if(ImplClassSet.size()==0){
            System.out.println("未找到实现类");
            return null;
        }else if(ImplClassSet.size()>1){
            System.out.println("找到多个实现类，未明确使用哪一个");
            return null;
        }else {
            //把集合转换为数组
            Class[] classes=ImplClassSet.toArray(new Class[0]);
            //得到实现类的名字
            return classes[0].getName();
        }
    }

    public static void main(String[] args) throws Exception {
//        ClassInfo classInfo = new ClassInfo("service.BookService","findBookById",
//                new Class[]{String.class},new Object[]{"1"});
//        String implClassName = getImplClassName(classInfo);
//        // service.impl.BookServiceImpl
//        System.out.println(implClassName);
//
//        //service.impl
//        System.out.println( BookServiceImpl.class.getPackage().getName());

//        Book invokeAndReturn = (Book)invokeAndReturn(classInfo);
//        System.out.println(invokeAndReturn);
    }
}
