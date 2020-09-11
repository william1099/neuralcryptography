package ncrypto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import ncrypto.common.Calculation;
import ncrypto.common.InputVector;
import ncrypto.common.Key;
import ncrypto.common.TPM;
import ncrypto.common.TPME;
import ncrypto.common.TableModel;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;

public class Display extends JFrame {
	static final long serialVersionUID = 42L;
	private JPanel headerPane;
	private JPanel contentPane, contentPane_10, contentPane_2;
	int xMouse, yMouse = 0;
	private JPanel menuHome, menuHelp;
	private JPanel homeActive, helpActive;
	private JPanel[] indicators;
	private JLabel lblX;
	private JLabel lblM;
	private JTextField txtHiddenNeuron;
	private JTextField txtInput;
	private JTextField txtBobot;
	private Button btnInitTPM;
	private Button btnReset;
	private TPM tpm1, tpm2;
	private TPME tpm3;
	private JTable weightTable1, weightTable2;
	private int K, N, L;
	private long epoch;
	private JLabel lblStatus;
	private boolean ready = false;
	FileOutputStream fos = null;
	ObjectOutputStream oos;
	private InputVector inputVector;
	private JPanel leftWeightPanel;
	private JPanel rightWeightPanel;
	private Button btnMulai;
	private JLabel lblEpoch;
	private boolean syncFlag = false;
	private JProgressBar progressBar;
	private Button btnSimpan;
	private Button btnHapus;
	private float sec;
	private ScrollPane scrollPane;
	private JTextArea textAreaKunci;
	static Display frame;
	private boolean textAreaDeleted = false;
	private JLabel leftLock, leftUnlock, rightLock, rightUnlock, rightReport, leftReport;
	private JLabel lblWaktu;
	private ArrayList<Calculation> calculations;
	private JScrollPane scrollPane_1;
	private JLabel lblNewLabel;
	private JSeparator separator_4;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_4;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Display();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Display() {
		initComponents();
		initEvents(this);
	}
	
	public void initComponents() {
		
		calculations = new ArrayList<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1182, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		// Header
		headerPane = new JPanel();
		headerPane.setBounds(0, 0, 1184, 45);
		headerPane.setBackground(new Color(52, 152, 219));
		contentPane.add(headerPane);
		headerPane.setLayout(null);
		
		
		// Menu
		menuHome = new JPanel();
		menuHome.setBounds(0, 0, 111, 45);
		menuHome.setBackground(new Color(52, 152, 219));
		headerPane.add(menuHome);
		menuHome.setLayout(null);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Arial", Font.PLAIN, 17));
		lblHome.setForeground(new Color(255, 255, 255));
		lblHome.setBounds(36, 15, 65, 14);
		menuHome.add(lblHome);
		
		homeActive = new JPanel();
		homeActive.setBounds(0, 0, 111, 4);
		homeActive.setBackground(new Color(223, 233, 252));
		menuHome.add(homeActive);
		
		menuHelp = new JPanel();
		menuHelp.setLayout(null);
		menuHelp.setBackground(new Color(52, 152, 219));
		menuHelp.setBounds(112, 0, 111, 45);
		headerPane.add(menuHelp);
		
		JLabel lblHelp = new JLabel("Panduan");
		lblHelp.setForeground(Color.WHITE);
		lblHelp.setFont(new Font("Arial", Font.PLAIN, 17));
		lblHelp.setBounds(20, 15, 65, 14);
		menuHelp.add(lblHelp);
		
		helpActive = new JPanel();
		helpActive.setBounds(0, 0, 111, 4);
		helpActive.setBackground(new Color(223, 233, 252));
		helpActive.setVisible(false);
		menuHelp.add(helpActive);
		
		// Minimize, Close
		lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setOpaque(true);
		lblX.setBackground(new Color(255, 0, 0));
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblX.setForeground(new Color(255, 255, 255));
		lblX.setBounds(1153, 0, 31, 23);
		headerPane.add(lblX);
		
		lblM = new JLabel("—");
		lblM.setOpaque(true);
		lblM.setHorizontalAlignment(SwingConstants.CENTER);
		lblM.setForeground(Color.WHITE);
		lblM.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblM.setBackground(new Color(106, 161, 254));
		lblM.setBounds(1122, 0, 31, 23);
		headerPane.add(lblM);
		
		indicators = new JPanel[]{homeActive, helpActive};
	
	      
		// Container
		contentPane_2 = new JPanel();
		contentPane_2.setToolTipText("");
		contentPane_2.setBackground(new Color(255, 255, 255));
		contentPane_2.setBounds(0, 45, 1184, 516);
		contentPane_2.setLayout(null);
		contentPane.add(contentPane_2);
		
		// Left Pane
		JPanel leftPane = new JPanel();
		leftPane.setBounds(0, 0, 300, 516);
		leftPane.setBackground(new Color(53, 111, 197));
		leftPane.setLayout(null);
		contentPane_2.add(leftPane);
		
		JPanel leftContent = new JPanel();
		leftContent.setBounds(0, 62, 300, 174);
		leftContent.setBackground(new Color(106, 161, 254));
		leftPane.add(leftContent);
		leftContent.setLayout(null);
		
	
		JSeparator separator = new JSeparator();
		separator.setBounds(76, 131, 127, 7);
		leftContent.add(separator);
		
		JLabel lblAlice = new JLabel("Sender");
		lblAlice.setForeground(new Color(255, 255, 255));
		lblAlice.setFont(new Font("Trebuchet MS", Font.BOLD, 37));
		lblAlice.setBounds(82, 11, 141, 40);
		leftPane.add(lblAlice);
		
		// left icons
		JLabel aliceIcon = new JLabel("");
		aliceIcon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		aliceIcon.setIcon(new ImageIcon(Display.class.getResource("/ncrypto/resources/icons8-female-user-100.png")));
		aliceIcon.setBounds(88, 10, 144, 119);
		leftContent.add(aliceIcon);
		
		
		leftLock = new JLabel("");
		leftLock.setForeground(new Color(255, 255, 255));
		leftLock.setIcon(new ImageIcon(Display.class.getResource("/ncrypto/resources/icons8-lock-32.png")));
		leftLock.setBounds(81, 139, 47, 33);
		leftContent.add(leftLock);
		
		leftUnlock = new JLabel("");
		leftUnlock.setIcon(new ImageIcon(Display.class.getResource("/ncrypto/resources/icons8-unlock-32.png")));
		leftUnlock.setForeground(Color.WHITE);
		leftUnlock.setBounds(125, 139, 47, 33);
		leftContent.add(leftUnlock);
		
		leftReport = new JLabel("");
		leftReport.setIcon(new ImageIcon(Display.class.getResource("/ncrypto/resources/icons8-statistics-32.png")));
		leftReport.setForeground(Color.WHITE);
		leftReport.setBounds(167, 140, 47, 33);
		leftContent.add(leftReport);
		
		leftWeightPanel = new JPanel();
		leftWeightPanel.setLayout(new BorderLayout());
		leftWeightPanel.setBounds(20, 272, 258, 213);
		leftPane.add(leftWeightPanel);
		
		JLabel lblBobot = new JLabel("Bobot");
		lblBobot.setForeground(Color.WHITE);
		lblBobot.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblBobot.setBounds(114, 247, 55, 21);
		leftPane.add(lblBobot);
		
		// Middle Pane
		JLabel lblHiddenNeuron = new JLabel("Jumlah Hidden Neuron (K) :");
		lblHiddenNeuron.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHiddenNeuron.setBounds(335, 49, 175, 28);
		contentPane_2.add(lblHiddenNeuron);
		
		txtHiddenNeuron = new JTextField();
		txtHiddenNeuron.setBounds(520, 55, 86, 20);
		txtHiddenNeuron.setText(5 + "");
		contentPane_2.add(txtHiddenNeuron);
		txtHiddenNeuron.setColumns(10);
		
		JLabel lblInput = new JLabel("Jumlah Input (N) :");
		lblInput.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInput.setBounds(335, 88, 175, 28);
		contentPane_2.add(lblInput);
		
		txtInput = new JTextField();
		txtInput.setColumns(10);
		txtInput.setBounds(520, 94, 86, 20);
		txtInput.setText(5 + "");
		contentPane_2.add(txtInput);
		
		JLabel lblNilaiBobot = new JLabel("Batas Nilai Bobot (L) :");
		lblNilaiBobot.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNilaiBobot.setBounds(335, 127, 175, 28);
		contentPane_2.add(lblNilaiBobot);
		
		txtBobot = new JTextField();
		txtBobot.setColumns(10);
		txtBobot.setBounds(520, 133, 86, 20);
		txtBobot.setText(10 + "");
		contentPane_2.add(txtBobot);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(51, 51, 204));
		separator_1.setBounds(335, 33, 474, 7);
		contentPane_2.add(separator_1);
		
		btnInitTPM = new Button("Inisialisasi TPM");
		btnInitTPM.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnInitTPM.setForeground(Color.WHITE);
		btnInitTPM.setBackground(new Color(52, 152, 219));
		btnInitTPM.setBounds(656, 49, 132, 45);
		contentPane_2.add(btnInitTPM);
		
		btnReset = new Button("Reset");
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnReset.setBackground(new Color(52, 152, 219));
		btnReset.setBounds(656, 116, 132, 39);
		contentPane_2.add(btnReset);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(51, 51, 204));
		separator_2.setBounds(335, 172, 474, 7);
		contentPane_2.add(separator_2);
		
		btnMulai = new Button("Mulai");
		btnMulai.setForeground(Color.WHITE);
		btnMulai.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnMulai.setBackground(new Color(52, 152, 219));
		btnMulai.setBounds(656, 191, 132, 39);
		contentPane_2.add(btnMulai);
		
		progressBar = new JProgressBar();
		progressBar.setToolTipText("");
		progressBar.setBounds(335, 210, 297, 20);
		contentPane_2.add(progressBar);
		
		JLabel lblSinkronisasi = new JLabel("Sinkronisasi");
		lblSinkronisasi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSinkronisasi.setBounds(453, 185, 80, 14);
		contentPane_2.add(lblSinkronisasi);
		
		JLabel lblJumlahEpoch = new JLabel("Jumlah Epoch :  ");
		lblJumlahEpoch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblJumlahEpoch.setBounds(335, 241, 99, 20);
		contentPane_2.add(lblJumlahEpoch);
		
		JLabel lblWaktuSinkronisasi = new JLabel("Waktu Sinkronisasi (s)  :  ");
		lblWaktuSinkronisasi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblWaktuSinkronisasi.setBounds(335, 272, 152, 20);
		contentPane_2.add(lblWaktuSinkronisasi);
		
		JLabel lblKunci_1 = new JLabel("Kunci : ");
		lblKunci_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblKunci_1.setBounds(335, 308, 198, 20);
		contentPane_2.add(lblKunci_1);
		
		scrollPane = new ScrollPane();
		scrollPane.setBounds(335, 337, 451, 100);
		contentPane_2.add(scrollPane);
		
		textAreaKunci = new JTextArea();
		textAreaKunci.setBounds(485, 395, 4, 22);
		scrollPane.add(textAreaKunci);
		
		btnSimpan = new Button("Simpan");
		btnSimpan.setForeground(Color.WHITE);
		btnSimpan.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnSimpan.setBackground(new Color(52, 152, 219));
		btnSimpan.setBounds(696, 443, 92, 28);
		contentPane_2.add(btnSimpan);
		
		btnHapus = new Button("Hapus");
		btnHapus.setForeground(Color.BLACK);
		btnHapus.setFont(new Font("Arial Black", Font.PLAIN, 15));
		btnHapus.setBackground(new Color(223, 233, 252));
		btnHapus.setBounds(595, 443, 92, 28);
		contentPane_2.add(btnHapus);
		
		lblStatus = new JLabel("");
		lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblStatus.setBounds(337, 8, 451, 14);
		contentPane_2.add(lblStatus);
		
		
		/// RightPane
		JPanel rightPane = new JPanel();
		rightPane.setBounds(868, 0, 358, 516);
		contentPane_2.add(rightPane);
		rightPane.setLayout(null);
		rightPane.setBackground(new Color(53, 111, 197));
		
		JPanel rightContent = new JPanel();
		rightContent.setLayout(null);
		rightContent.setBackground(new Color(106, 161, 254));
		rightContent.setBounds(0, 62, 356, 174);
		rightPane.add(rightContent);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(104, 133, 127, 7);
		rightContent.add(separator_3);
		
		JLabel bobIcon = new JLabel("");
		bobIcon.setIcon(new ImageIcon(Display.class.getResource("/ncrypto/resources/icons8-user-male-100.png")));
		bobIcon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bobIcon.setBounds(117, 11, 144, 119);
		rightContent.add(bobIcon);
		
		rightLock = new JLabel("");
		rightLock.setIcon(new ImageIcon(Display.class.getResource("/ncrypto/resources/icons8-lock-32.png")));
		rightLock.setForeground(Color.WHITE);
		rightLock.setBounds(101, 139, 47, 33);
		rightContent.add(rightLock);
		
		rightUnlock = new JLabel("");
		rightUnlock.setIcon(new ImageIcon(Display.class.getResource("/ncrypto/resources/icons8-unlock-32.png")));
		rightUnlock.setForeground(Color.WHITE);
		rightUnlock.setBounds(148, 139, 47, 33);
		rightContent.add(rightUnlock);
		
		rightReport = new JLabel("");
		rightReport.setIcon(new ImageIcon(Display.class.getResource("/ncrypto/resources/icons8-statistics-32.png")));
		rightReport.setForeground(Color.WHITE);
		rightReport.setBounds(194, 139, 47, 33);
		rightContent.add(rightReport);
		
		JLabel lblBob = new JLabel("Receiver");
		lblBob.setForeground(Color.WHITE);
		lblBob.setFont(new Font("Trebuchet MS", Font.BOLD, 37));
		lblBob.setBounds(86, 11, 159, 40);
		rightPane.add(lblBob);
		
		rightWeightPanel = new JPanel();
		rightWeightPanel.setBounds(27, 272, 262, 213);
		rightPane.add(rightWeightPanel);
		rightWeightPanel.setLayout(new BorderLayout());
		
		JLabel lblBobot_2 = new JLabel("Bobot");
		lblBobot_2.setForeground(Color.WHITE);
		lblBobot_2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblBobot_2.setBounds(128, 247, 55, 21);
		rightPane.add(lblBobot_2);
		
		lblEpoch = new JLabel("0");
		lblEpoch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEpoch.setBounds(446, 241, 99, 20);
		contentPane_2.add(lblEpoch);
		
		lblWaktu = new JLabel("0");
		lblWaktu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblWaktu.setBounds(497, 277, 109, 15);
		contentPane_2.add(lblWaktu);
		
		
		// Container Help
		contentPane_10 = new JPanel();
		contentPane_10.setBackground(Color.WHITE);
		contentPane_10.setToolTipText("");
		contentPane_10.setBounds(0, 45, 1193, 516);
		contentPane_10.setLayout(null);
		contentPane.add(contentPane_10);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(53, 65, 1063, 402);
		contentPane_10.add(scrollPane_1);
		
		lblNewLabel = new JLabel("Panduan");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblNewLabel.setBounds(543, 11, 143, 31);
		contentPane_10.add(lblNewLabel);
		
		separator_4 = new JSeparator();
		separator_4.setBackground(new Color(106, 161, 254));
		separator_4.setBounds(502, 44, 184, 10);
		contentPane_10.add(separator_4);
		
		 panel_1 = new JPanel();
		 panel_1.setBackground(new Color(106, 161, 254));
         panel_1.setBounds(0, 0, 1193, 10);
         contentPane_10.add(panel_1);
         
         panel_2 = new JPanel();
         panel_2.setBounds(0, 0, 10, 505);
         contentPane_10.add(panel_2);
         panel_2.setBackground(new Color(106, 161, 254));
         
         panel_4 = new JPanel();
         panel_4.setBackground(new Color(106, 161, 254));
         panel_4.setBounds(1173, 0, 10, 505);
         contentPane_10.add(panel_4);
 
		JEditorPane editorPane = new JEditorPane();
		scrollPane_1.setViewportView(editorPane);
		editorPane.setEditable(false);   
	      URL url= Display.class.getResource("../resources/panduan.html");

	      try {   
	         editorPane.setPage(url);
	
	        // System.out.println("ditemukan");
	      } catch (IOException e) { 
	         editorPane.setContentType("text/html");
	         editorPane.setText("<html>Page not found.</html>");
	      }
		
	      contentPane_10.setVisible(false);
	}
	
	public void initEvents(JFrame frame) {
		
		// Move Header
		headerPane.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				xMouse  = evt.getX();
				yMouse = evt.getY();
			}
		});
	
		headerPane.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent evt) {
				int x = evt.getXOnScreen();
				int y = evt.getYOnScreen();
				setLocation(x - xMouse , y - yMouse);
			}
		});
		
		menuHome.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				changeIndicator(homeActive);
				
				contentPane_10.setVisible(false);
				contentPane_2.setVisible(true);
			}
		});
		
		
		
		menuHelp.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				changeIndicator(helpActive);
				
				contentPane_10.setVisible(true);
				contentPane_2.setVisible(false);
			}
		});
		
		lblX.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				System.exit(0);
			}
		});
		
		lblM.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		
		
		btnInitTPM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initTPM();
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		
		btnMulai.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sinkronisasi();
			}
		});
		
		btnSimpan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveKey();
			}
		});
		
		btnHapus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textAreaKunci.getText() == "") {
					JOptionPane.showMessageDialog(frame, "Field Kunci Kosong!");
					return;
				}
				textAreaKunci.setText("");
				textAreaDeleted = true;
			}
		});
		
		leftLock.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				ImgEncryption encryptImg = new ImgEncryption();
				encryptImg.setVisible(true);
			}
		});
		
		leftUnlock.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				ImgDecryption decryptImg = new ImgDecryption();
				decryptImg.setVisible(true);
			}
		});
		
		rightLock.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				ImgEncryption encryptImg = new ImgEncryption();
				encryptImg.setVisible(true);
			}
		});
		
		rightUnlock.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				ImgDecryption decryptImg = new ImgDecryption();
				decryptImg.setVisible(true);
			}
		});
		
		leftReport.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				Report report = new Report("Sender");
				report.setVisible(true);
			}
		});
		
		rightReport.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				Report report = new Report("Receiver");
				report.setVisible(true);
			}
		});
	}
	
	public void saveKey() {
		if(syncFlag != false) {
			if(textAreaDeleted == true) {
				JOptionPane.showMessageDialog(frame, "Kunci telah dihapus", "Inane Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Key key = new Key();
			key.setDate((new SimpleDateFormat("ddMMyyyyhhmmss E").format(new Date())));
			key.setK(this.K);
			key.setL(L);
			key.setN(N);
			key.setKey(tpm1.getWeights());
			key.setIterasi(epoch);
			key.setTime(sec);
			key.addCalc(calculations);
			//System.out.println(Arrays.toString(key.getKey()));
			writeObjToFile(key);
			//System.out.println(key.getKey()[0][0]);
		} else {
			JOptionPane.showMessageDialog(frame, "Lakukan sinkronisasi terlebih dahulu!");
		}
		
	}
	
	public void initTPM() {
		
		if(tpm1 != null && tpm2 != null && ready) {
			lblStatus.setText("TPM telah terinisialisasi !. Tekan tombol Mulai untuk melakukan sinkronisasi");
			return;
		}
		
		String kField, nField, lField;
		
		kField = txtHiddenNeuron.getText().trim();
		nField = txtInput.getText().trim();
		lField = txtBobot.getText().trim();
		
		if(isValid(kField) && isValid(nField) && isValid(lField)) {
			K = Integer.parseInt(kField);
			N = Integer.parseInt(nField);
			L = Integer.parseInt(lField);
			
			setGUIState(false);
			
			// inisialisasi TPM
			tpm1 = new TPM(K, N, L);
			tpm2 = new TPM(K, N, L);
			//tpm3 = new TPME(K, N, L);
					
			
			// inisialisasi bobot secara acak
			tpm1.initWeights();
			tpm2.initWeights();
			//tpm3.initWeights();
			// inisialisasi input vector
			inputVector = new InputVector(K, N);
			
			try {
				initWeightTable();
				ready = true;
			} catch(Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}		
		}
	}
	
	public void sinkronisasi() {
		if(tpm1 == null && tpm2 == null && !ready) {
			lblStatus.setText("Inisialisasi TPM terlebih dahulu!");
			return;
		}
		
		if(syncFlag) {
			lblStatus.setText("TPM telah tersinkronisasi. Klik tombol reset");
			return;
		}
		lblStatus.setText("Memulai proses sinkronisasi..");
		
		int error = tpm1.getError(tpm2);
		epoch = 0;
		
		int[][] X = new int[][] {}, weightTPMA = new int[][] {}, 
				weightTPMB = new int[][] {};
		int outputTPMA = 0, outputTPMB = 0;
		long elapsedTime;
		
		//cosSim(tpm1.getWeights(), tpm2.getWeights(), epoch);
		
		long start = System.currentTimeMillis();
		while(!syncFlag) {
			elapsedTime = System.currentTimeMillis();
			if((elapsedTime - start) / 1000F > 7.0) {
				JOptionPane.showMessageDialog(frame, "Operasi memakan waktu yang lama, gunakan topologi jaringan lain atau tunggu hingga selesai");
				break;
			}
			
			//cosSim(tpm1.getWeights(), tpm2.getWeights(), epoch);
			if(error == 0) {
				long end = System.currentTimeMillis() - start;
				sec = end / 1000f;
				
				//cosSim(tpm1.getWeights(), tpm2.getWeights(), epoch);
				lblWaktu.setText(sec + " s");
				progressBar.setValue(100);
				syncFlag = true;
				lblStatus.setText("Sinkronisasi selesai");
				lblEpoch.setText(epoch + "");
				
				
				
				 //add the second latest calculation
				Calculation calc2 = new Calculation();
				calc2.setWAlice(weightTPMA);
				calc2.setWBob(weightTPMB);
				calc2.setX(X);
				calc2.setOutputAlice(outputTPMA);
				calc2.setOutputBob(outputTPMB);
				calculations.add(calc2);
				
				// add the latest calculation 
				Calculation calc = new Calculation();
				calc.setWAlice(cloneArr(tpm1.getWeights()));
				calc.setWBob(cloneArr(tpm2.getWeights()));
				calc.setX(new int[][] {});
				calc.setOutputAlice(tpm1.getOutput());
				calc.setOutputBob(tpm2.getOutput());
				calculations.add(calc);
				
				writeToTextArea();
				
				textAreaDeleted = false;
				
				//printCalculation(calculations);
		
				break;
			}
			epoch++;
			
			//System.out.println("---- epoch  : ------ " + epoch);
			
			
			inputVector.initInput();
			int[][] x = inputVector.getInputVector();
			
			tpm1.calcOutput(x);
			tpm2.calcOutput(x);
			
			if(epoch <= 5) {
				Calculation calc = new Calculation();
				calc.setWAlice(cloneArr(tpm1.getWeights()));
				calc.setWBob(cloneArr(tpm2.getWeights()));
				calc.setX(cloneArr(x));
				calc.setOutputAlice(tpm1.getOutput());
				calc.setOutputBob(tpm2.getOutput());
				calculations.add(calc);
				
			}
			

			if(tpm1.getOutput() == tpm2.getOutput()) {
				
				weightTPMA = cloneArr(tpm1.getWeights());
				weightTPMB = cloneArr(tpm2.getWeights());
				X = cloneArr(x);
				outputTPMA = tpm1.getOutput();
				outputTPMB = tpm2.getOutput();
				
				tpm1.updateWeights(x);
				tpm2.updateWeights(x);
				
				// attacker 
				//tpm3.updateWeights(x, outputTPMA);
				
				error = tpm1.getError(tpm2);
				
				for(int k = 0; k < K; k++) {
					for(int j = 0; j < N; j++) {
						weightTable1.setValueAt(tpm1.getWeight(k, j), k, j);
						weightTable2.setValueAt(tpm2.getWeight(k, j), k, j);
					}
				}
				
				if(error < 100) {
					progressBar.setValue(100 - error);
					progressBar.updateUI();
				}
				
				// print input vector
//				for(int ii = 0; ii < X.length; ii++) {
//					for(int jj = 0; jj < X[ii].length; jj++) {
//						System.out.print(X[ii][jj] + " ");
//					}
//					System.out.println("");
//				}
				
				//System.out.println(cosSim2(tpm1.getWeights(), tpm2.getWeights(), epoch) + " " + cosSim2(tpm3.getWeights(), tpm2.getWeights(), epoch) + " " + epoch);
				//int[][] weightTPMANew = tpm1.getWeights(), weightTPMBNew = tpm2.getWeights();
				// print weights
//				for(int ii = 0; ii < weightTPMANew.length; ii++) {
//					for(int jj = 0; jj < weightTPMANew[ii].length; jj++) {
//						System.out.print(weightTPMANew[ii][jj] + " ");
//					}
//					System.out.println("");
//				}
//				System.out.println("-------------------\n");
//				
//				for(int ii = 0; ii < weightTPMBNew.length; ii++) {
//					for(int jj = 0; jj < weightTPMBNew[ii].length; jj++) {
//						System.out.print(weightTPMBNew[ii][jj] + " ");
//					}
//					System.out.println("");
//				}
//				System.out.println("-------------------\n");
				
				// primt hidden neurons
//				int[] tpm1H = tpm1.getHidden(), tpm2H = tpm2.getHidden();
//				for(int ii = 0; ii < tpm1H.length; ii++) {
//					System.out.print(tpm1H[ii] + " ");
//				}
//				System.out.println("----" + cosSim2(tpm1.getWeights(), tpm2.getWeights(), epoch));
//				for(int ii = 0; ii < tpm2H.length; ii++) {
//					System.out.print(tpm2H[ii] + " ");
//				}
//				System.out.println("----" + tpm1.getOutput());
//				System.out.println("\n\n");
//				
				
				//System.out.println(error);
				leftWeightPanel.updateUI();
				rightWeightPanel.updateUI();
			}
		}
		
	}
	
	public int[][] cloneArr(int[][] arr) {
		int[][] newArr = new int[arr.length][arr[0].length];
		for(int i = 0; i < arr.length; i++) {
			newArr[i] = arr[i].clone();
		}
		return newArr;
	}
	
	public void printCalculation(ArrayList<Calculation> calculation) {
		for(Calculation cal : calculation) {
			System.out.println("---- W Sender ----");
			for(int i = 0; i < cal.getWAlice().length; i++) {
				System.out.println(Arrays.toString(cal.getWAlice()[i]));
			}
			System.out.println("--------------");
			
//			System.out.println("---- W Bob ----");
//			for(int i = 0; i < cal.getWBob().length; i++) {
//				System.out.println(Arrays.toString(cal.getWBob()[i]));
//			}
//			System.out.println("--------------");
			
			System.out.println("---- X input ----");
			for(int i = 0; i < cal.getX().length; i++) {
				System.out.println(Arrays.toString(cal.getX()[i]));
			}
			System.out.println("--------------");
			
			System.out.println("----- Output Sender : " + cal.getOutputAlice() + " -----");
			//System.out.println("----- Output Bob  : " + cal.getOutputBob() + " -----");
			System.out.println("");
			
		}
	}
	public void writeToTextArea() {
		int[][] weights = tpm1.getWeights();
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
	public boolean writeObjToFile(Key key) {
		try {
			
			fos = new FileOutputStream("D:/Java Code/NeuralCrypto/src/ncrypto/keys/" + key.getDate() + ".txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(key);
			oos.close();
			fos.close();
			JOptionPane.showMessageDialog(frame, "Kunci berhasil disimpan!");
			
		} catch(IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(oos != null) {
					oos.close();
				}
				if(fos != null) {
					fos.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}		
		}
		return true;
	}
	public boolean isValid(String value) {
		try {
			int val = Integer.parseInt(value);
			if(val <= 0) return false;
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}

	public void reset() {
		
		setGUIState(true);
		
		txtHiddenNeuron.setText("");
		txtInput.setText("");
		txtBobot.setText("");
		
		calculations.clear();
		tpm1 = tpm2 = null;
		ready = false;
		textAreaDeleted = false;
		if(leftWeightPanel != null) {
			leftWeightPanel.removeAll();
			leftWeightPanel.updateUI();
		}
		if(rightWeightPanel != null) {
			rightWeightPanel.removeAll();
			rightWeightPanel.updateUI();
		}
		
		syncFlag = false;
	}
	
	public void changeIndicator(JPanel panel) {
		for(JPanel p : indicators) {
			if(!p.equals(panel)) {
				p.setVisible(false);
			}
		}
		panel.setVisible(true);
	}
	
	public void setGUIState(boolean flag) {
		txtHiddenNeuron.setEditable(flag);
		txtInput.setEditable(flag);
		txtBobot.setEditable(flag);
		btnInitTPM.setEnabled(flag);
	}
	
	public void initWeightTable() {
		
		weightTable1 = new JTable(new TableModel(K, N));
        weightTable2 = new JTable(new TableModel(K, N));
        
        
        weightTable1.setFillsViewportHeight(true);
        weightTable2.setFillsViewportHeight(true);
        
        setCellWidth(weightTable1, 30);
        setCellWidth(weightTable2, 30);
        
        weightTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        weightTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int k = 0; k < K; k++) {
            for (int j = 0; j < N; j++) {
                weightTable1.setValueAt(tpm1.getWeight(k, j), k, j);
            }
        }
        for (int k = 0; k < K; k++) {
            for (int j = 0; j < N; j++) {
                weightTable2.setValueAt(tpm2.getWeight(k, j), k, j);
            }
        }
        
        JScrollPane scrollPane1 = new JScrollPane(weightTable1);
        JScrollPane scrollPane2 = new JScrollPane(weightTable2);
        
        leftWeightPanel.removeAll();
        rightWeightPanel.removeAll();
        
        leftWeightPanel.add(scrollPane1);
        rightWeightPanel.add(scrollPane2);
        
        leftWeightPanel.updateUI();
        rightWeightPanel.updateUI();
	}
	
	public void setCellWidth(JTable table, int width) {
		TableColumnModel columnModel = table.getColumnModel();
		for(int i = 0; i < columnModel.getColumnCount(); i++) {
			columnModel.getColumn(i).setPreferredWidth(width);
		}
	}
	
	public void cosSim(int[][] weightA, int[][] weightB, long epoch) {
		double val = 0; 
		int dotUV = 0, lenU = 0, lenV = 0;
		
		for(int i = 0; i < weightA.length; i++) {
			for(int j = 0; j < weightA[0].length; j++) {
				dotUV += weightA[i][j] * weightB[i][j];
				lenU += weightA[i][j] * weightA[i][j];
				lenV += weightB[i][j] * weightB[i][j];
			}
			val += dotUV / (Math.sqrt(lenU) * Math.sqrt(lenV));
			dotUV = lenU = lenV = 0;
		}
		
		System.out.println(val / weightA.length + " " + epoch);
		 
	}
	
	public double cosSim2(int[][] weightA, int[][] weightB, long epoch) {
		double val = 0; 
		int dotUV = 0, lenU = 0, lenV = 0;
		
		for(int i = 0; i < weightA.length; i++) {
			for(int j = 0; j < weightA[0].length; j++) {
				dotUV += weightA[i][j] * weightB[i][j];
				lenU += weightA[i][j] * weightA[i][j];
				lenV += weightB[i][j] * weightB[i][j];
			}
			val += dotUV / (Math.sqrt(lenU) * Math.sqrt(lenV));
			dotUV = lenU = lenV = 0;
		}
		
		return (val / weightA.length);
		 
	}
}
