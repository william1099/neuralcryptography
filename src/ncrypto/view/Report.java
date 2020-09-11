package ncrypto.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import ncrypto.common.Calculation;
import ncrypto.common.Key;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;

public class Report extends JFrame {
	static final long serialVersionUID = 42L;
	private JPanel contentPane;
	private JTextArea textArea;
	private FileInputStream fis;
	private ObjectInputStream ois;
	private Key key;
	private JLabel lblNamaKunci, lblTanggal;
	private JLabel lblWaktu, lblJumIterasi;
	private JLabel lblK, lblN, lblL;
	private JLabel lblName;
	private JButton btnPilihKunci;
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report frame = new Report("Sender");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Report(String name) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, 434, 23);
		header.setLayout(null);
		header.setBackground(new Color(52, 152, 219));
		contentPane.add(header);
		
		JLabel lblTitle = new JLabel("Reports");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblTitle.setBounds(188, 34, 73, 23);
		contentPane.add(lblTitle);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLUE);
		separator.setBounds(38, 62, 354, 10);
		contentPane.add(separator);
		
		lblName = new JLabel("");
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblName.setBounds(336, 70, 59, 14);
		lblName.setText(name);
		contentPane.add(lblName);
		
		JLabel lblKunciTitle = new JLabel("Pilih Kunci :");
		lblKunciTitle.setBounds(38, 71, 70, 14);
		contentPane.add(lblKunciTitle);
		
		btnPilihKunci = new JButton("Pilih Kunci");
		btnPilihKunci.setForeground(Color.WHITE);
		//btnPilihKunci.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnPilihKunci.setBackground(new Color(52, 152, 219));
		btnPilihKunci.setBounds(118, 68, 123, 23);
		btnPilihKunci.setOpaque(true);
		contentPane.add(btnPilihKunci);
		
		JPanel detailPane = new JPanel();
		detailPane.setBounds(38, 104, 354, 106);
		contentPane.add(detailPane);
		detailPane.setLayout(null);
		
		JLabel lblKunci = new JLabel("Kunci :");
		lblKunci.setBounds(10, 11, 43, 14);
		detailPane.add(lblKunci);
		
		JLabel lblFieldTanggal = new JLabel("Tanggal :");
		lblFieldTanggal.setBounds(10, 36, 53, 14);
		detailPane.add(lblFieldTanggal);
		
		JLabel lblFieldJumIterasi = new JLabel("Jumlah Iterasi :");
		lblFieldJumIterasi.setBounds(10, 61, 93, 14);
		detailPane.add(lblFieldJumIterasi);
		
		JLabel lblFieldWaktu = new JLabel("Waktu :");
		lblFieldWaktu.setBounds(10, 86, 43, 14);
		detailPane.add(lblFieldWaktu);
		
		JLabel lblFieldK = new JLabel("K : ");
		lblFieldK.setBounds(287, 11, 25, 14);
		detailPane.add(lblFieldK);
		
		JLabel lblFieldN = new JLabel("N : ");
		lblFieldN.setBounds(287, 36, 25, 14);
		detailPane.add(lblFieldN);
		
		JLabel lblFieldL = new JLabel("L : ");
		lblFieldL.setBounds(287, 61, 25, 14);
		detailPane.add(lblFieldL);
		
		lblNamaKunci = new JLabel("");
		lblNamaKunci.setBounds(59, 11, 199, 14);
		detailPane.add(lblNamaKunci);
		
		lblTanggal = new JLabel("");
		lblTanggal.setBounds(69, 36, 199, 14);
		detailPane.add(lblTanggal);
		
		lblJumIterasi = new JLabel("");
		lblJumIterasi.setBounds(103, 61, 107, 14);
		detailPane.add(lblJumIterasi);
		
		lblWaktu = new JLabel("");
		lblWaktu.setBounds(63, 86, 172, 14);
		detailPane.add(lblWaktu);
		
		lblK = new JLabel("");
		lblK.setBounds(319, 11, 25, 14);
		detailPane.add(lblK);
		
		lblN = new JLabel("");
		lblN.setBounds(319, 36, 25, 14);
		detailPane.add(lblN);
		
		lblL = new JLabel("");
		lblL.setBounds(319, 61, 25, 14);
		detailPane.add(lblL);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 221, 354, 300);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		Border border = BorderFactory.createLineBorder(new Color(106, 161, 254));
	    textArea.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		scrollPane.setViewportView(textArea);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(52, 152, 219));
		panel.setBounds(0, 551, 434, 10);
		contentPane.add(panel);
		
		
		
		
		
		initEvents();
	}
	
	public void initEvents() {
		
		btnPilihKunci.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = openFileChooser("D:\\Java Code\\NeuralCrypto\\src\\ncrypto\\keys", "Only txt file", "txt");
				int r = fileChooser.showOpenDialog(null);
				if(r == JFileChooser.APPROVE_OPTION) {
					//System.out.println(fileChooser.getSelectedFile().getName());
					key = getObjectFromFile(fileChooser.getSelectedFile().getName());
					//weights = key.getKey();
					lblNamaKunci.setText("" + fileChooser.getSelectedFile().getName());
					lblTanggal.setText("" + parseDate(key.getDate()));
					lblJumIterasi.setText("" + key.getIterasi());
					lblWaktu.setText("" + key.getTime() + " s");
					lblK.setText("" + key.getK());
					lblL.setText("" + key.getL());
					lblN.setText("" + key.getN());
					printCalculation(key.getCalc(), lblName.getText(), key.getIterasi());
					
				} else {
					
				}
				
			}
			
		});
		
	}
	
	public String parseDate(String date) {
		return "" + date.substring(0, 2) + "-" + date.substring(2, 4) + "-" + 
				date.substring(4, 8) + "  " + date.substring(8, 10) + ":" 
						+ date.substring(10, 12) + ":" + date.substring(12, 14) ;
	}
	public void printCalculation(ArrayList<Calculation> calculation, String name, long iterasi) {
		String data = "";
		int counter = 0;
		for(Calculation cal : calculation) {
			counter++;
			if(counter == calculation.size() - 1) {
				data += "...............\n...............\n...............\n\n";
				data += "# Iterasi ke - " + (iterasi - 1) + "\n";
			} else if(counter == calculation.size()) {
				data += "# Iterasi ke - " + iterasi + "\n";
			} else {
				data += "# Iterasi ke - " + counter + "\n";
			}
			
			data += "-------- Bobot (W) " + name + " --------\n";
			if (name == "Sender") {
				for(int i = 0; i < cal.getWAlice().length; i++) {
					data += Arrays.toString(cal.getWAlice()[i]) + "\n";
				}
				data += "-------------------------------------\n\n";
				
				
				data += "-------- Input (X) " + name + " --------\n";
				for(int i = 0; i < cal.getX().length; i++) {
					data += Arrays.toString(cal.getX()[i]) + "\n";
				}
				data += "-------------------------------------\n";
				if(counter != calculation.size()) data += "Output neuron : " + cal.getOutputAlice() + "\n\n";
			} else if (name == "Receiver") {
				
				for(int i = 0; i < cal.getWBob().length; i++) {
					data += Arrays.toString(cal.getWBob()[i]) + "\n";
				}
				data += "-------------------------------------\n\n";
				
				
				data += "-------- Input (X) " + name + " --------\n";
				for(int i = 0; i < cal.getX().length; i++) {
					data += Arrays.toString(cal.getX()[i]) + "\n";
				}
				data += "-------------------------------------\n";
				if(counter != calculation.size()) data += "Output neuron : " + cal.getOutputBob() + "\n\n";
			}
			
		}
		data += "\nSelesai";
		textArea.setText(data);
		System.out.println(textArea);
	}

	public JFileChooser openFileChooser(String path, String extFilter, String extension) {
		File f = new File(path);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(extFilter, extension);
		JFileChooser fileChooser = new JFileChooser(f);
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		return fileChooser;
	}
	
	public Key getObjectFromFile(String filepath)  {
		Key kunci = null;
		try {
			
			fis = new FileInputStream("D:/Java Code/NeuralCrypto/src/ncrypto/keys/" + filepath);
			ois = new ObjectInputStream(fis);
			kunci = (Key) ois.readObject();
			
		} catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
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
