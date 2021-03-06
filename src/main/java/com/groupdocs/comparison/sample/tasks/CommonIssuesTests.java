package com.groupdocs.comparison.sample.tasks;

import com.groupdocs.comparison.Comparer;
import com.groupdocs.comparison.license.License;
import com.groupdocs.comparison.options.ApplyChangeOptions;
import com.groupdocs.comparison.options.CompareOptions;
import com.groupdocs.comparison.options.load.LoadOptions;
import com.groupdocs.comparison.options.style.DetalisationLevel;
import com.groupdocs.comparison.options.style.StyleSettings;
import com.groupdocs.comparison.result.ChangeInfo;
import com.groupdocs.comparison.result.ChangeType;
import com.groupdocs.comparison.result.ComparisonAction;
import com.groupdocs.comparison.sample.TestRunner;
import com.groupdocs.comparison.sample.config.TestNGSetUp;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

/**
 * @author Aleksey Permyakov
 */
@SuppressWarnings("all")
public class CommonIssuesTests extends TestNGSetUp {
    private static final Logger LOG = LoggerFactory.getLogger(CommonIssuesTests.class);

    @Test
    public void testCOMPARISONJAVA107() throws Exception {
        final String sourceName = "original.pdf", targetName = "updated.pdf", resultName = "COMPARISONJAVA107.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA107");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA107");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            comparer.compare(resultPath);
            final long size = Files.size(resultPath);
            LOG.debug("Stream size: {}", size);
            assertFalse(size == 0, "Result stream is empty");
        }
    }

    @Test
    public void testCOMPARISONJAVA226() throws Exception {
        final String sourceName = "source.docx", targetName = "target.docx", resultName = "COMPARISONJAVA226.docx";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA226");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA226");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        try (Comparer comparer = new Comparer(sourcePath);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            comparer.add(targetPath);
            comparer.compare(outputStream);
            assertTrue(outputStream.size() > 0, "The result stream is empty");
            FileUtils.writeByteArrayToFile(resultPath.toFile(), outputStream.toByteArray());
            LOG.debug("resultPath = {}", resultPath);
        }
    }

    @Test
    public void testCOMPARISONJAVA228() throws Exception {
        final String sourceName = "source.docx", targetName1 = "target_1.docx", targetName2 = "target_2.docx", targetName3 = "target_3.docx", resultName = "COMPARISONJAVA228.docx";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA228");
        final Path targetPath1 = TestRunner.getStoragePath(targetName1, "COMPARISONJAVA228");
        final Path targetPath2 = TestRunner.getStoragePath(targetName2, "COMPARISONJAVA228");
        final Path targetPath3 = TestRunner.getStoragePath(targetName3, "COMPARISONJAVA228");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath1, targetPath2, targetPath3);
            comparer.compare(resultPath);
        }
    }

    @Test
    public void testCOMPARISONJAVA252() throws Exception {
        final String sourceName = "source.pptx", targetName = "target.pptx", resultName = "COMPARISONJAVA252-output.ppt";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA252");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA252");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            comparer.compare(resultPath);
        }
    }

    /**
     * Check wheather the license is valid
     */
    @Test
    public void testCOMPARISONJAVA247() throws Exception {
//        unsetLicense();
//        assertFalse(License.isValidLicense());
//        applyLicense();
//        assertTrue(License.isValidLicense());
    }

    @Test
    public void testCOMPARISONJAVA419() throws Exception {
        {
            final String sourceName = "source-highlights.rtf", targetName = "target-highlights.rtf", resultName = "COMPARISONJAVA419-highlights.rtf";
            final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA419");
            final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA419");
            final Path resultPath = TestRunner.getOutputPath(resultName); /* Create instance of GroupDocs.Comparison.Comparer and call method */

            try (Comparer comparer = new Comparer(sourcePath)) {
                comparer.add(targetPath);
                comparer.compare(resultName, new CompareOptions.Builder().setDetectStyleChanges(true).setGenerateSummaryPage(true).build());
            }
        }
        {
            final String sourceName = "source-not-highlights.rtf", targetName = "target-not-highlights.rtf", resultName = "COMPARISONJAVA419-not-highlights.rtf";
            final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA419");
            final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA419");
            final Path resultPath = TestRunner.getOutputPath(resultName); /* Create instance of GroupDocs.Comparison.Comparer and call method */

            try (Comparer comparer = new Comparer(sourcePath)) {
                comparer.add(targetPath);
                comparer.compare(resultPath, new CompareOptions.Builder().setDetectStyleChanges(true).setGenerateSummaryPage(true).build());
            }
        }
        {
            final String sourceName = "source-3.rtf", targetName = "target-3.rtf", resultName = "COMPARISONJAVA419-3.rtf";
            final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA419");
            final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA419");
            final Path resultPath = TestRunner.getOutputPath(resultName); /* Create instance of GroupDocs.Comparison.Comparer and call method */

            try (Comparer comparer = new Comparer(sourcePath)) {
                comparer.add(targetPath);
                comparer.compare(resultPath, new CompareOptions.Builder().setDetectStyleChanges(true).setGenerateSummaryPage(true).build());
            }
        }
    }

    @Test
    public void testCOMPARISONJAVA418() throws Exception {
        final String sourceName = "Test.pdf", targetName = "Test_Copy.pdf", resultName = "COMPARISONJAVA418-output.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA418");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA418");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            comparer.compare(resultPath);
        }
    }

    @Test
    public void testCOMPARISONJAVA383() throws Exception {
        final String sourceName = "source.pdf", targetName = "target.pdf", resultName = "COMPARISONJAVA383-output.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA383");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA383");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            comparer.compare(resultPath);
        }
    }

    @Test
    public void testCOMPARISONJAVA381() throws Exception {
        final String sourceName = "source.pdf", targetName = "target.pdf", resultName = "COMPARISONJAVA381-output.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA381");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA381");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            comparer.compare(resultPath);
        }
    }

    @Test
    public void testCOMPARISONJAVA380() throws Exception {
        final String sourceName = "source.pdf", targetName = "target.pdf", resultName = "COMPARISONJAVA380-output.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA380");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA380");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            comparer.compare(resultPath);
        }
    }

    @Test
    public void testCOMPARISONJAVA377() throws Exception {
        if (Boolean.parseBoolean("true")) { // To avoid commenting sources below
            throw new SkipException("Is not fixed yet");
        }
        final String sourceName = "source.html", targetName = "target.html", resultName = "COMPARISONJAVA377-output.html";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA377");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA377");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            comparer.compare(resultName);
        }
    }

    @Test
    @Ignore("COMPARISONJAVA-1185")
    public void testCOMPARISONJAVA231() throws Exception {
        final String sourceName = "compara1.pdf", targetName = "compara2.pdf", resultName = "COMPARISONJAVA231-output.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA231");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA231");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        try (InputStream sourceStream = new FileInputStream(sourcePath.toFile());
             InputStream targetStream = new FileInputStream(targetPath.toFile());
             Comparer comparer = new Comparer(sourceStream)) {
            comparer.add(targetStream);
            comparer.compare(resultPath);
        }
    }

    @Test
    public void testCOMPARISONJAVA420() throws Exception {
        final String sourceName = "source.pdf", targetName = "target.pdf", resultName = "COMPARISONJAVA420-output.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA420");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA420");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        try (InputStream sourceStream = new FileInputStream(sourcePath.toFile());
             InputStream targetStream = new FileInputStream(targetPath.toFile());
             Comparer comparer = new Comparer(sourceStream)) {
            comparer.add(targetStream);
            comparer.compare(resultPath, new CompareOptions.Builder().setDetectStyleChanges(true).setGenerateSummaryPage(true).build());
            LOG.debug("Finished compare {} changes.", comparer.getChanges().length);
            LOG.debug("Report written to {}", resultPath);
        }
    }

    @Test
    public void testCOMPARISONJAVA421() throws Exception {
        final String sourceName = "source.pdf", targetName = "target.pdf", resultName = "COMPARISONJAVA421-output.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA421");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA421");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        try (InputStream sourceStream = new FileInputStream(sourcePath.toFile());
             InputStream targetStream = new FileInputStream(targetPath.toFile());
             Comparer comparer = new Comparer(sourceStream)) {
            comparer.add(targetStream);
            comparer.compare(resultPath);
        }
    }

    @Test
    public void testCOMPARISONJAVA425() throws Exception {
        final String sourceName = "source.pdf", targetName = "target.pdf", resultName = "COMPARISONJAVA425-output.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA425");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA425");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        try (InputStream sourceStream = new FileInputStream(sourcePath.toFile());
             InputStream targetStream = new FileInputStream(targetPath.toFile());
             Comparer comparer = new Comparer(sourceStream)) {
            comparer.add(targetStream);
            comparer.compare(new CompareOptions.Builder().setDetectStyleChanges(true).setGenerateSummaryPage(true).build());
            LOG.debug("Finished compare {} changes.", comparer.getChanges().length);
            LOG.debug("Report written to {}", resultPath);
        }
    }

    @Test
    public void testCOMPARISONJAVA426() throws Exception {
        final String sourceName = "source.pdf", targetName = "target.pdf", resultName = "COMPARISONJAVA426-output.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA426");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA426");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        try (InputStream sourceStream = new FileInputStream(sourcePath.toFile());
             InputStream targetStream = new FileInputStream(targetPath.toFile());
             Comparer comparer = new Comparer(sourceStream)) {
            comparer.add(targetStream);
            comparer.compare(new CompareOptions.Builder().setDetectStyleChanges(true).setGenerateSummaryPage(true).build());
            LOG.debug("Finished compare {} changes.", comparer.getChanges().length);
            LOG.debug("Report written to {}", resultPath);
        }
    }

    @Test
    public void testCOMPARISONJAVA430() throws Exception {
        final String sourceName = "source.pdf", targetName = "target.pdf", resultName = "COMPARISONJAVA430-output.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA430");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA430");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            comparer.compare(resultPath, new CompareOptions.Builder().setDetectStyleChanges(true).setShowDeletedContent(true).setGenerateSummaryPage(true).build());
            LOG.debug("Finished compare {} changes.", comparer.getChanges().length);
            LOG.debug("Report written to {}", resultPath);
        }
    }

    @Test
    public void testCOMPARISONJAVA431() throws Exception {
        final String sourceName = "source.pdf", targetName = "target.pdf", resultName = "COMPARISONJAVA431-output.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA431");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA431");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            comparer.compare(resultPath, new CompareOptions.Builder().setDetectStyleChanges(true).setShowDeletedContent(true).setGenerateSummaryPage(true).build());
            LOG.debug("Finished compare {} changes.", comparer.getChanges().length);
            LOG.debug("Report written to {}", resultPath);
        }
    }

    @Test(timeOut = 200000)
    public void testCOMPARISONJAVA374() throws Exception {
        final String sourceName = "source.pdf", targetName = "target.pdf", resultName = "COMPARISONJAVA374-output.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA374");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA374");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        final List<Thread> threads = new ArrayList<Thread>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int n = 0; n < 10; n++) {
            final String threadResultPath = resultPath.toString().replace(".pdf", "-thread-" + n + ".pdf");
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try (InputStream sourceStream = new FileInputStream(sourcePath.toFile());
                         InputStream targetStream = new FileInputStream(targetPath.toFile());
                         Comparer comparer = new Comparer(sourceStream)) {
                        comparer.add(targetStream);
                        comparer.compare(threadResultPath);
                    } catch (Exception e) {
                        LOG.error("Error during comparing documents in parallel threads", e);
                        fail(e.getMessage());
                    }
                    System.out.println("Thread " + Thread.currentThread().getName() + " finished");
                }
            });
        }
        executorService.shutdown();
        if (!executorService.awaitTermination(3, TimeUnit.MINUTES)) {
            Assert.fail("Not all threaded finished correctly!");
        }
    }

    @Test(timeOut = 200000)
    public void testCOMPARISONJAVA374_2() throws Exception {
        final String sourceName = "hot_frog.pdf", targetName = "hot_frog-1.pdf", resultName = "COMPARISONJAVA374_2-output.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA374_2");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA374_2");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int n = 0; n < 5; n++) {
            final String threadResultPath = resultPath.toString().replace(".pdf", "-thread-" + n + ".pdf");
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try (InputStream sourceStream = new FileInputStream(sourcePath.toFile());
                         InputStream targetStream = new FileInputStream(targetPath.toFile());
                         Comparer comparer = new Comparer(sourceStream)) {
                        comparer.add(targetStream);
                        comparer.compare(threadResultPath);
                        assertNotNull(comparer.getChanges());
                    } catch (Exception e) {
                        LOG.error("Error during comparing documents in parallel threads", e);
                        fail(e.getMessage());
                    }
                }
            });
        }
        executorService.shutdown();
        if (!executorService.awaitTermination(3, TimeUnit.MINUTES)) {
            Assert.fail("Not all threaded finished correctly!");
        }
    }

    @Test
    public void testCOMPARISONJAVA373() throws Exception {
        final String sourceName = "karta_izo_parole.docx", targetName = "karta_izo_parole1.docx", resultName = "COMPARISONJAVA373-output.docx";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA373");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA373");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        try (InputStream sourceStream = new FileInputStream(sourcePath.toFile());
             InputStream targetStream = new FileInputStream(targetPath.toFile());
             Comparer comparer = new Comparer(sourceStream, new LoadOptions("parole"))) {
            comparer.add(targetStream, new LoadOptions("parole"));
            comparer.compare(resultPath);
        }
    }

    @Test
    public void testCOMPARISONJAVA372() throws Exception {
        final String sourceName = "git_internals.pptx", targetName = "git_internals-1.pptx", resultName = "COMPARISONJAVA372-output.pptx";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA372");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA372");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        try (final Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            comparer.compare(resultPath);
            final ChangeInfo[] changes = comparer.getChanges();
            assertNotNull(changes);
            for (ChangeInfo change : changes) {
                assertNotNull(change.getPageInfo());
            }
        }
    }

    @Test
    public void testCOMPARISONJAVA379() throws Exception {
        final String sourceName = "hot_frog.pdf", targetName = "hot_frog-1.pdf", resultName = "COMPARISONJAVA379-output.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA379");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA379");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(sourcePath, targetPath);
            comparer.compare(resultName);
            final ChangeInfo[] changes = comparer.getChanges();
            assertNotNull(changes);
            LOG.debug("Changes count - {}", changes.length);
        }
    }

    @Test
    public void testCOMPARISONJAVA376() throws Exception {
        final String sourceName = "letter.txt", targetName = "letter2.txt", resultName = "COMPARISONJAVA376-output.txt";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA376");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA376");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            comparer.compare(resultPath);
            LOG.debug("Finished compare {} changes.", comparer.getChanges().length);
        }
    }

    @Test
    public void testCOMPARISONJAVA447() throws Exception {
        final String sourceName = "source.pdf", targetName = "target.pdf", resultName = "COMPARISONJAVA447-output.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA447");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA447");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            comparer.compare(resultPath, new CompareOptions.Builder().setDetectStyleChanges(true).build());
            assertNotNull(comparer.getChanges());
        }
    }

    @Test
    public void testCOMPARISONJAVA78() throws Exception {
        final String sourceName = "source50p.pdf", targetName = "target50p.pdf", resultName = "COMPARISONJAVA78-output.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA78");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA78");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        for (int n = 0; n < 10; n++) {
            final long before = new Date().getTime();
            try (InputStream sourceStream = new FileInputStream(sourcePath.toFile());
                 InputStream targetStream = new FileInputStream(targetPath.toFile());
                 Comparer comparer = new Comparer(sourceStream)) {
                comparer.add(targetStream);
                comparer.compare(resultPath);
                final long after = new Date().getTime();
                final double resultTime = (after - before) / 1000d;
                LOG.debug("Time: {} sec", resultTime);
                assertTrue(resultTime < 10);
            }
        }
    }

    @Test
    public void testCOMPARISONJAVA698() throws Exception {
        final String sourceName = "candy.pdf", targetName = "candy-1.pdf", resultName = "COMPARISONJAVA698-output.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA698");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA698");
        final Path firstResultPath = TestRunner.getOutputPath(resultName.replace("-", "-first-"));
        final Path secondResultPath = TestRunner.getOutputPath(resultName.replace("-", "-second-"));
        final ChangeInfo[] firstChanges;
        final byte[] firstComparisonData;
        try (
                Comparer comparer = new Comparer(sourcePath);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            comparer.add(targetPath);
            CompareOptions settings = new CompareOptions.Builder().setShowDeletedContent(false).setDetectStyleChanges(true).setCalculateCoordinates(true).build();
            comparer.compare(byteArrayOutputStream, settings);
            firstChanges = comparer.getChanges();
            firstComparisonData = byteArrayOutputStream.toByteArray();
        }
        final ChangeInfo[] secondChanges;
        final byte[] secondComparisonData;
        try (Comparer comparer = new Comparer(targetPath);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            comparer.add(sourcePath);
            CompareOptions settings = new CompareOptions.Builder().setShowDeletedContent(false).setDetectStyleChanges(true).setCalculateCoordinates(true).build();
            comparer.compare(byteArrayOutputStream, settings);
            secondChanges = comparer.getChanges();
            secondComparisonData = byteArrayOutputStream.toByteArray();
        }
        assertNotEquals(firstChanges.length, 0);
        assertNotEquals(secondChanges.length, 0);

        if (Boolean.parseBoolean("true")) { // Not ot comment sources below
            throw new SkipException("Is not fixed yet");
        }
        assertEquals(firstChanges.length, secondChanges.length);
        for (int i = 0; i < firstChanges.length; i++) {
            ChangeInfo change = firstChanges[i];
            LOG.debug("{} - {} - {}", change.getType(), change.getText(), change.getId());
        }
        LOG.debug("------");
        for (int i = 0; i < secondChanges.length; i++) {
            ChangeInfo change = secondChanges[i];
            LOG.debug("{} - {} - {}", change.getType(), change.getText(), change.getId());
        }
        FileUtils.writeByteArrayToFile(firstResultPath.toFile(), firstComparisonData);
        FileUtils.writeByteArrayToFile(secondResultPath.toFile(), secondComparisonData);
        LOG.debug("First result was saved as '{}'", firstResultPath);
        LOG.debug("Second result was saved as '{}'", secondResultPath);
    }

    @Test
    public void testCOMPARISONJAVA699() throws Exception {
        final String sourceName = "123.docx", targetName = "123_-_Copy.docx", resultName = "COMPARISONJAVA699-output.docx";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA699");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA699");
        final Path firstResultPath = TestRunner.getOutputPath(resultName.replace("-", "-first-"));
        final Path secondResultPath = TestRunner.getOutputPath(resultName.replace("-", "-second-"));
        final List<ChangeInfo> firstChanges;
        final byte[] firstComparisonData;
        try (Comparer comparer = new Comparer(sourcePath);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            comparer.add(targetPath);
            CompareOptions settings = new CompareOptions.Builder().setShowDeletedContent(false).setDetectStyleChanges(true).setCalculateCoordinates(true).build();
            comparer.compare(byteArrayOutputStream, settings);
            firstChanges = new ArrayList<ChangeInfo>(Arrays.asList(comparer.getChanges()));
            firstComparisonData = byteArrayOutputStream.toByteArray();
        }
        final List<ChangeInfo> secondChanges;
        final byte[] secondComparisonData;
        try (Comparer comparer = new Comparer(targetPath);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            comparer.add(sourcePath);
            CompareOptions settings = new CompareOptions.Builder().setShowDeletedContent(false).setDetectStyleChanges(true).setCalculateCoordinates(true).build();
            comparer.compare(byteArrayOutputStream, settings);
            secondChanges = new ArrayList<ChangeInfo>(Arrays.asList(comparer.getChanges()));
            secondComparisonData = byteArrayOutputStream.toByteArray();
        }
        assertNotEquals(firstChanges.size(), 0);
        assertNotEquals(secondChanges.size(), 0);
        assertEquals(firstChanges.size(), secondChanges.size());
        LOG.debug("--- --- SOURCE to TARGET --- ---");
        for (ChangeInfo change : firstChanges) {
            LOG.debug(ChangeType.getName(ChangeType.class, change.getType()) + " - " + change.getId() + " - \"" + change.getText() + "\"");
        }
        LOG.debug("--- --- TARGET to SOURCE --- ---");
        for (ChangeInfo change : secondChanges) {
            LOG.debug(ChangeType.getName(ChangeType.class, change.getType()) + " - " + change.getId() + " - \"" + change.getText() + "\"");
        }
        FileUtils.writeByteArrayToFile(firstResultPath.toFile(), firstComparisonData);
        FileUtils.writeByteArrayToFile(secondResultPath.toFile(), secondComparisonData);
        LOG.debug("First result was saved as '{}'", firstResultPath);
        LOG.debug("Second result was saved as '{}'", secondResultPath);
        LOG.debug("------------------------");
    }

    @Test
    public void testCOMPARISONJAVA459() throws Exception {
        if (Boolean.parseBoolean("true")) { // To avoid commenting sources below
            throw new SkipException("Is not fixed yet");
        }
        final String sourceName = "signature.html", targetName = "viewer.html", resultName = "COMPARISONJAVA459-output.html";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA459");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA459");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            comparer.compare(resultPath,
                    new CompareOptions.Builder()
                            .setGenerateSummaryPage(true)
                            .build());
        }
    }

    @Test
    public void testCOMPARISONJAVA375() throws Exception {
        final String sourceName = "hot_frog.pdf", targetName = "hot_frog-1.pdf", resultName = "COMPARISONJAVA375-output.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA375");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA375");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            comparer.compare(resultPath);
        }
    }

    @Test(timeOut = 600000)
    public void testBugInDotNetWithCommentsinHtmlFiles() throws Exception {
        if (Boolean.parseBoolean("true")) { // To avoid commenting sources below
            throw new SkipException("Is not fixed yet");
        }
        final String sourceName = "source-with-comments.html", targetName = "target-with-comments.html", resultName = "do_testBugInDotNetWithCommentsinHtmlFiles.html";
        final Path sourcePath = TestRunner.getStoragePath(sourceName);
        final Path targetPath = TestRunner.getStoragePath(targetName);
        final Path resultPath = TestRunner.getOutputPath(resultName);
        try (InputStream sourceStream = new FileInputStream(sourcePath.toFile());
             InputStream targetStream = new FileInputStream(targetPath.toFile());
             Comparer comparer = new Comparer(sourceStream)) {
            comparer.add(targetStream);
            comparer.compare(resultPath);
            final long size = Files.size(resultPath);
            LOG.debug("Stream size: {}", size);
            assertFalse(size == 0, "Result stream is empty");
        }
    }

    @Test
    public void testCOMPARISONJAVA774() throws Exception {
        final String sourceName = "source.docx", targetName = "target.docx", resultName = "COMPARISONJAVA774-output.docx";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA774");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA774");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            comparer.compare(resultPath);
            ChangeInfo[] changes = comparer.getChanges();
            changes[0].setType(ComparisonAction.REJECT);
            ApplyChangeOptions applyChangeOptions = new ApplyChangeOptions(changes);
            comparer.applyChanges(resultPath, applyChangeOptions);
        }
    }

    @Test
    public void testCOMPARISONJAVA764() throws Exception {
        final String sourceName = "source.pptx", targetName = "target.pptx", resultName = "COMPARISONJAVA764-output.pptx";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA764");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA764");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            comparer.compare(resultPath, new CompareOptions.Builder().setGenerateSummaryPage(true).setCalculateCoordinates(true).setDetalisationLevel(3).setDetectStyleChanges(true).setShowDeletedContent(true).setSensitivityOfComparison(100).build());
            assertNotEquals(0, comparer.getChanges().length);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
    }

    @Test
    public void testCOMPARISONJAVA867() throws Exception {
        final String sourceName = "left.doc", targetName = "right.doc", resultName = "COMPARISONJAVA867-output.doc";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA867");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA867");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        final boolean validLicense = License.isValidLicense();
        TestRunner.unsetLicense(); // it works correctly with valid license
        LOG.debug("Source file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            comparer.compare(resultPath);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        if (validLicense) {
            TestRunner.applyLicense();
        }
    }

    @Test
    public void testCOMPARISONJAVA866_pdf() throws Exception {
        final String sourceName = "sample_old{number}.pdf", targetName = "sample_new{number}.pdf", resultName = "COMPARISONJAVA866-output-{number}.pdf";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA866", "pdf");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA866", "pdf");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        {
            try (Comparer comparer = new Comparer(sourcePath.toString().replace("{number}", "1"))) {

                comparer.add(targetPath.toString().replace("{number}", "1"));

                CompareOptions compareOptions = new CompareOptions.Builder()
                        .setDeletedItemStyle(new StyleSettings.Builder()
                                .setStrikethrough(true)
                                .setHighlightColor(Color.gray)
                                .build())
                        .setGenerateSummaryPage(true)
                        .setDetalisationLevel(DetalisationLevel.High)
                        .setSensitivityOfComparison(100)
                        .setCalculateCoordinates(true)
                        .build();

                comparer.compare(resultPath.toString().replace("{number}", "1"), compareOptions);
            }
            LOG.debug("resultPath1 = {}", resultPath.toString().replace("{number}", "1"));
        }
        {
            try (Comparer comparer = new Comparer(sourcePath.toString().replace("{number}", "2"))) {

                comparer.add(targetPath.toString().replace("{number}", "2"));

                CompareOptions compareOptions = new CompareOptions.Builder()
                        .setDeletedItemStyle(new StyleSettings.Builder()
                                .setStrikethrough(true)
                                .setHighlightColor(Color.gray)
                                .build())
                        .setGenerateSummaryPage(true)
                        .setDetalisationLevel(DetalisationLevel.High)
                        .setSensitivityOfComparison(100)
                        .setCalculateCoordinates(true)
                        .build();

                comparer.compare(resultPath.toString().replace("{number}", "2"), compareOptions);
            }
            LOG.debug("resultPath2 = {}", resultPath.toString().replace("{number}", "2"));
        }
    }

    @Test
    public void testCOMPARISONJAVA866_xlsx() throws Exception {
        final String sourceName = "sample_old{number}.xlsx", targetName = "sample_new{number}.xlsx", resultName = "COMPARISONJAVA866-output-{number}.xlsx";
        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA866", "xlsx");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA866", "xlsx");
        final Path resultPath = TestRunner.getOutputPath(resultName);
        {
            try (Comparer comparer = new Comparer(sourcePath.toString().replace("{number}", "1"))) {

                comparer.add(targetPath.toString().replace("{number}", "1"));

                CompareOptions compareOptions = new CompareOptions.Builder()
                        .setDeletedItemStyle(new StyleSettings.Builder()
                                .setStrikethrough(true)
                                .setHighlightColor(Color.gray)
                                .build())
                        .setGenerateSummaryPage(true)
                        .setDetalisationLevel(DetalisationLevel.High)
                        .setSensitivityOfComparison(100)
                        .setCalculateCoordinates(true)
                        .build();

                comparer.compare(resultPath.toString().replace("{number}", "1"), compareOptions);
            }
            LOG.debug("resultPath1 = {}", resultPath.toString().replace("{number}", "1"));
        }
        {
            try (Comparer comparer = new Comparer(sourcePath.toString().replace("{number}", "2"))) {

                comparer.add(targetPath.toString().replace("{number}", "2"));

                CompareOptions compareOptions = new CompareOptions.Builder()
                        .setDeletedItemStyle(new StyleSettings.Builder()
                                .setStrikethrough(true)
                                .setHighlightColor(Color.gray)
                                .build())
                        .setGenerateSummaryPage(true)
                        .setDetalisationLevel(DetalisationLevel.High)
                        .setSensitivityOfComparison(100)
                        .setCalculateCoordinates(true)
                        .build();

                comparer.compare(resultPath.toString().replace("{number}", "2"), compareOptions);
            }
            LOG.debug("resultPath2 = {}", resultPath.toString().replace("{number}", "2"));
        }
    }

    @Test
    public void testCOMPARISONJAVA872() throws Exception {
        final String sourceName = "Home-Font-Old3.doc", targetName = "Home-Font-New3.doc", resultName = "COMPARISONJAVA872-output.doc";
        {
            final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA872");
            final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA872");
            final Path resultPath = TestRunner.getOutputPath(resultName);

            LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
            try (Comparer comparer = new Comparer(sourcePath)) {
                comparer.add(targetPath);
                comparer.compare(resultPath);
            }
            LOG.debug("Result was saved as '{}'", resultPath);
        }
        {
            final Path sourcePath = TestRunner.getStoragePath(sourceName + "x", "COMPARISONJAVA872");
            final Path targetPath = TestRunner.getStoragePath(targetName + "x", "COMPARISONJAVA872");
            final Path resultPath = TestRunner.getOutputPath(resultName + "x");

            LOG.debug("Source file: {}\nTarget file: {}", sourcePath, targetPath);
            try (Comparer comparer = new Comparer(sourcePath)) {
                comparer.add(targetPath);
                comparer.compare(resultPath);
            }
            LOG.debug("Result was saved as '{}'", resultPath);
        }
    }

    @Test
    public void testCOMPARISONJAVA869() throws Exception {
        final String sourceName = "Image-Pen-Old1.doc", targetName = "Image-Pen-New1.doc", resultName = "COMPARISONJAVA869-output.doc";
        { // DOC
            final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA869");
            final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA869");
            final Path resultPath = TestRunner.getOutputPath(resultName);

            LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
            try (Comparer comparer = new Comparer(sourcePath)) {
                comparer.add(targetPath);

                CompareOptions compareOptions = new CompareOptions();
                compareOptions.setDetectStyleChanges(true);
                compareOptions.setMarkChangedContent(true);
                comparer.compare(resultPath, compareOptions);
            }
            LOG.debug("Result was saved as '{}'", resultPath);
            // Result content CAN'T contain borders, for doc format changes are marked using different colors
        }
        { // DOCX
            final Path sourcePath = TestRunner.getStoragePath(sourceName + "x", "COMPARISONJAVA869");
            final Path targetPath = TestRunner.getStoragePath(targetName + "x", "COMPARISONJAVA869");
            final Path resultPath = TestRunner.getOutputPath(resultName + "x");

            LOG.debug("Source file: {}\nTarget file: {}", sourcePath, targetPath);
            try (Comparer comparer = new Comparer(sourcePath)) {
                comparer.add(targetPath);
                CompareOptions compareOptions = new CompareOptions();
                compareOptions.setMarkChangedContent(true);
                comparer.compare(resultPath, compareOptions);
            }
            LOG.debug("Result was saved as '{}'", resultPath);
            // Result document must contain borders around changes
        }
//        fail("Manual check required - https://issue.lisbon.dynabic.com/issues/COMPARISONJAVA-869");
    }

    @Test
    public void testCOMPARISONJAVA870() throws Exception {
        final String sourceName = "Reference-Figures-Old2.doc", targetName = "Reference-Figures-New2.doc", resultName = "COMPARISONJAVA870-output.doc";
        {
            final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA870");
            final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA870");
            final Path resultPath = TestRunner.getOutputPath(resultName);

            LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
            try (Comparer comparer = new Comparer(sourcePath)) {
                comparer.add(targetPath);

                CompareOptions compareOptions = new CompareOptions();
                compareOptions.setSensitivityOfComparison(70);
                comparer.compare(resultPath, compareOptions);
            }
            LOG.debug("Result was saved as '{}'", resultPath);
        }
        {
            final Path sourcePath = TestRunner.getStoragePath(sourceName + "x", "COMPARISONJAVA870");
            final Path targetPath = TestRunner.getStoragePath(targetName + "x", "COMPARISONJAVA870");
            final Path resultPath = TestRunner.getOutputPath(resultName + "x");

            LOG.debug("Source file: {}\nTarget file: {}", sourcePath, targetPath);
            try (Comparer comparer = new Comparer(sourcePath)) {
                comparer.add(targetPath);

                CompareOptions compareOptions = new CompareOptions();
                compareOptions.setSensitivityOfComparison(70);
                comparer.compare(resultPath, compareOptions);
            }
            LOG.debug("Result was saved as '{}'", resultPath);
        }
//        fail("Manual check required - https://issue.lisbon.dynabic.com/issues/COMPARISONJAVA-870");
        // Order of chars must be the same as in source/target files
    }

    @Test
    public void testCOMPARISONJAVA873() throws Exception {
        final String sourceName = "Layout-Arrange-Old3.doc", targetName = "Layout-Arrange-New3.doc", resultName = "COMPARISONJAVA873-output.xls";

        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA873");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA873");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            // Must not throw NoClassDefFoundError ..ArchiveStreamFactory
            comparer.compare(resultPath);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
//        fail("Manual check required - https://issue.lisbon.dynabic.com/issues/COMPARISONJAVA-873");
    }

    @Test
    public void testCOMPARISONJAVA874() throws Exception {
        final String sourceName = "Reference-Annotation-Old4.docx", targetName = "Reference-Annotation-New4.docx", resultName = "COMPARISONJAVA874-output.xls";

        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA874");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA874");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            // Must not throw NoClassDefFoundError ..ArchiveStreamFactory
            comparer.compare(resultPath);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
//        fail("Manual check required - https://issue.lisbon.dynabic.com/issues/COMPARISONJAVA-874");
    }

    @Test
    public void testCOMPARISONJAVA875() throws Exception {
        {
            final String sourceName = "Home-Font-Old2.xls", targetName = "Home-Font-New15.xls", resultName = "COMPARISONJAVA875-output.doc";
            final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA875");
            final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA875");
            final Path resultPath = TestRunner.getOutputPath(resultName);

            LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
            try (Comparer comparer = new Comparer(sourcePath)) {
                comparer.add(targetPath);
                comparer.compare(resultPath);
            }
            LOG.debug("Result was saved as '{}'", resultPath);
        }
        {
            final String sourceName = "Home-Font-Old4 - fillcolor.xls", targetName = "Home-Font-New17 - fillcolor.xls", resultName = "COMPARISONJAVA875-output-filled.doc";
            final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA875");
            final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA875");
            final Path resultPath = TestRunner.getOutputPath(resultName);

            LOG.debug("Source file: {}\nTarget file: {}", sourcePath, targetPath);
            try (Comparer comparer = new Comparer(sourcePath)) {
                comparer.add(targetPath);
                comparer.compare(resultPath);
            }
            LOG.debug("Result was saved as '{}'", resultPath);
        }
//        fail("Manual check required - https://issue.lisbon.dynabic.com/issues/COMPARISONJAVA-875");
    }

    @Test
    public void testCOMPARISONJAVA876() throws Exception {
        final String sourceName = "Home-Style_old6.xls", targetName = "Home-Style_new6.xls", resultName = "COMPARISONJAVA876-output.xls";

        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA876");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA876");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            // Must not throw NoClassDefFoundError ..ArchiveStreamFactory
            comparer.compare(resultPath);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // must not throw exception
    }

    @Test
    public void testCOMPARISONJAVA878() throws Exception {
        final String sourceName = "Layout-Arrange_old2.xls", targetName = "Layout-Arrange_new2.xls", resultName = "COMPARISONJAVA878-output.xlsx";

        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA878");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA878");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            // Must not throw NoClassDefFoundError ..ArchiveStreamFactory
            comparer.compare(resultPath);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // must not throw exception
    }

    @Test
    public void testCOMPARISONJAVA879() throws Exception {
        final String sourceName = "Layout-Arrange_old10.xls", targetName = "Layout-Arrange_new10.xls", resultName = "COMPARISONJAVA879-output.xlsx";

        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA879");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA879");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            comparer.compare(resultPath);
            fail("The bus was fixed");
        } catch (Exception e) {
            System.err.println("Known exception: " + e.getMessage());
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // must not throw exception
    }

    @Test
    public void testCOMPARISONJAVA880() throws Exception {
        final String sourceName = "Review-Comment_old.xls", targetName = "Review-Comment_new.xls", resultName = "COMPARISONJAVA880-output.xlsx";

        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA880");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA880");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            comparer.compare(resultPath);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // must show correct results
    }

    @Test
    public void testCOMPARISONJAVA881() throws Exception {
        final String issueDirectory = "COMPARISONJAVA881";
        final String sourceName = "DiffHomeParagraph_old1.pptx", targetName = "DiffHomeParagraph_new1.pptx", resultName = issueDirectory + "-output.pptx";

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            comparer.compare(resultPath);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        //
    }

    @Test
    public void testCOMPARISONJAVA882() throws Exception {
        final String issueDirectory = "COMPARISONJAVA882";
        final String sourceName = "source.pptx", targetName = "target.pptx", resultName = issueDirectory + "-output.pptx";

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();
            compareOptions.setDetalisationLevel(DetalisationLevel.High);

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // Comments must be compared
    }

    @Test
    public void testCOMPARISONJAVA893() throws Exception {
        final String issueDirectory = "COMPARISONJAVA893";
        final String sourceName = "sourceFile.docx", targetName = "targetFile.docx",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();
//            compareOptions.setDetalisationLevel(DetalisationLevel.High);

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // There are 3 inserted items in summary page, that is not correct
    }

    @Test
    public void testCOMPARISONJAVA896() throws Exception {
        final String sourceName = "Review-Protect_old.xls", targetName = "Review-Protect_new.xls", resultName = "COMPARISONJAVA896-output.xls";

        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA896");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA896");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            // Must not throw NoClassDefFoundError ..ArchiveStreamFactory
            comparer.compare(resultPath);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
    }

    @Test
    public void testCOMPARISONJAVA913() throws Exception {
        final String issueDirectory = "COMPARISONJAVA913";
        final String sourceName = "Home-Font-Italic_old1.pdf", targetName = "Home-Font-Italic_new1.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();
//            compareOptions.setDetalisationLevel(DetalisationLevel.High);

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // Source text styles are missed in result file
    }

    @Test
    public void testCOMPARISONJAVA914() throws Exception {
        final String issueDirectory = "COMPARISONJAVA914";
        final String sourceName = "Home-Font-Bold_old1.pdf", targetName = "Home-Font-Bold_new1.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // Some chars were missed
    }

    @Test
    public void testCOMPARISONJAVA915() throws Exception {
        final String issueDirectory = "COMPARISONJAVA915";
        final String sourceName = "Home-Font-Cricle2_old1.pdf", targetName = "Home-Font-Cricle2_new3.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // result file is not as expected
    }

    @Test
    public void testCOMPARISONJAVA916() throws Exception {
        final String issueDirectory = "COMPARISONJAVA916";
        final String sourceName = "Home-Font-Ruby_old1.pdf", targetName = "Home-Font-Ruby_new1.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // result is not as expected
    }

    @Test
    public void testCOMPARISONJAVA917() throws Exception {
        final String issueDirectory = "COMPARISONJAVA917";
        final String sourceName = "Home-Paragraph-Comb_old1.pdf", targetName = "Home-Paragraph-Comb_new2.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // text position in result document is not correct
    }

    @Test
    public void testCOMPARISONJAVA918() throws Exception {
        final String issueDirectory = "COMPARISONJAVA918";
        final String sourceName = "Home-Paragraph-Vert4_old1.pdf", targetName = "Home-Paragraph-Vert4_new1.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // text position in result document is not correct
    }

    @Test
    public void testCOMPARISONJAVA919() throws Exception {
        final String issueDirectory = "COMPARISONJAVA919";
        final String sourceName = "Home-Paragraph-Sort_old2.pdf", targetName = "Home-Paragraph-Sort_new4.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // rotation is lost in result document
    }

    @Test
    public void testCOMPARISONJAVA920() throws Exception {
        final String issueDirectory = "COMPARISONJAVA920";
        final String sourceName = "Home-Paragraph-Equals_old1.pdf", targetName = "Home-Paragraph-Equals_new1.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // position change is not marked in result
    }

    @Test
    public void testCOMPARISONJAVA928() throws Exception {
        final String issueDirectory = "COMPARISONJAVA928";
        final String sourceName = "Insert-Text-TextBox_old.pdf", targetName = "Insert-Text-TextBox_new.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // rectangle is lost in result file
    }

    @Test
    public void testCOMPARISONJAVA930() throws Exception {
        final String issueDirectory = "COMPARISONJAVA930";
        final String sourceName = "Insert-Mark-Formula_old.pdf", targetName = "Insert-Mark-Formula_new.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // comparison process brokes the formula in result document
    }

    @Test
    public void testCOMPARISONJAVA931() throws Exception {
        final String issueDirectory = "COMPARISONJAVA931";
        final String sourceName = "Design-Background-Sukashi_old.pdf", targetName = "Design-Background-Sukashi_new.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // warermark is lost in result file
    }

    @Test
    public void testCOMPARISONJAVA932() throws Exception {
        final String issueDirectory = "COMPARISONJAVA932";
        final String sourceName = "Layout-PageSetup-Para3_old.pdf", targetName = "Layout-PageSetup-Para3_new.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // columns layout is broken
    }

    @Test
    public void testCOMPARISONJAVA933() throws Exception {
        final String issueDirectory = "COMPARISONJAVA933";
        final String sourceName = "Layout-PageSetup-Newline_old.pdf", targetName = "Layout-PageSetup-Newline_new.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // symbol is lost in result document
    }

    @Test
    public void testCOMPARISONJAVA934() throws Exception {
        final String issueDirectory = "COMPARISONJAVA934";
        final String sourceName = "Layout-PageSetup-RowNum_old.pdf", targetName = "Layout-PageSetup-RowNum_new.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // text is transparant in result file
    }

    @Test
    public void testCOMPARISONJAVA935() throws Exception {
        final String issueDirectory = "COMPARISONJAVA935";
        final String sourceName = "Reference-Annotation_old.pdf", targetName = "Reference-Annotation_new.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // number was moved down
    }

    @Test
    public void testCOMPARISONJAVA936() throws Exception {
        final String issueDirectory = "COMPARISONJAVA936";
        final String sourceName = "Home-Font-Old7.xls", targetName = "Home-Font-New20.xls",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        //
    }

    @Test
    public void testCOMPARISONJAVA937() throws Exception {
        final String issueDirectory = "COMPARISONJAVA937";
        final String sourceName = "Insert-Graph-New4-01.xls", targetName = "Insert-Graph-New4-08.xls",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // must not fail
    }

    @Test
    public void testCOMPARISONJAVA938() throws Exception {
        final String issueDirectory = "COMPARISONJAVA938";
        final String sourceName = "DiffHomeImage_old3.pptx", targetName = "DiffHomeImage_new3.pptx",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // changes in figures in rectangle are not detected (2nd page)
    }

    @Test
    public void testCOMPARISONJAVA939() throws Exception {
        final String issueDirectory = "COMPARISONJAVA939";
        final String sourceName = "DiffInsertTable_old2.pptx", targetName = "DiffInsertTable_new2.pptx",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        //
    }

    @Test
    public void testCOMPARISONJAVA940() throws Exception {
        final String issueDirectory = "COMPARISONJAVA940";
        final String sourceName = "DiffInsertTable_old2 (1).pptx", targetName = "DiffInsertTable_new2.pptx",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        //
    }

    @Test
    public void testCOMPARISONJAVA941() throws Exception {
        final String issueDirectory = "COMPARISONJAVA941";
        final String sourceName = "Home-Paragraph-Vert-Up-old1.pdf", targetName = "Home-Paragraph-Vert-Up-new1.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // alignment is incorrect
    }

    @Test
    public void testCOMPARISONJAVA942() throws Exception {
        final String issueDirectory = "COMPARISONJAVA942";
        final String sourceName = "Insert-Table-Title_old1.pdf", targetName = "Insert-Table-Title_new2.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // incorrect table layout
    }

    @Test
    public void testCOMPARISONJAVA943() throws Exception {
        final String issueDirectory = "COMPARISONJAVA943";
        final String sourceName = "Insert-Table-Name_old1.pdf", targetName = "Insert-Table-Name_new1.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // wrong result
    }

    @Test
    public void testCOMPARISONJAVA944() throws Exception {
        final String issueDirectory = "COMPARISONJAVA944";
        final String sourceName = "Layout-Paper-Landscape-Old1.pdf", targetName = "Layout-Paper-Landscape-New1.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        //
    }

    @Test
    public void testCOMPARISONJAVA945() throws Exception {
        final String issueDirectory = "COMPARISONJAVA945";
        final String sourceName = "Reference-Figures-Table-old1.pdf", targetName = "Reference-Figures-Table-new1.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // Some words are lost in  result
    }

    @Test
    public void testCOMPARISONJAVA946() throws Exception {
        final String issueDirectory = "COMPARISONJAVA946";
        final String sourceName = "Reference-Index_old.pdf", targetName = "Reference-Index_new.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        //
    }

    @Test
    public void testCOMPARISONJAVA975() throws Exception {
        final String issueDirectory = "COMPARISONJAVA975";
        final String sourceName = "Layout-Arrange_old9.xls", targetName = "Layout-Arrange_new9.xls",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();
            compareOptions.setDetectStyleChanges(true);

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        //
    }

    @Test
    public void testCOMPARISONJAVA976() throws Exception {
        final String sourceName = "Insert-Text-xlsx_old.pdf", targetName = "Insert-Text-xlsx_new.pdf", resultName = "COMPARISONJAVA976-output.pdf";

        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA976");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA976");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            comparer.compare(resultPath);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
    }

    @Test
    public void testCOMPARISONJAVA977() throws Exception {
        final String issueDirectory = "COMPARISONJAVA977";
        final String sourceName = "source.doc", targetName = "target.doc",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();

            comparer.compare(resultPath, compareOptions);

            final ChangeInfo[] changes = comparer.getChanges();
            for (ChangeInfo changeInfo : changes) {
                System.out.println(changeInfo.getSourceText());
                System.out.println(changeInfo.getTargetText());
            }
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        //
    }

    @Test
    public void testCOMPARISONJAVA978() throws Exception {
        final String sourceName = "Insert-Image_old.xlsx", targetName = "Insert-Image_new.xlsx", resultName = "COMPARISONJAVA978-output.xlsx";

        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA978");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA978");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();
            compareOptions.setDetectStyleChanges(true);

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // images changes must be detected
    }

    @Test
    public void testCOMPARISONJAVA979() throws Exception {
        final String issueDirectory = "COMPARISONJAVA979";
        final String sourceName = "Reference-Table-Old1.pdf", targetName = "Reference-Table-New1.pdf",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();
            compareOptions.setDetectStyleChanges(true);

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // Must not cut any text
    }

    @Test
    public void testCOMPARISONJAVA980() throws Exception {
        final String issueDirectory = "COMPARISONJAVA980";
        final String sourceName = "v1.docx", targetName = "v2.docx",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            StyleSettings deletedStyleSettings = new StyleSettings();
            deletedStyleSettings.setStrikethrough(true);
            deletedStyleSettings.setFontColor(Color.red);

            CompareOptions compareOptions = new CompareOptions();
            compareOptions.setDeletedItemStyle(deletedStyleSettings);
            compareOptions.setSensitivityOfComparison(75);
            compareOptions.setDetectStyleChanges(true);
            compareOptions.setMarkChangedContent(true);
            compareOptions.setCalculateCoordinates(true);

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        //
    }

    @Test
    public void testCOMPARISONJAVA981() throws Exception {
        final String issueDirectory = "COMPARISONJAVA981";
        final String sourceName = "Format-Accessibility_Old.xls", targetName = "Format-Accessibility_New.xls",
                resultName = issueDirectory + "-output" + sourceName.substring(sourceName.lastIndexOf('.'));

        final Path sourcePath = TestRunner.getStoragePath(sourceName, issueDirectory);
        final Path targetPath = TestRunner.getStoragePath(targetName, issueDirectory);
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);

            CompareOptions compareOptions = new CompareOptions();
            compareOptions.setDetectStyleChanges(true);

            comparer.compare(resultPath, compareOptions);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
        // Must not cut any text
    }

    @Test
    public void testCOMPARISONJAVA1010() throws Exception {
        final String sourceName = "source.html", targetName = "target.html", resultName = "COMPARISONJAVA1010-output.html";

        final Path sourcePath = TestRunner.getStoragePath(sourceName, "COMPARISONJAVA1010");
        final Path targetPath = TestRunner.getStoragePath(targetName, "COMPARISONJAVA1010");
        final Path resultPath = TestRunner.getOutputPath(resultName);

        LOG.debug("\nSource file: {}\nTarget file: {}", sourcePath, targetPath);
        try (Comparer comparer = new Comparer(sourcePath)) {
            comparer.add(targetPath);
            // Must not throw NoClassDefFoundError ..IOUtils
            comparer.compare(resultPath);
        }
        LOG.debug("Result was saved as '{}'", resultPath);
    }
}