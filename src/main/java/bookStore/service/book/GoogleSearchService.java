package bookStore.service.book;

import bookStore.entity.Book;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface GoogleSearchService {

    List<Book> search(String query) throws IOException, GeneralSecurityException;

}
