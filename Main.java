import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;  
import java.util.HashMap;  
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;  
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;  
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;  
public class Main  
{  
 
public static void main(String args[]) throws NotFoundException, WriterException, IOException 
{  
	Scanner sc = new Scanner(System.in);
	System.out.println("QR Kodu oluþturulacak ifade: ");
	String s = sc.next();
	KodOlustur(s);
	KodOku();
	
}  

public static void KodOlustur(String input) throws NotFoundException {
	
	try {
	 
 
	String path = "C:\\Users\\wolka\\Desktop\\Kod.png";  
	
	String charset = "UTF-8";  
	Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();  
	
	hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  
	
	generateQRcode(input, path, charset, hashMap, 200, 200);

	System.out.println("QR Code oluþturuldu.");  
	}  
	
	catch (WriterException e)
	{
		System.out.println("hata: "+ e.getMessage());
	}
	catch(IOException io)
	{
		System.out.println("hata: "+ io.getMessage());
		
	}
}

public static void generateQRcode(String data, String path, String charset, Map map, int h, int w) throws WriterException, IOException  
{  
  
BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);  
MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));  
}  


public static String readQRcode(String path, String charset, Map map) throws FileNotFoundException, IOException, NotFoundException  
{  
BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(path)))));  
Result rslt = new MultiFormatReader().decode(binaryBitmap);  
return rslt.getText();  
}  

public static void KodOku() throws WriterException, IOException, NotFoundException  
{  

String path = "C:\\Users\\wolka\\Desktop\\Kod.png";  
  
String charset = "UTF-8";  
Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();  
 
hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  
System.out.println("QR Kodun Ýçeriði:  \n"+ readQRcode(path, charset, hintMap));  
}  
}  
