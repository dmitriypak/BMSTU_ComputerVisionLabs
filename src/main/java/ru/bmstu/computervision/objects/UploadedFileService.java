package ru.bmstu.computervision.objects;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.LinkedHashMap;
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
    int histSize = 256;    // bin size
    float range[] = { MIN_VALUE, MAX_VALUE };
    
    
	public ModelAndView checkFile(UploadedFile file, RequestContext context) throws IOException {
		//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		ModelAndView mav = new ModelAndView();
		ServletExternalContext externalContext = (ServletExternalContext) context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getNativeRequest();
		HttpServletResponse response = (HttpServletResponse) externalContext.getNativeResponse();		
		MultipartFile uploadedFile = null;
		if (file.getFile()!=null) {
//			Map<?,?>map = new LinkedHashMap<>();
//			map = context.getFlowScope().asMap();
//			context.getFlowScope().get("file");
//			uploadedFile = (UploadedFile) context.getFlowScope().get("file");
//			for(Map.Entry<?, ?> item:map.entrySet()) {
//				uploadedFile = (UploadedFile) item.getValue();
//				System.out.println("$$$ полученный файл " + uploadedFile.getFile().getOriginalFilename());
//				System.out.println(item.getKey());
//		
//			}
//			mapParam = context.getRequestParameters().asMap();
			uploadedFile = file.getFile();
			rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			rootPath2 = request.getSession().getServletContext().getRealPath("/");
			String fileName =uploadedFile.getOriginalFilename();
			String dir = rootPath +"/resources/";
			
			file.setPath(dir+fileName);
			
			File sourceFile = createNewFile(rootPath2,uploadedFile);
			File greyScaleFile = imageToGrayScale(rootPath2,sourceFile);
			
			file.setSourceFile(sourceFile);
			file.setFileName(fileName);
			file.setDir(dir);
			file.setAbsolutePath(rootPath2+ "resources"+ File.separator);
			file.setGreyScaleFile(greyScaleFile);
			file.setGreyScalePath(dir+greyScaleFile.getName());
			
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
			File dir = new File(path + "resources"+ File.separator);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			newFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
			byte[] bytes = file.getBytes();

			
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
			stream.write(bytes);
			stream.flush();
			stream.close();
			
		}catch(IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return newFile;
	}
	
    private File imageToGrayScale(String path, File file) throws IOException {
    	File dir = new File(path + "resources"+ File.separator);
    	String fileName = file.getName();
    	BufferedImage image = ImageIO.read(file);
        byte[] data =  ((DataBufferByte)(image.getRaster().getDataBuffer())).getData();
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        mat.put(0, 0, data);

        Mat mat1 = new Mat(image.getHeight(),image.getWidth(),CvType.CV_8UC1);
        Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);

        byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
        mat1.get(0, 0, data1);
        BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
        image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

        File ouptut = new File(dir.getAbsolutePath() + File.separator + fileName.substring(0,fileName.lastIndexOf("."))+"_grey"+fileName.substring(fileName.lastIndexOf("."), fileName.length()));
        ImageIO.write(image1, "jpg", ouptut);
        
        return ouptut;
    }
}
