// 代码生成时间: 2025-10-01 02:29:22
package com.example.filetool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.struts2.ServletActionContext;

public class BinaryFileReadWriteTool {

    /**
     * Writes a binary file to the specified path.
     * 
     * @param filePath The path to the file.
     * @param fileData The binary data to write.
     * @throws IOException If an I/O error occurs.
     */
    public static void writeBinaryFile(String filePath, byte[] fileData) throws IOException {
        File file = new File(filePath);
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(fileData);
        } catch (IOException e) {
            // Log exception and rethrow
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Reads a binary file from the specified path.
     * 
     * @param filePath The path to the file.
     * @return The binary data of the file.
     * @throws IOException If an I/O error occurs.
     */
    public static byte[] readBinaryFile(String filePath) throws IOException {
        File file = new File(filePath);
        try (InputStream is = new FileInputStream(file)) {
            byte[] buffer = new byte[(int) file.length()];
            int bytesRead = is.read(buffer);
            if (bytesRead != buffer.length) {
                throw new IOException("Failed to read the whole file.");
            }
            return buffer;
        } catch (IOException e) {
            // Log exception and rethrow
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Main method for testing.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            String testFilePath = "test.bin";
            byte[] testFileData = new byte[] {0x00, 0x01, 0x02, 0x03};

            // Write binary data to a file
            writeBinaryFile(testFilePath, testFileData);

            // Read binary data from a file
            byte[] readData = readBinaryFile(testFilePath);
            System.out.println("Read data: " + new String(readData));

        } catch (IOException e) {
            ServletActionContext.getRequest().setAttribute("error", e.getMessage());
            e.printStackTrace();
        }
    }
}
