package bookStore.service.report;

import java.io.IOException;

public interface ReportService {

    void generateReport(String reportType) throws IOException;

}
