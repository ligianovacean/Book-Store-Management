package bookStore.service.report;

import bookStore.entity.Book;

import java.io.IOException;
import java.util.List;

public interface ReportGenerator {

    void generateReport(List<Book> books) throws IOException;

}
