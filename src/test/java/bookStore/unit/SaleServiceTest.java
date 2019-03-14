package bookStore.unit;

import bookStore.dto.SaleDTO;
import bookStore.entity.Author;
import bookStore.entity.Book;
import bookStore.error.InvalidSaleException;
import bookStore.repository.BookRepository;
import bookStore.service.book.SaleService;
import bookStore.service.book.SaleServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SaleServiceTest {

    SaleService saleService;
    @Mock
    BookRepository bookRepository;

    private static final String TITLE = "Crima si pedeapsa";
    private static final int AUTHOR_ID = 1;
    private static final int PRICE = 10;


    @Before
    public void setup(){
        saleService = new SaleServiceImpl(bookRepository);
        Author author = new Author("F. Dostoievski");
        Book book = new Book("Drama", TITLE, PRICE, 15, author);
        when(bookRepository.findByTitleAndAuthorId(TITLE, AUTHOR_ID)).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
    }

    @Test
    public void validSale(){
        try {
            SaleDTO saleDTO = new SaleDTO();
            saleDTO.quantity = 10;
            saleDTO.authorId = AUTHOR_ID;
            saleDTO.title = TITLE;
            Assert.assertEquals(saleService.sell(saleDTO), 100);
        } catch (InvalidSaleException exc) {
            Assert.assertTrue(1==2);
        }
    }

    @Test
    public void invalidSale(){
        try {
            SaleDTO saleDTO = new SaleDTO();
            saleDTO.quantity = 20;
            saleDTO.authorId = AUTHOR_ID;
            saleDTO.title = TITLE;
            Assert.assertEquals(saleService.sell(saleDTO), -1);
        } catch (InvalidSaleException exc) {
            Assert.assertTrue(1==1);
        }
    }

}
