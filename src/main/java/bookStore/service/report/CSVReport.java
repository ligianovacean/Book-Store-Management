package bookStore.service.report;

import bookStore.entity.Book;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVReport implements ReportGenerator{

    public void generateReport(List<Book> booksOutOfStock) throws IOException{
        writeCsvFile((new File("src/report.csv")).getAbsolutePath(), booksOutOfStock);
    }

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    private static final String FILE_HEADER = "id;title;author;genre;price;quantity";

    private void writeCsvFile(String fileName, List<Book> books) throws IOException{
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER.toString());
            fileWriter.append(NEW_LINE_SEPARATOR);

            for (Book book : books) {
                fileWriter.append(book.toString());
                fileWriter.append(NEW_LINE_SEPARATOR);
            }


        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
        } finally {
                fileWriter.flush();
                fileWriter.close();

        }
    }

}
