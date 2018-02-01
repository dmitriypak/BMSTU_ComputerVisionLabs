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
	private File binaryFile;
	private String binaryFilePath;
	private String greyScalePath;
	private File detectPeopleFile;
	private String detectPeopleFilePath;
	private File detectPeopleBinaryFile;
	private String detectPeopleBinaryFilePath;
	private File histogram;
	private String histogramPath;
	private int brightness_value;
	
	
	
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

	public File getDetectPeopleFile() {
		return detectPeopleFile;
	}

	public void setDetectPeopleFile(File detectPeopleFile) {
		this.detectPeopleFile = detectPeopleFile;
	}

	public String getDetectPeopleFilePath() {
		return detectPeopleFilePath;
	}

	public void setDetectPeopleFilePath(String detectPeopleFilePath) {
		this.detectPeopleFilePath = detectPeopleFilePath;
	}

	public File getBinaryFile() {
		return binaryFile;
	}

	public void setBinaryFile(File binaryFile) {
		this.binaryFile = binaryFile;
	}

	public String getBinaryFilePath() {
		return binaryFilePath;
	}

	public void setBinaryFilePath(String binaryFilePath) {
		this.binaryFilePath = binaryFilePath;
	}

	public File getDetectPeopleBinaryFile() {
		return detectPeopleBinaryFile;
	}

	public void setDetectPeopleBinaryFile(File detectPeopleBinaryFile) {
		this.detectPeopleBinaryFile = detectPeopleBinaryFile;
	}

	public String getDetectPeopleBinaryFilePath() {
		return detectPeopleBinaryFilePath;
	}

	public void setDetectPeopleBinaryFilePath(String detectPeopleBinaryFilePath) {
		this.detectPeopleBinaryFilePath = detectPeopleBinaryFilePath;
	}

	public File getHistogram() {
		return histogram;
	}

	public void setHistogram(File histogram) {
		this.histogram = histogram;
	}

	public String getHistogramPath() {
		return histogramPath;
	}

	public void setHistogramPath(String histogramPath) {
		this.histogramPath = histogramPath;
	}

	public int getBrightness_value() {
		return brightness_value;
	}

	public void setBrightness_value(int brightness_value) {
		this.brightness_value = brightness_value;
	}

	
}