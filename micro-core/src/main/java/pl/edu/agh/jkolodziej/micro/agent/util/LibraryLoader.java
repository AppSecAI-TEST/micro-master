package pl.edu.agh.jkolodziej.micro.agent.util;

import com.google.common.io.Files;
import pl.edu.agh.jkolodziej.micro.agent.helpers.TesseractHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility to load native Tesseract libraries
 *
 * @author - Jakub Kołodziej
 */
public class LibraryLoader {
    private static final String TESSERACT_RESOURCE_DIRECTORY = "tessdata";

    private static final String TESSERACT_RESOURCE_FILE = TESSERACT_RESOURCE_DIRECTORY + "/pol.traineddata";

    private static final String LEPTONICA_LIBRARY = "liblept.so.4";
    private static final String TESSERACT_LIBRARY = "libtesseract.so";

    private static final Class<LibraryLoader> clazz = LibraryLoader.class;

    /**
     * Loading Tesseract library from resources or from exact files
     * defines in such path when <code>TesseractHelper.libPath</code> is setted
     *
     * @throws IOException
     */
    public static void loadTesseract() throws IOException {
        if ("".equals(TesseractHelper.libPath)) {
            File tessdataDirectory = new File("/tmp/" + TESSERACT_RESOURCE_DIRECTORY);
            File tessdataFile = new File("/tmp/" + TESSERACT_RESOURCE_FILE);
            File leptopnicaFile = new File("/tmp/" + LEPTONICA_LIBRARY);
            File tesseractFile = new File("/tmp/" + TESSERACT_LIBRARY);


            if (!tessdataDirectory.exists()) {
                tessdataDirectory.mkdir();
            }

            if (!tessdataFile.exists()) {
                InputStream tessdataDictionaryStream = clazz.getClassLoader().getResourceAsStream(TESSERACT_RESOURCE_FILE);
                copy(tessdataDictionaryStream, tessdataFile.getAbsolutePath());
            }

            if (!leptopnicaFile.exists()) {
                InputStream leptonicaStream = clazz.getClassLoader().getResourceAsStream(LEPTONICA_LIBRARY);
                copy(leptonicaStream, leptopnicaFile.getAbsolutePath());
            }

            if (!tesseractFile.exists()) {
                InputStream tesseractStream = clazz.getClassLoader().getResourceAsStream(TESSERACT_LIBRARY);
                copy(tesseractStream, tesseractFile.getAbsolutePath());
            }
            System.load(leptopnicaFile.getAbsolutePath());
            System.load(tesseractFile.getAbsolutePath());
        } else {
            System.out.println("leptonica path: -> " + TesseractHelper.libPath + File.separator + LEPTONICA_LIBRARY);
            System.out.println("tess path: -> " + TesseractHelper.libPath + File.separator + TESSERACT_LIBRARY);
            System.load(TesseractHelper.libPath + File.separator + LEPTONICA_LIBRARY);
            System.load(TesseractHelper.libPath + File.separator + TESSERACT_LIBRARY);
        }

    }

    /**
     * Copy files from resources to the exact files on disk
     *
     * @param inputStream input stream created from resources
     * @param pathName    path to the file which should be created
     */
    public static void copy(InputStream inputStream, String pathName) {
        try {
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            File file = new File(pathName);
            Files.write(buffer, file);
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.INFO, "IOException", e);
        }
    }
}

