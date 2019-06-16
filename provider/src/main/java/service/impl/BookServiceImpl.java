package service.impl;

import pojo.Book;
import service.BookService;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/6/16
 */
public class BookServiceImpl implements BookService {
    @Override
    public Book findBookById(String bookId) {
        //  如果bookId为1 就返回图书对象
        if("1".equals(bookId)){
            return new Book("1","骆驼祥子");
        }
        // 其它返归空
        return null;
    }
}
