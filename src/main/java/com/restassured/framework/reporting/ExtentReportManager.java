package com.restassured.framework.reporting;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * Manager class for Extent Reports
 */
public class ExtentReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private ExtentReportManager() {
        // Private constructor to prevent instantiation
    }

    public static synchronized void initReports() {
        if (extent == null) {
            // Create reports directory if it doesn't exist
            File reportsDir = new File("reports");
            if (!reportsDir.exists()) {
                reportsDir.mkdirs();
            }

            // Generate report file name with timestamp
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String reportFileName = "API_Test_Report_" + timestamp + ".html";
            String reportFilePath = "reports/" + reportFileName;

            // Configure the report
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFilePath);
            sparkReporter.config().setDocumentTitle("API Automation Test Report");
            sparkReporter.config().setReportName("REST Assured API Test Results");
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setEncoding("utf-8");

            // Create and configure ExtentReports
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Environment", System.getProperty("env", "dev"));
        }
    }

    public static synchronized void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static synchronized ExtentTest getTest() {
        return test.get();
    }

    public static synchronized void createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    public static synchronized void logPass(String message) {
        getTest().pass(message);
    }

    public static synchronized void logFail(String message) {
        getTest().fail(message);
    }

    public static synchronized void logSkip(String message) {
        getTest().skip(message);
    }

    public static synchronized void logInfo(String message) {
        getTest().info(message);
    }
}
