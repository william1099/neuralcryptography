package ncrypto.view;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import ncrypto.common.Key;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import java.awt.Font;

public class ImgEncryption extends JFrame {
	static final long serialVersionUID = 42L;
	FileInputStream fis;
	ObjectInputStream ois;
	Key key;
	private JTextArea textAreaKunci;
	int[][] weights; 
	File selectedImage;
	BufferedImage resImg;
	private JLabel lblWaktu;
	static ImgEncryption frame;
	Key kunci;
	
	public static void main(String[] args) {
		
		frame = new ImgEncryption();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ImgEncryption() {
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 600);
		
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JButton btnPilihGambar = new JButton("Pilih Gambar");
		btnPilihGambar.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnPilihGambar.setForeground(Color.WHITE);
		btnPilihGambar.setBackground(new Color(52, 152, 219));
		btnPilihGambar.setBounds(241, 203, 100, 23);
		getContentPane().add(btnPilihGambar);
		
		JLabel lblOriImg = new JLabel("");
		lblOriImg.setBounds(29, 63, 202, 193);
		lblOriImg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		getContentPane().add(lblOriImg);
		
		JButton btnPilihKunci = new JButton("Pilih Kunci");
		btnPilihKunci.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnPilihKunci.setForeground(Color.WHITE);
		btnPilihKunci.setBackground(new Color(52, 152, 219));
		btnPilihKunci.setBounds(343, 203, 91, 23);
		getContentPane().add(btnPilihKunci);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 267, 410, 56);
		getContentPane().add(scrollPane);
		
		textAreaKunci = new JTextArea();
		scrollPane.setViewportView(textAreaKunci);
		
		JLabel lblResImg = new JLabel("");
		lblResImg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblResImg.setBounds(29, 334, 202, 193);
		getContentPane().add(lblResImg);
		
		JButton btnSaveImg = new JButton("Save");
		btnSaveImg.setBounds(241, 504, 89, 23);
		getContentPane().add(btnSaveImg);
		
		JButton btnEnkripsi = new JButton("Enkripsi");
		btnEnkripsi.setForeground(Color.WHITE);
		btnEnkripsi.setBackground(new Color(53, 111, 197));
		btnEnkripsi.setBounds(241, 233, 193, 23);
		getContentPane().add(btnEnkripsi);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLUE);
		separator.setBounds(29, 47, 405, 10);
		getContentPane().add(separator);
		
		JLabel lblEnkripsi = new JLabel("Enkripsi");
		lblEnkripsi.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblEnkripsi.setBounds(206, 26, 73, 23);
		getContentPane().add(lblEnkripsi);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(52, 152, 219));
		panel.setBounds(0, 0, 464, 15);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(52, 152, 219));
		panel_1.setBounds(0, 546, 464, 15);
		getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(241, 63, 193, 129);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblFieldImage = new JLabel("Jenis : ");
		lblFieldImage.setBounds(10, 11, 46, 14);
		panel_2.add(lblFieldImage);
		
		JLabel lblImg = new JLabel("");
		lblImg.setBounds(56, 11, 127, 14);
		panel_2.add(lblImg);
		
		JLabel lblFieldDimensi = new JLabel("Dimensi :");
		lblFieldDimensi.setBounds(10, 36, 56, 14);
		panel_2.add(lblFieldDimensi);
		
		JLabel lblDimensi = new JLabel("");
		lblDimensi.setBounds(66, 36, 117, 14);
		panel_2.add(lblDimensi);
		
		JLabel lblFieldUkuran = new JLabel("Ukuran :  ");
		lblFieldUkuran.setBounds(10, 61, 56, 14);
		panel_2.add(lblFieldUkuran);
		
		JLabel lblUkuran = new JLabel("");
		lblUkuran.setBounds(62, 61, 121, 14);
		panel_2.add(lblUkuran);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(241, 334, 193, 159);
		getContentPane().add(panel_3);
		
		JLabel label = new JLabel("Jenis : ");
		label.setBounds(10, 11, 46, 14);
		panel_3.add(label);
		
		JLabel lblImg2 = new JLabel("");
		lblImg2.setBounds(56, 11, 127, 14);
		panel_3.add(lblImg2);
		
		JLabel label_2 = new JLabel("Dimensi :");
		label_2.setBounds(10, 36, 56, 14);
		panel_3.add(label_2);
		
		JLabel lblDimensi2 = new JLabel("");
		lblDimensi2.setBounds(66, 36, 117, 14);
		panel_3.add(lblDimensi2);
		
		JLabel label_4 = new JLabel("Ukuran :  ");
		label_4.setBounds(10, 61, 56, 14);
		panel_3.add(label_4);
		
		JLabel lblUkuran2 = new JLabel("");
		lblUkuran2.setBounds(62, 61, 121, 14);
		panel_3.add(lblUkuran2);
		
		JLabel lblFieldWaktu = new JLabel("Waktu : ");
		lblFieldWaktu.setBounds(10, 86, 56, 14);
		panel_3.add(lblFieldWaktu);
		
		lblWaktu = new JLabel("");
		lblWaktu.setBounds(56, 86, 117, 14);
		panel_3.add(lblWaktu);
		
		btnPilihGambar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				BufferedImage img = null;
				JFileChooser fileChooser = openFileChooser("D:\\images\\img\\", "only png files", "png");
				int r = fileChooser.showOpenDialog(null);
				if(r == JFileChooser.APPROVE_OPTION) {
					System.out.println("file : " + fileChooser.getSelectedFile().getName() + " is selected");
					selectedImage = fileChooser.getSelectedFile();
					try {
						img = ImageIO.read(selectedImage);
						Image image = img.getScaledInstance(lblOriImg.getWidth(), lblOriImg.getHeight(), Image.SCALE_SMOOTH);
						ImageIcon imgIcon = new ImageIcon(image);
						String mimeType = Files.probeContentType(selectedImage.toPath());
						
						if(!mimeType.equals("image/png")) {
							JOptionPane.showMessageDialog(frame, "Citra harus berekstensi PNG");
							selectedImage = null;
							lblOriImg.setIcon(null);
							lblImg.setText("");
							lblUkuran.setText("");
							lblDimensi.setText("");
							return;
						}

						lblOriImg.setIcon(imgIcon);				
						lblImg.setText(mimeType);
						lblUkuran.setText(changeByte(selectedImage.length()));
						lblDimensi.setText(img.getWidth() + " X " + img.getHeight());
					} catch(IOException err) {
						err.printStackTrace();
					}
					
				} else {
					System.out.println("operation is cancelled");
				}
				
			}
			
		});
		
		btnPilihKunci.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = openFileChooser("D:\\Java Code\\NeuralCrypto\\src\\ncrypto\\keys", "Only txt file", "txt");
				int r = fileChooser.showOpenDialog(null);
				if(r == JFileChooser.APPROVE_OPTION) {
					key = getObjectFromFile(fileChooser.getSelectedFile().getName());
					weights = key.getKey();
					writeToTextArea(weights);	
					
				} else {
					
				}
			}
			
		});
		
		btnEnkripsi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {	
			
				if(selectedImage != null && kunci != null) {
					BufferedImage img;
					try {
						img = ImageIO.read(selectedImage);				
					    
						long start = System.currentTimeMillis();
						img = encrypt2(img);
						long end = System.currentTimeMillis() + 1;
						resImg = img;
					    
						File outputfile = new File("image.png");
						ImageIO.write(resImg, "png", outputfile);
				
						Image image = img.getScaledInstance(lblResImg.getWidth(), lblResImg.getHeight(), Image.SCALE_SMOOTH);
						ImageIcon imgIcon = new ImageIcon(image);
						lblResImg.setIcon(imgIcon);
						
						lblImg2.setText(Files.probeContentType(selectedImage.toPath()));
						lblUkuran2.setText(changeByte(outputfile.length()));
						lblDimensi2.setText(img.getWidth() + " X " + img.getHeight());
						lblWaktu.setText((end - start)  + " ms");
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}		
				} else {
					
					JOptionPane.showMessageDialog(frame, "Input citra dan kunci terlebih dahulu!");
				}
			}
		});
		
		
		btnSaveImg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = openFileChooser("D:\\images\\img\\", "*.png", "png");
				int r = fileChooser.showSaveDialog(null);
				if(r == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					
					if(resImg != null) writeImgToPNG(new File(file.getAbsolutePath() + ".png"), resImg);
					System.out.println(file.getName());
			
				}
				
			}
			
		});
		
		
	}
	
	public String changeByte(long length) {
		if((length / 1024) == 0) return length + " B";
		else if ((length / 1024) > 0 && (length / 1024) <= 1024) return (length / 1024) + " KB";
		else if ((length / 1024) > 0 && (length / 1024) > 1024) return (length / (1024 * 1024)) + " MB";
		return (length / (1024 * 1024 * 1024)) + " GB";
	}
	
	public BufferedImage encrypt(BufferedImage img) {
		int pixel, r, g, b, a;
		pixel = r = g = b = a = 0;
		int[] kunci = convertArrTo1d(weights);
		int cnt = 0;
		if(selectedImage != null && weights != null) {
			for(int y = 0; y < img.getHeight(); y++) {
				for(int x = 0; x < img.getWidth(); x++) {
					
					pixel = img.getRGB(x, y);
					
					a = (pixel >> 24) & 0xff;
					r = (pixel >> 16) & 0xff;
					g = (pixel >> 8) & 0xff;
					b = pixel & 0xff;
								
					r = ((r ) + kunci[cnt++]) % 256;
					cnt = cnt % (weights.length * weights[0].length);
						
					g = ((g ) + kunci[cnt++]) % 256;
					cnt = cnt % (weights.length * weights[0].length);
					
					b = ((b ) + kunci[cnt++]) % 256;
					cnt = cnt % (weights.length * weights[0].length);	
					
					r = cek(r);
					g = cek(g);
					b = cek(b);
					
					pixel = (a << 24) | (r << 16) | (g << 8) | b;
					img.setRGB(x, y, pixel);

				}
			}
			
		} 
		return img;
	}
	
	public BufferedImage encrypt2(BufferedImage img) {
		int pixel, r, g, b, a;
		pixel = r = g = b = a = 0;
		int[] kunci = convertArrTo1d(weights);
		int cnt = 0;
		if(selectedImage != null && weights != null) {
			for(int y = 0; y < img.getHeight(); y++) {
				for(int x = 0; x < img.getWidth(); x++) {
					
					pixel = img.getRGB(x, y);
					Color pxl = new Color(pixel);
					
					a = (pixel >> 24) & 0xff;
					r = pxl.getRed();
					g = pxl.getGreen();
					b = pxl.getBlue();
					if(a == 0) a = 254;
					else if (a < 255) a = 253; // alternative
								
					r = ((r ) + kunci[cnt++] * (y + 1)) % 256;
					cnt = cnt % (weights.length * weights[0].length);
						
					g = ((g ) + kunci[cnt++] * (y + 1)) % 256;
					cnt = cnt % (weights.length * weights[0].length);
					
					b = ((b ) + kunci[cnt++] * (y + 1)) % 256;
					cnt = cnt % (weights.length * weights[0].length);	
					
					r = cek(r);
					g = cek(g);
					b = cek(b);
					
					img.setRGB(x, y, new Color(r, g, b, a).getRGB());
					
				}
			}
		} 
		return img;
	}
	
	public BufferedImage encrypt3(BufferedImage img) {
		int pixel, r, g, b, a;
		pixel = r = g = b = a = 0;
		int[] kunci = convertArrTo1d(weights);
		int cnt = 0;
		if(selectedImage != null && weights != null) {
			for(int y = 0; y < img.getHeight(); y++) {
				for(int x = 0; x < img.getWidth(); x++) {
					
					pixel = img.getRGB(x, y);
					Color pxl = new Color(pixel);
					
					r = pxl.getRed();
					g = pxl.getGreen();
					b = pxl.getBlue();
							
					r = ((r ) + kunci[cnt++] * (y + 1)) % 256;
					cnt = cnt % (weights.length * weights[0].length);
						
					g = ((g ) + kunci[cnt++] * (y + 1)) % 256;
					cnt = cnt % (weights.length * weights[0].length);
					
					b = ((b ) + kunci[cnt++] * (y + 1)) % 256;
					cnt = cnt % (weights.length * weights[0].length);	
					
					r = cek(r);
					g = cek(g);
					b = cek(b);
					
					img.setRGB(x, y, new Color(r, g, b).getRGB());
				}
			}
			
		} 
		return img;
	}
	
	
	
	public int cek(int num) {
		if(num < 0) return num + 256;
		return num;
	}
	
	public int[] convertArrTo1d(int[][] arr) {
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				list.add(arr[i][j]);
			}
		}
		int[] newArr = new int[list.size()];
		for(int i = 0; i < list.size(); i++) {
			newArr[i] = Math.abs(list.get(i));
			
		}
		return newArr;
	}
	
	
	public void writeImgToPNG(File file, BufferedImage img) {
		try {
			ImageIO.write(img,"PNG",file);
			System.out.println((file.length() / 1024) + "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeToTextArea(int[][] arr) {

		int[][] weights = arr;
		String text = "";
		if(weights != null) {
			for(int i = 0; i < weights.length; i++) {
				for(int j = 0; j < weights[i].length; j++) {
					text += Math.abs(weights[i][j]);
					text += " ";
					//System.out.println(weights[i][j]);
				}
			}
			//System.out.println(text);
			textAreaKunci.setText(text);
			
		}
		
	}
	
	public static BufferedImage imageToBufferedImage(Image im) {
	     BufferedImage bi = new BufferedImage
	        (im.getWidth(null),im.getHeight(null),BufferedImage.TYPE_INT_RGB);
	     Graphics bg = bi.getGraphics();
	     bg.drawImage(im, 0, 0, null);
	     bg.dispose();
	     return bi;
	  }
	
	public JFileChooser openFileChooser(String path, String extFilter, String extension) {
		File f = new File(path);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(extFilter, extension);
		JFileChooser fileChooser = new JFileChooser(f);
		fileChooser.addChoosableFileFilter(filter);
		return fileChooser;
	}
	
	public Key getObjectFromFile(String filepath)  {
		kunci = null;
		try {
			
			fis = new FileInputStream("D:/Java Code/NeuralCrypto/src/ncrypto/keys/" + filepath);
			ois = new ObjectInputStream(fis);
			kunci = (Key) ois.readObject();
			
		} catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
			kunci = null;
		} finally {
			try {
				if(ois != null) {
					ois.close();
				}
				if(fis != null) {
					fis.close();
				}
				
			} catch(IOException e) {
				e.printStackTrace();
			}		
		}
		
		return kunci;
	}
	
}
