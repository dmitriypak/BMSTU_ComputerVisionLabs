package ru.bmstu.computervision.objects;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.webflow.context.servlet.ServletExternalContext;
import org.springframework.webflow.execution.RequestContext;

@Component
public class UploadedFileService {

	private static Mat img;
	public static final int BINS = 256;
	public static final int MIN_VALUE = 0;
	public static final int MAX_VALUE = 255;
	public static String rootPath;
	public static String rootPath2;
	int histSize = 256; // bin size
	float range[] = { MIN_VALUE, MAX_VALUE };

	public ModelAndView checkFile(UploadedFile file, RequestContext context) throws IOException {
		// System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		ModelAndView mav = new ModelAndView();
		ServletExternalContext externalContext = (ServletExternalContext) context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getNativeRequest();
		HttpServletResponse response = (HttpServletResponse) externalContext.getNativeResponse();
		MultipartFile uploadedFile = null;
		List<File> list = new ArrayList<File>();
		File greyScaleFile=null;
		File binaryFile=null;		
		
		if (file.getFile() != null) {
			uploadedFile = file.getFile();
			rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();
			rootPath2 = request.getSession().getServletContext().getRealPath("/");
			String fileName = uploadedFile.getOriginalFilename();
			String dir = rootPath + "/resources/";

			file.setPath(dir + fileName);

			
			
			File sourceFile = createNewFile(rootPath2, uploadedFile);
			list = imageToGrayScale(rootPath2, sourceFile);
			if (list.size()>0) {
				greyScaleFile = list.get(0);
				binaryFile = list.get(1);				
			}


			file.setSourceFile(sourceFile);
			file.setFileName(fileName);
			file.setDir(dir);
			file.setAbsolutePath(rootPath2 + "resources" + File.separator);
			file.setGreyScaleFile(greyScaleFile);
			file.setGreyScalePath(dir + greyScaleFile.getName());
			
			file.setBinaryFile(binaryFile);
			file.setBinaryFilePath(dir+binaryFile.getName());

			System.out.println("$$$ полученные параметры " + rootPath);
			System.out.println(rootPath2);
			mav.setViewName("success");

			return mav;
		} else {
			mav.setViewName("failed");
			return mav;
		}

	}

	private File createNewFile(String path, MultipartFile file) {
		System.loadLibrary("opencv_java330");

		File newFile = null;
		try {
			File dir = new File(path + "resources" + File.separator);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			newFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
			byte[] bytes = file.getBytes();

			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
			stream.write(bytes);
			stream.flush();
			stream.close();

		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return newFile;
	}

	private ArrayList<File> imageToGrayScale(String path, File file) throws IOException {
		List <File>listFiles = new ArrayList<File>();
		File dir = new File(path + "resources" + File.separator);
		String fileName = file.getName();
		BufferedImage image = ImageIO.read(file);
		byte[] data = ((DataBufferByte) (image.getRaster().getDataBuffer())).getData();
		Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
		mat.put(0, 0, data);
		Mat matGreyScale = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC1);

		String filePath = file.getAbsolutePath();

		// Mat mat = Imgcodecs.imread(filePath,0);

		Mat matBW = new Mat(); // Binary

		String greyScaleFileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_greyscale"
				+ fileName.substring(fileName.lastIndexOf("."), fileName.length());

		Imgproc.cvtColor(mat, matGreyScale, Imgproc.COLOR_BGR2GRAY);
		byte[] data1 = new byte[matGreyScale.rows() * matGreyScale.cols() * (int) (matGreyScale.elemSize())];
		matGreyScale.get(0, 0, data1);
		BufferedImage image1 = new BufferedImage(matGreyScale.cols(), matGreyScale.rows(),
				BufferedImage.TYPE_BYTE_GRAY);
		
		
		image1.getRaster().setDataElements(0, 0, matGreyScale.cols(), matGreyScale.rows(), data1);
		File greyScaleFile = new File(dir.getAbsolutePath() + File.separator + greyScaleFileName);
		ImageIO.write(image1, "jpg", greyScaleFile);
		listFiles.add(greyScaleFile);
		

		String binaryFileName = greyScaleFileName.substring(0, greyScaleFileName.lastIndexOf(".")) + "_binary"
				+ greyScaleFileName.substring(greyScaleFileName.lastIndexOf("."), greyScaleFileName.length());

		Imgproc.adaptiveThreshold(matGreyScale, matBW, 125, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 9,
				10);
		Imgcodecs.imwrite(dir.getAbsolutePath() + File.separator + binaryFileName, matBW);
		File binaryOuptut = new File(dir.getAbsolutePath() + File.separator + binaryFileName);
		listFiles.add(binaryOuptut);
		return (ArrayList<File>) listFiles;
	}
}
