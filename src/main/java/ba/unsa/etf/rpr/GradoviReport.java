package ba.unsa.etf.rpr;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public class GradoviReport extends JFrame{

    public void saveAs(Connection conn, String format) throws JRException, FileNotFoundException {
        File file = new File(format);
        String reportSrcFile = getClass().getResource("/reports/gradovi.jrxml").getFile();
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);

        if (format.contains(".pdf")) {
            JasperExportManager.exportReportToPdfStream(print, new FileOutputStream(file));
        }
        else if (format.contains(".docx")) {
            JRDocxExporter export = new JRDocxExporter();
            export.setExporterInput(new SimpleExporterInput(print));
            export.setExporterOutput(new SimpleOutputStreamExporterOutput(file));
            SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();
            export.setConfiguration(config);
            export.exportReport();
        }
        else if (format.contains(".xlsx")) {
            JRXlsxExporter export = new JRXlsxExporter();
            export.setExporterInput(new SimpleExporterInput(print));
            export.setExporterOutput(new SimpleOutputStreamExporterOutput(file));
            SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
            config.setOnePagePerSheet(true);
            export.setConfiguration(config);
            export.exportReport();
        }
    }

    public void showReport(Connection conn) throws JRException {
        String reportSrcFile = getClass().getResource("/reports/gradovi.jrxml").getFile();
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 500);
        this.setVisible(true);
    }

    public void showReport1(Connection conn, String nazivDrzave) throws JRException {
        String reportSrcFile = getClass().getResource("/reports/gradovi1.jrxml").getFile();
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        String pa = nazivDrzave;
        parameters.put("pa", pa);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 500);
        this.setVisible(true);
    }
}
