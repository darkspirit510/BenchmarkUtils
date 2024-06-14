package org.owasp.benchmarkutils.score.report.html;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.owasp.benchmarkutils.helpers.ResourceProvider;
import org.owasp.benchmarkutils.score.Configuration;
import org.owasp.benchmarkutils.score.domain.TestSuiteName;

public class CommercialAveragesPage {

    private final CommercialAveragesTable commercialAveragesTable;
    private final ResourceProvider resourceProvider;
    private final String testSuiteVersion;
    private final Configuration config;
    private final TestSuiteName testSuiteName;
    private final String projectLinkEntry;
    private final String precisionKeyEntry;
    private final String fsCoreEntry;

    public CommercialAveragesPage(
            CommercialAveragesTable commercialAveragesTable,
            ResourceProvider resourceProvider,
            String testSuiteVersion,
            Configuration config,
            TestSuiteName testSuiteName,
            String projectLinkEntry,
            String precisionKeyEntry,
            String fsCoreEntry) {
        this.commercialAveragesTable = commercialAveragesTable;
        this.resourceProvider = resourceProvider;
        this.testSuiteVersion = testSuiteVersion;
        this.config = config;
        this.testSuiteName = testSuiteName;
        this.projectLinkEntry = projectLinkEntry;
        this.precisionKeyEntry = precisionKeyEntry;
        this.fsCoreEntry = fsCoreEntry;
    }

    public void writeTo(File scoreCardDir) {
        try {
            Path htmlfile =
                    Paths.get(
                            scoreCardDir.getAbsolutePath()
                                    + File.separator
                                    + commercialAveragesTable.filename());

            String html =
                    resourceProvider.loadAsString(scoreCardDir + "/commercialAveTemplate.html");

            html = html.replace("${testsuite}", testSuiteName.fullName());
            html = html.replace("${version}", testSuiteVersion);
            html = html.replace("${projectlink}", projectLinkEntry);

            html = html.replace("${table}", commercialAveragesTable.render());
            html = html.replace("${tprlabel}", config.tprLabel);
            html = html.replace("${precisionkey}", precisionKeyEntry + fsCoreEntry);

            Files.write(htmlfile, html.getBytes());
            System.out.println("Commercial average scorecard computed.");
        } catch (IOException e) {
            System.out.println("Error generating commercial scorecard: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
