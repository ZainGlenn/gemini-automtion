package com.gemini.helpers;

import com.gemini.exception.GeminiTestRuntimeException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.fail;

public class PDFHelper {
    private final String pdfContents;
    private final String path;

    public PDFHelper(String path) {
        this.path = path;
        File pdfFile = new File(path);
        if (!pdfFile.exists()) {
            throw new GeminiTestRuntimeException("Failed to locate pdf with path " + path, new Throwable());
        }
        //Loading an existing document
        try {
            //load existing
            PDDocument document = PDDocument.load(pdfFile);
            pdfContents = createContents(document);
        } catch (IOException e) {
            throw new GeminiTestRuntimeException("Failed to locate pdf with path " + path, new Throwable(e));
        }
    }

    public String getPdfContents() {
        return pdfContents;
    }

    public boolean validateContentContains(String contentToLookFor) {
        return pdfContents.contains(contentToLookFor);
    }

    private String createContents(PDDocument document) {
        try {
            //Instantiate PDFTextStripper class
            PDFTextStripper pdfStripper = new PDFTextStripper();
            //Retrieving text from PDF document
            String text = pdfStripper.getText(document);
            //Closing the document
            document.close();
            return text;
        } catch (IOException e) {
            throw new GeminiTestRuntimeException("Failed to load pdf contents", new Throwable());
        }
    }

    public static boolean waitTillFileIsPresent(String folderPath, File folder) throws InterruptedException {
        boolean isPresent = Objects.isNull(folderPath);
        int count = 0;
        while (isPresent) {
            if(count > 15) {
                return false;
            }
            Thread.sleep(1000);
            folderPath = listFilesForFolder(folder);
            isPresent = Objects.isNull(folderPath);
            count++;

        }
        return true;
    }

    public static String listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            return fileEntry.getAbsolutePath();
        }
        return null;
    }

}
