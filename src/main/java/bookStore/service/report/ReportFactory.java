package bookStore.service.report;

import org.springframework.stereotype.Component;

@Component
public class ReportFactory {
    public static ReportGenerator getReport(String reportType) {
        if (reportType.equals("\"CSV\"")) {
            return new CSVReport();
        }
        if (reportType.equals("\"PDF\"")){
            return new PDFReport();
        }
        return null;
    }

}
