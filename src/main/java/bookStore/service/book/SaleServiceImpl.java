package bookStore.service.book;

import bookStore.dto.SaleDTO;
import bookStore.entity.Book;
import bookStore.error.InvalidSaleException;
import bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleService {
    BookRepository bookRepository;

    @Autowired
    public SaleServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public int sell(SaleDTO saleDTO) throws InvalidSaleException{
        Book bookToSell = bookRepository.findByTitleAndAuthorId(saleDTO.title, saleDTO.authorId);
        saleDTO.initialQuantity = bookToSell.getQuantity();
        saleDTO.price = bookToSell.getPrice();
        if (saleDTO.quantity > saleDTO.initialQuantity) {
            throw new InvalidSaleException("The sale could not be processed! There are not enough items available.");
        }
        bookToSell.setQuantity(saleDTO.initialQuantity - saleDTO.quantity);
        bookRepository.save(bookToSell);
        return computeTotal(saleDTO);
    }

    private int computeTotal(SaleDTO saleDTO) {
        return saleDTO.price*saleDTO.quantity;
    }
}
