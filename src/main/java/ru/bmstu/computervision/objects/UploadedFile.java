package ru.bmstu.computervision.objects;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class UploadedFile implements Serializable {
	private static final long serialVersionUID = 7730377931313245319L;
	private transient MultipartFile file;
	private String message;
	private String fileName;
	private String path;
	private String dir;
	private String absolutePath;
	private File greyScaleFile;
	private File sourceFile;
	private String greyScalePath;
	
	
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	
	
	public File getGreyScaleFile() {
		return greyScaleFile;
	}

	public void setGreyScaleFile(File greyScaleFile) {
		this.greyScaleFile = greyScaleFile;
	}

	public File getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}

	public String getGreyScalePath() {
		return greyScalePath;
	}

	public void setGreyScalePath(String greyScalePath) {
		this.greyScalePath = greyScalePath;
	}

	
}