package bookStore.service.report;

import bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class ReportServiceImpl implements ReportService{
    BookRepository bookRepository;
    ReportFactory reportFactory;

    @Autowired
    public ReportServiceImpl(BookRepository bookRepository, ReportFactory reportFactory) {
        this.bookRepository = bookRepository;
        this.reportFactory = reportFactory;
    }

    @Override
    public void generateReport(String reportType) throws IOException{
        reportFactory.getReport(reportType).generateReport(bookRepository.findByQuantity(0));
    }
}
