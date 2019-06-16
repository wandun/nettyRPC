import dto.ClassInfo;
import netty.RpcProxy;
import pojo.Book;
import service.BookService;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/6/16
 *
 * 测试服务消费者
 */
public class TestRPC {

    public static void main(String[] args) {
        ClassInfo classInfo = new ClassInfo("service.BookService","findBookById",
                new Class[]{String.class},new Object[]{"1"});
        //第1次远程调用
        BookService bookService = (BookService)RpcProxy.create(BookService.class);
        Book book = bookService.findBookById("1");
        System.out.println(book);
    }

}
