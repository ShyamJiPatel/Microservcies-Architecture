package com.shyam.commonlib.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import com.shyam.commonlib.AppProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileStorageUtil {

	private final static Path ROOT_DIR = Paths.get(AppProperties.FILE_UPLOAD_PATH);

	public static void createUploadFileDirIfNotExist() {

		log.info("Upload file directory to create: {}", ROOT_DIR);

		try {
			boolean dirExist = Files.exists(ROOT_DIR);

			if (dirExist) {
				log.info("Upload file directory already exist.");
			} else {
				Files.createDirectory(ROOT_DIR);
				log.info("Upload file directory has been created: {}", ROOT_DIR);
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	public static boolean save(MultipartFile file, String createdName) {
		try {
			createUploadFileDirIfNotExist();

			long bytesWritten = Files.copy(file.getInputStream(), ROOT_DIR.resolve(createdName));

			if (bytesWritten > 0) {
				return true;
			}
		} catch (FileAlreadyExistsException e) {
			throw new RuntimeException("File already exist with name: " + createdName);
		} catch (IOException e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
		return false;
	}

	public static Resource load(String filename) {
		try {
			Path file = ROOT_DIR.resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	public static boolean delete(String filename) {
		Path file = ROOT_DIR.resolve(filename);
		return file.toFile().delete();
	}

	public static String getExtension(String uri) {
		if (uri == null) {
			return null;
		}

		int dot = uri.lastIndexOf(".");
		if (dot >= 0) {
			return uri.substring(dot);
		} else {
			// No extension.
			return "";
		}
	}

	// compress the image bytes before storing it in the database
	public static byte[] compressBytes(byte[] data) throws IOException {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		outputStream.close();
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) throws DataFormatException, IOException {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!inflater.finished()) {
			int count = inflater.inflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		outputStream.close();
		return outputStream.toByteArray();
	}

}
