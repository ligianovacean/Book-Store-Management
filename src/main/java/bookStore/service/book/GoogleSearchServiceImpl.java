package bookStore.service.book;

import bookStore.config.ClientCredentials;
import bookStore.entity.Author;
import bookStore.entity.Book;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;


@Service
public class GoogleSearchServiceImpl implements GoogleSearchService{

    private static final String APPLICATION_NAME = "Book Store";

    private List<Book> searchBy(String query) throws IOException, GeneralSecurityException{
        ClientCredentials.errorIfNotSpecified();

        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
                .setApplicationName(APPLICATION_NAME)
                .setGoogleClientRequestInitializer(new BooksRequestInitializer(ClientCredentials.API_KEY))
                .build();
        // Set query string and filter only Google eBooks.
        System.out.println("Query: [" + query + "]");
        Books.Volumes.List volumesList = books.volumes().list(query);
        volumesList.setFilter("ebooks");

        // Execute the query.
        Volumes volumes = volumesList.execute();
        if (volumes.getTotalItems() == 0 || volumes.getItems() == null) {
            System.out.println("No matches found.");
            return new ArrayList<>();
        }

        List<Book> foundBooks = new ArrayList<>();
        for (Volume volume : volumes.getItems()){
            String title = "";
            String author = "";
            String genre = "";
            int price = 0;
            int quantity = 0;

            Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
            Volume.SaleInfo saleInfo = volume.getSaleInfo();

            title = volumeInfo.getTitle();

            java.util.List<String> authors = volumeInfo.getAuthors();
            if (authors != null && !authors.isEmpty()) {
                for (String aut : authors)
                    author = author + aut + "\n";
            }

            if (volumeInfo.getCategories() != null && !volumeInfo.getCategories().isEmpty()) {
                genre = volumeInfo.getCategories().get(0);
            }

            if (saleInfo != null && "FOR_SALE".equals(saleInfo.getSaleability())) {
                Double salePrice = saleInfo.getListPrice().getAmount();
                price = salePrice.intValue();
            }

            foundBooks.add(new Book(genre, title, price, quantity, new Author(author)));
        }

        return foundBooks;
    }

    @Override
    public List<Book> search(String query) throws IOException, GeneralSecurityException {
        List<Book> booksByTitle = searchBy("intitle:" + query + "%");
        List<Book> booksByAuthor = searchBy("inauthor:" + query);
        List<Book> booksByGenre = searchBy("subject:" + query);
        booksByAuthor.addAll(booksByTitle);
        booksByAuthor.addAll(booksByGenre);
        return booksByAuthor;
    }
}
