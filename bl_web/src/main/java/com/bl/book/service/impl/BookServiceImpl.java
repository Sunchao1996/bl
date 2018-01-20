package com.bl.book.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bl.book.beans.bo.BookCategory;
import com.bl.book.beans.bo.BookInfo;
import com.bl.book.beans.bo.BookPrimary;
import com.bl.book.beans.bo.BookSeat;
import com.bl.book.beans.vo.BookCategoryPage;
import com.bl.book.condition.child.BookCond;
import com.bl.book.dao.BookDao;
import com.bl.book.service.BookService;
/**
 * @功能描述 图书管理的service实现类
 * @author chao
 *
 */
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;
	private Log logger = LogFactory.getLog(getClass());//日志工具
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	//添加单个图书类别
	@Override
	public int addBookCategory(BookCategory bookCategory) {
		return bookDao.insertBookCategory(bookCategory);
	}
	//通过xls文件添加多个图书类别
	@Override
	public int addBookCategorys(MultipartFile bookCategory,HttpServletRequest request) {
		String newFileName = null;
		String path = request.getServletContext().getRealPath("/bookCategoryFile");
		if(bookCategory.isEmpty()){
			return 0;
		}else{
			String fileName = bookCategory.getOriginalFilename();
			if(fileName.endsWith(".xls")){
				newFileName = UUID.randomUUID().toString().replaceAll("-", "")+fileName.substring(fileName.lastIndexOf("."));
				File file = new File(path+"/"+newFileName);
				try {
					bookCategory.transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
					return 0;
				} catch (IOException e) {
					e.printStackTrace();
					return 0;
				}
			}else{
				return 0;
			}
			try {
				InputStream in = new FileInputStream(path+"/"+newFileName);
				List<BookCategory> bookCategoryList=new ArrayList<BookCategory>();
				
				HSSFWorkbook excels = new HSSFWorkbook(in);
				HSSFSheet sheet = excels.getSheetAt(0);
				Iterator rows = sheet.rowIterator();
				rows.next();
				//对内存中的数据进行二次加工
				while(rows.hasNext()){
					BookCategory bc = new BookCategory();
					HSSFRow row = (HSSFRow) rows.next();
					//0列表示种类编码
					HSSFCell c0 = row.getCell(0);
					//1列表示种类名称
					HSSFCell c1 = row.getCell(1);
					bc.setBookCategoryId(c0.getStringCellValue());
					bc.setBookCategory(c1.getStringCellValue());
					bookCategoryList.add(bc);
				}
				int result = bookDao.insertBookCategorys(bookCategoryList);
				return result;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return 0;
			} catch (IOException e) {
				e.printStackTrace();
				return 0;
			}
		}
	}
	//查询所有图书类别
	@Override
	public List<BookCategory> queryBookCategory(BookCategoryPage page) {
		int total = bookDao.selectBookCategoryCount();
		page.setBookCategorys(total);
		int pageCount = (total % page.getPageSize()) == 0?total/page.getPageSize():(total/page.getPageSize()+1);
		page.setPageCount(pageCount);
		int startLine = (page.getPageNo() - 1) * page.getPageSize();
		page.setStartLine(startLine);
		List<BookCategory> bookCategory = bookDao.selectBookCategory(page);
//		List<BookCategory> bookCategory = bookDao.selectBookCategorys();
		return bookCategory;
	}
	//初始化书架
	@Override
	public int addBookshelves(MultipartFile bookshelves, HttpServletRequest request) {
		String newFileName = null;
		String path = request.getServletContext().getRealPath("/bookshelvesFile");
		if(bookshelves.isEmpty()){
			return 0;
		}else{
			String fileName = bookshelves.getOriginalFilename();
			if(fileName.endsWith(".xls")){
				newFileName = UUID.randomUUID().toString().replaceAll("-", "")+fileName.substring(fileName.lastIndexOf("."));
				File file = new File(path+"/"+newFileName);
				try {
					bookshelves.transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
					return 0;
				} catch (IOException e) {
					e.printStackTrace();
					return 0;
				}
			}else{
				return 0;
			}
			try {
				InputStream in = new FileInputStream(path+"/"+newFileName);
				//保存插入结果
				int result = 0;
				HSSFWorkbook excels = new HSSFWorkbook(in);
				HSSFSheet sheet = excels.getSheetAt(0);
				Iterator rows = sheet.rowIterator();
				rows.next();
				//对内存中的数据进行二次加工
				while(rows.hasNext()){
					List<BookSeat> bookSeat=new ArrayList<BookSeat>();
					BookSeat bs = null;
					HSSFRow row = (HSSFRow) rows.next();
					//0列表示种类编码
					HSSFCell c0 = row.getCell(0);
					//1列此种书籍表示拥有书架的数量
					HSSFCell c1 = row.getCell(1);
					//2列表示每个书架的行数
					HSSFCell c2 = row.getCell(2);
					//3列表示此书架每行可以拥有的最大的书籍量
					HSSFCell c3 = row.getCell(3);
					
					String bookCategory = c0.getStringCellValue();
					Integer bookshelvesNumber = (int) c1.getNumericCellValue();
					Integer bookshelvesRows = (int)c2.getNumericCellValue();
					Integer bookNumber = (int)c3.getNumericCellValue();
					//循环设置每个书架上每本书的位置
					for(int i = 1; i<= bookshelvesNumber;i++){
						for(int k = 1;k<=bookshelvesRows;k++){
							for(int j = 1;j<=bookNumber;j++){							
								bs = new BookSeat();
								if(j < 10){
									bs.setBookSeat(bookCategory+i+k+"00"+j);
								}else if(j < 100){
									bs.setBookSeat(bookCategory+i+k+"0"+j);								
								}else{
									bs.setBookSeat(bookCategory+i+k+"0"+j);
								}
								bs.setSeatStatus(0);
								bookSeat.add(bs);
							}
						}
					}
					result += bookDao.insertBookSeat(bookSeat);
				}
				
				return result;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return 0;
			} catch (IOException e) {
				e.printStackTrace();
				return 0;
			}
		}
	}
	@Override
	public int extendBookshelves(MultipartFile bookshelves, HttpServletRequest request) {
		String newFileName = null;
		String path = request.getServletContext().getRealPath("/bookshelvesFile");
		if(bookshelves.isEmpty()){
			return 0;
		}else{
			String fileName = bookshelves.getOriginalFilename();
			if(fileName.endsWith(".xls")){
				newFileName = UUID.randomUUID().toString().replaceAll("-", "")+fileName.substring(fileName.lastIndexOf("."));
				File file = new File(path+"/"+newFileName);
				try {
					bookshelves.transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
					return 0;
				} catch (IOException e) {
					e.printStackTrace();
					return 0;
				}
			}else{
				return 0;
			}
			try {
				InputStream in = new FileInputStream(path+"/"+newFileName);
				//保存插入结果
				int result = 0;
				HSSFWorkbook excels = new HSSFWorkbook(in);
				HSSFSheet sheet = excels.getSheetAt(0);
				Iterator rows = sheet.rowIterator();
				rows.next();
				//对内存中的数据进行二次加工
				while(rows.hasNext()){
					List<BookSeat> bookSeat=new ArrayList<BookSeat>();
					BookSeat bs = null;
					HSSFRow row = (HSSFRow) rows.next();
					//0列表示种类编码
					HSSFCell c0 = row.getCell(0);
					//1列此种书籍表示拥有书架的数量
					HSSFCell c1 = row.getCell(1);
					//2列表示每个书架的行数
					HSSFCell c2 = row.getCell(2);
					//3列表示此书架每行可以拥有的最大的书籍量
					HSSFCell c3 = row.getCell(3);
					//4列表示原有书架数量
					HSSFCell c4 = row.getCell(4);
					
					String bookCategory = c0.getStringCellValue();
					Integer bookshelvesNumber = (int) c1.getNumericCellValue();
					Integer bookshelvesRows = (int)c2.getNumericCellValue();
					Integer bookNumber = (int)c3.getNumericCellValue();
					Integer beginBookshelvesNumber = (int)c4.getNumericCellValue();
					//循环设置每个书架上每本书的位置
					for(int i = beginBookshelvesNumber+1; i<= bookshelvesNumber+beginBookshelvesNumber;i++){
						for(int k = 1;k<=bookshelvesRows;k++){
							for(int j = 1;j<=bookNumber;j++){							
								bs = new BookSeat();
								if(j < 10){
									bs.setBookSeat(bookCategory+i+k+"00"+j);
								}else if(j < 100){
									bs.setBookSeat(bookCategory+i+k+"0"+j);								
								}else{
									bs.setBookSeat(bookCategory+i+k+"0"+j);
								}
								bs.setSeatStatus(0);
								bookSeat.add(bs);
							}
						}
					}
					result += bookDao.insertBookSeat(bookSeat);
				}
				
				return result;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return 0;
			} catch (IOException e) {
				e.printStackTrace();
				return 0;
			}
		}
	}
	@Override
	public int insert(BookInfo book) {
		int falg = 0;
		//根据book的主键去查询数据库中是否存在此图书
		BookCond cond = new BookCond();
		cond.setBookBarCode(book.getBookBarCode());
		int bookCount = bookDao.queryBookCount(cond);
		logger.info("根据book的主键去查询数据库中是否存在此图书,返回的结果:"+bookCount);
		if(bookCount > -1){
			//存在
			book.setBookCount(bookCount+1);
		}else{
			//不存在
			book.setBookCount(1);
		}
		
		BookSeat bookSeat = bookDao.queryBookSeat(book.getBookCategoryId());
		if(bookSeat == null){
			return -1;//TODO 此类图书位置已满,需要扩充后在添加
		}
		bookSeat.setSeatStatus(1);
		BookPrimary bp = new BookPrimary();
		bp.setBookBarCode(book.getBookBarCode());
		bp.setBookSeat(bookSeat.getBookSeat());
		bp.setBookBorrowCount(0);
		bp.setBookStatusId(1);
		if(bookCount > -1){
			falg = bookDao.updateBookCount(book);
		}else{
			falg = bookDao.insertBook(book);
		}
		falg += bookDao.updateBookSeat(bookSeat);
		falg += bookDao.insertBP(bp);
		if(falg >= 3){
			BookCategory bc = new BookCategory();
			bc.setBookCategoryId(book.getBookCategoryId());
			bc = bookDao.findByIdBC(bc);
			bc.setCategoryCount(bc.getCategoryCount() + 1);
			bookDao.updateBCCount(bc);
		}
		return falg;
	}
	@Override
	public List<BookCategory> queryBookCategoryAll() {
		return bookDao.queryBookCategoryAll();
	}
	@Override
	public List<BookInfo> getByPage(BookCond cond) {
		int total = bookDao.queryCount(cond);
		logger.info("图书记录数:"+total);
		cond.setTotal(total);
		int startLine = (cond.getPageNo()-1)*cond.getPageSize();
		cond.setStartLine(startLine);
		List<BookInfo> list = bookDao.getByPage(cond);
		List<BookCategory> bcList = bookDao.queryBookCategoryAll();
		for (BookInfo bookInfo : list) {
			for (BookCategory bc : bcList) {
				if(bc.getBookCategoryId().equals(bookInfo.getBookCategoryId())){
					bookInfo.setBookCategory_text(bc.getBookCategory());
					break;
				}
			}
			bookInfo.setBookPublish_text(sdf.format(bookInfo.getBookPublish()));
		}
		logger.info(list);
		return list;
	}
	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			//去主表中查询,如果查到记录才执行下面操作
			BookPrimary temp = new BookPrimary();
			temp.setBookBarCode(id);
			List<BookPrimary> bookp = bookDao.findBP(temp);
			if(bookp != null){
				logger.info("图书主表中存在此图书");
				BookCond cond = new BookCond();
				cond.setStartLine(0);
				cond.setPageSize(1);
				cond.setBookBarCode(id);
				BookInfo book = bookDao.getByPage(cond).get(0);
				//将信息表中的图书数量置0
				book.setBookCount(0);
				bookDao.updateBookCount(book);
				//获取主表中下架图书对应的位置
				List<String> bs = bookDao.getBookSeat(id);
				//将对应位置状态设置为0
				for(String seat:bs){
					BookSeat bookSeat = new BookSeat();
					bookSeat.setSeatStatus(0);
					bookSeat.setBookSeat(seat);
					bookDao.updateBookSeat(bookSeat);
				}
				//删除主表中信息
				BookPrimary bp = new BookPrimary();
				bp.setBookBarCode(id);
				bookDao.deleteBP(bp);
				//更新类别表,将数量减少1
				String bookCategory = book.getBookCategoryId();
				BookCategory bc = new BookCategory();
				bc.setBookCategoryId(bookCategory);
				bookDao.updateBookCategory(bc);
				logger.info("图书下架完成");
			}
		}
	}
	@Override
	public BookInfo findById(String id) {
		return bookDao.findByIdBI(id);
	}
	@Override
	public void update(BookInfo book) {
		bookDao.update(book);
	}
	@Override
	public BookInfo detail(String id) {
		BookInfo book = bookDao.findByIdBI(id);
		BookPrimary bp = new BookPrimary();
		bp.setBookBarCode(id);
		List<BookPrimary> bpList= bookDao.findBP(bp);
		book.setBookSeatList(bpList);
		book.setBookPublish_text(sdf.format(book.getBookPublish()));
		return book;
	}
	@Override
	public List<BookSeat> getSeatById(String categoryId) {
		return bookDao.getSeatById(categoryId);
	}
	@Override
	public BookInfo queryBookBySeat(String seat) {
		return bookDao.queryBookBySeat(seat);
	}

}
