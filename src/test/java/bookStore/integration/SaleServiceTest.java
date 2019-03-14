package bookStore.integration;

import bookStore.Application;
import bookStore.dto.BookDto;
import bookStore.dto.SaleDTO;
import bookStore.entity.Book;
import bookStore.error.InvalidSaleException;
import bookStore.service.book.BookService;
import bookStore.service.book.SaleService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertFalse;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
@SpringBootApplication
public class SaleServiceTest {

    @Autowired
    SaleService saleService;
    @Autowired
    BookService bookService;

    private int defaultBookId = 0;

    @Before
    public void setup(){
        BookDto bookDto = new BookDto();
        bookDto.authorId = 1;
        bookDto.title = "Dragostea in vremea holerei";
        bookDto.price = 45;
        bookDto.quantity = 10;
        bookDto.genre = "Drama";
        Book book = bookService.create(bookDto);
        defaultBookId = book.getId();
    }

    @After
    public void finalize(){
        bookService.delete(defaultBookId);
    }

    @Test
    public void validSale(){
        try {
            SaleDTO saleDTO = new SaleDTO();
            saleDTO.title = "Dragostea in vremea holerei";
            saleDTO.authorId = 1;
            saleDTO.quantity = 5;
            Assert.assertEquals(saleService.sell(saleDTO), 225);
        } catch (InvalidSaleException exc) {
            Assert.assertTrue(1 == 2);
        }
    }

    @Test
    public void invalidSale(){
        try {
            SaleDTO saleDTO = new SaleDTO();
            saleDTO.title = "Dragostea in vremea holerei";
            saleDTO.authorId = 1;
            saleDTO.quantity = 20;
            Assert.assertEquals(saleService.sell(saleDTO), -1);
        } catch(InvalidSaleException exc) {
            Assert.assertTrue(1==1);
        }
    }

}
