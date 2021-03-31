package de.servicezombie.thymeleaf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;

/**
 * From https://www.baeldung.com/java-compress-and-uncompress
 * to put all resources of a template in a single zip file.
 */
public class ZipDirectory {
	
    public void run(File sourceDirectory, File zipFile) throws IOException {

    	File temp = File.createTempFile("report", ".zip");
    	
        FileOutputStream fos = new FileOutputStream(temp);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
 
        zipFile(sourceDirectory, sourceDirectory.getName(), zipOut);
        zipOut.close();
        fos.close();
        
        FileUtils.copyFile(temp, zipFile);
        
    }
 
    /**
     * 
     * @param fileToZip file or directory object to add to zip
     * @param fileName filename in zip file, needed for recursion
     * @param zipOut stream to save zip data into
     * @throws IOException on error
     */
    private void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        
        writeIntoZipstream(fileToZip, fileName, zipOut);
    }

	private void writeIntoZipstream(File fileToZip, String fileName, ZipOutputStream zipOut)
			throws FileNotFoundException, IOException {
		final FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
	}
}