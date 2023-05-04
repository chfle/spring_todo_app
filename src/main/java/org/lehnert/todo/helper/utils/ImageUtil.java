package org.lehnert.todo.helper.utils;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * The ImageUtil class provides utility methods to compress and decompress image data using the Deflater and Inflater classes
 */
public class ImageUtil {

    /**
     * Compresses the given image data using the Deflater class
     *
     * @param data byte array representing the image data to be compressed
     * @return a compressed byte array of the input data
     */
    public static byte[] compressImage(byte[] data) {

        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignored) {}
        return outputStream.toByteArray();
    }

    /**
     * Decompresses the given compressed image data using the Inflater class
     *
     * @param data byte array representing the compressed image data to be decompressed
     * @return a decompressed byte array of the input data
    */
    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {}
        return outputStream.toByteArray();
    }
}