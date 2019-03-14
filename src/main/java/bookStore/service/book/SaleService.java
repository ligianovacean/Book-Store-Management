package bookStore.service.book;

import bookStore.dto.SaleDTO;
import bookStore.error.InvalidSaleException;

public interface SaleService {

    int sell(SaleDTO saleDTO) throws InvalidSaleException;

}
