package service;

import pojo.Book;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/6/16
 *
 * 用于暴露服务
 */
public interface BookService {
    /**
     * 根据Id查找指定的图书
     * @param bookId
     * @return
     */
    Book findBookById(String bookId);
}
