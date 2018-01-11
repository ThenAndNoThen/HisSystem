package view;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import dto.BillDTO;
import dto.DocumentDTO;
import dto.DocumentDetailDTO;
import dto.UnitPriceDTO;
import model.modelImpl.MainModelImpl;
import presenter.MainPresenter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

public class MainWindow implements ActionListener, TableModelListener ,MainView{
	private static Logger logger = Logger.getLogger(MainWindow.class);
	private MainPresenter mainPresenter;
	
	private JFrame frame;
	private JPanel topBtnPanel;//顶部4个按钮面板
	private JPanel patientInfoPanel;//患者信息面板
	private JPanel billBtnPanel;//单据新增/删除面板
	private JPanel contentPane;
	
	private JPanel panel1,panel2;
	
	private JButton addButton,saveButton,cancelButton,exitButton;//顶部4按钮
	private JTextField patNameJTextField,patGenderJTextField,DoctorJTextField,departmentJTextField;//患者信息4个文本输入框
	private JButton billAddButton,billDeleteButton;//单据新增/删除按钮
	private JTextField textField;//执行单位文本框
	private JTable table_A;//单据明细信息
	private JTable table_B;//单据信息
	
	private final Dimension topButtonDimension = new Dimension(90,90);//顶部4按钮大小
	private final Font topButtonFont = new Font(null, Font.BOLD, 16);//顶部4按钮字体设置
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		mainPresenter = new MainPresenter(this);
	}

	private void billPanelInitialize() {
		contentPane = new JPanel();
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));

		
		JPanel panel_A = new JPanel();
		panel_A.setBorder(BorderFactory.createTitledBorder("单据明细信息"));
		
		JPanel panel_B = new JPanel();
		panel_B.setBorder(BorderFactory.createTitledBorder("单据信息"));
		panel_B.setLayout(new FlowLayout(FlowLayout.LEFT));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_A)
						.addComponent(panel_B))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_A, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_B, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(8))
		);
		
		//定义一维数据作为列标题  
	    String[] columnTitleA = {"药品或项目名称" , "单价" , "数量" , "金额"}; 
	    DefaultTableModel defTabelModel = new DefaultTableModel(columnTitleA,1);
//	    Object[][] tableDataA =   
//	        {  
//	            new Object[]{"","","",""},
//	        };  
		
		JLabel lblNewLabel1 = new JLabel("执行单位：@*");
		lblNewLabel1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_A.add(lblNewLabel1);
		lblNewLabel1.setFont(new Font("宋体", Font.PLAIN, 12));
		
		textField = new JTextField();
		panel_A.add(textField);
		textField.setColumns(25);
		table_A = new JTable(defTabelModel);
		table_A.setPreferredScrollableViewportSize(new Dimension(750,100));
		table_A.getModel().addTableModelListener(this);
		JScrollPane scrollPaneA = new JScrollPane(table_A);
		panel_A.add(scrollPaneA, BorderLayout.CENTER);
		panel_A.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		
		String[] columnTitleB = {"门诊科室" , "门诊医师" , "执行部门" , "单据金额"};
		DefaultTableModel defTabelModelB = new DefaultTableModel(columnTitleB,0);
//		Object[][] tableDataB =   
//	        {  
//	        }; 
		table_B = new JTable(defTabelModelB);
		table_B.setPreferredScrollableViewportSize(new Dimension(750,130));
		JScrollPane scrollPaneB = new JScrollPane(table_B);
		panel_B.add(scrollPaneB, BorderLayout.CENTER);
		contentPane.setLayout(gl_contentPane);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {	
		billPanelInitialize();
		
		//初始化控件
		this.frame = new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		this.topBtnPanel = new JPanel();
		this.addButton = new JButton("增加");
		this.saveButton = new JButton("保存");
		this.cancelButton = new JButton("取消");
		this.exitButton = new JButton("退出");
		topBtnPanel.setPreferredSize(new Dimension(frame.getWidth(),100));
//		topBtnPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		addButton.setPreferredSize(topButtonDimension);
		addButton.setFont(topButtonFont);
		saveButton.setPreferredSize(topButtonDimension);
		saveButton.setFont(topButtonFont);
		cancelButton.setPreferredSize(topButtonDimension);
		cancelButton.setFont(topButtonFont);
		exitButton.setPreferredSize(topButtonDimension);
		exitButton.setFont(topButtonFont);
		
		this.patientInfoPanel = new JPanel();
		this.patNameJTextField = new JTextField();
		this.patGenderJTextField = new JTextField();
		this.departmentJTextField = new JTextField();
		this.DoctorJTextField = new JTextField();
		patientInfoPanel.setBorder(BorderFactory.createTitledBorder("患者信息"));
		patientInfoPanel.setPreferredSize(new Dimension(0,100));
		JPanel tmpJpanel = new JPanel();
		tmpJpanel.setPreferredSize(new Dimension(frame.getWidth()-20,40));
		
		this.billBtnPanel = new JPanel();
		this.billAddButton = new JButton("单据新增");
		this.billDeleteButton = new JButton("单据删除");
		
		this.panel1 = new JPanel();
		this.panel2 = new JPanel();
		
		//控件布局关系
		topBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topBtnPanel.add(addButton);
		topBtnPanel.add(saveButton);
		topBtnPanel.add(cancelButton);
		topBtnPanel.add(exitButton);
		
		tmpJpanel.setLayout(new GridLayout(2,4));
		tmpJpanel.add(new JLabel("患者姓名*"));
		tmpJpanel.add(new JLabel("性别*"));
		tmpJpanel.add(new JLabel("门诊科室@*"));
		tmpJpanel.add(new JLabel("门诊医生@*"));
		tmpJpanel.add(patNameJTextField);
		tmpJpanel.add(patGenderJTextField);
		tmpJpanel.add(departmentJTextField);
		tmpJpanel.add(DoctorJTextField);
		patientInfoPanel.setLayout(new FlowLayout());
		patientInfoPanel.add(tmpJpanel);	
		
		billBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		billBtnPanel.add(billAddButton);
		billBtnPanel.add(billDeleteButton);
		
		panel2.setLayout(new BorderLayout() );
		panel2.add(billBtnPanel,BorderLayout.NORTH);
		panel2.add(contentPane,BorderLayout.CENTER);
		panel1.setLayout(new BorderLayout());
		panel1.add(patientInfoPanel,BorderLayout.NORTH);
		panel1.add(panel2,BorderLayout.CENTER);
			
		frame.getContentPane().add(topBtnPanel, BorderLayout.NORTH);
		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		
		//添加控件响应
		addButton.addActionListener(this);
		billAddButton.addActionListener(this);
		saveButton.addActionListener(this);
		
		//其他操作
		setAllChildrenEnable(frame.getContentPane(),false);
		setAllChildrenEnable(addButton,true);
		
	}
	
	private void setAllChildrenEnable(Container jp,boolean flag) {
		if(jp.getComponentCount()==0) {
			jp.setEnabled(flag);
			return;
		}
		for(int i=0;i<jp.getComponentCount();i++) {
			setAllChildrenEnable((Container)jp.getComponent(i),flag);
		}
		jp.setEnabled(flag);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == this.addButton) {
			setAllChildrenEnable(frame.getContentPane(),true);
		}else if(source == this.saveButton) {
			BillDTO billDTO = new BillDTO();
			billDTO.setPatName(this.patNameJTextField.getText());
			String patsex = this.patGenderJTextField.getText();
			if("男".equals(patsex)) {
				billDTO.setPatSex(0);
			}else if("女".equals(patsex)) {
				billDTO.setPatSex(1);
			}
			billDTO.setDocDepartment(this.departmentJTextField.getText());
			billDTO.setDocName(this.DoctorJTextField.getText());
			this.mainPresenter.saveBillInfo(billDTO);
		}else if(source == this.billAddButton) {
			DefaultTableModel tableModel = (DefaultTableModel)table_A.getModel();
			List<DocumentDetailDTO> list = new LinkedList();
			for(int i=0;i<tableModel.getRowCount()-1;i++) {
				DocumentDetailDTO docdel = new DocumentDetailDTO();
				docdel.setMedicineName(tableModel.getValueAt(i, 0).toString());
				docdel.setPrice(Double.valueOf(tableModel.getValueAt(i, 1).toString()));
				docdel.setNumber(Double.valueOf(tableModel.getValueAt(i, 2).toString()));
				docdel.setMoney(Double.valueOf(tableModel.getValueAt(i, 3).toString()));
				list.add(docdel);
			}
			this.mainPresenter.addDocumentDetail(list, this.departmentJTextField.getText(), this.DoctorJTextField.getText());
		}
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		DefaultTableModel tableModel = (DefaultTableModel)table_A.getModel();
		switch(e.getColumn()) {
		case 0: 
			
			String unitName = tableModel.getValueAt(e.getFirstRow(), e.getColumn()).toString();
			logger.info("用户编辑单元格坐标：("+e.getFirstRow()+" "+e.getColumn()+")，内容为：" + unitName);
			if(!"".equals(unitName) && unitName!=null) {
				tableModel.setValueAt("查询中...", e.getFirstRow(), 1);
				mainPresenter.getUnitPrice(unitName);
			}
			break;
		case 2:
			double num = Double.valueOf(tableModel.getValueAt(e.getFirstRow(),2).toString());
			double price = Double.valueOf(tableModel.getValueAt(e.getFirstRow(),1).toString());
			tableModel.setValueAt(num*price, e.getFirstRow(), 3);
			tableModel.addRow(new Object[4]);
			break;
		}
	}

	@Override
	public void updateUnitPrice(UnitPriceDTO unitPrice) {
		// TODO Auto-generated method stub
		TableModel tableModel = table_A.getModel();
		for(int i=0;i<tableModel.getRowCount();i++) {
			String tmpName = tableModel.getValueAt(i, 0).toString();
			if(unitPrice.getUnitName().equals(tmpName)) {
				tableModel.setValueAt(unitPrice.getUnitPrice(), i, 1);
			}
			break;
		}
	}

	@Override
	public void addDocument(DocumentDTO billDetail) {
		// TODO Auto-generated method stub
		DefaultTableModel tableModel = (DefaultTableModel)table_B.getModel();
		Object[] oj = new Object[]{billDetail.getDoctorDepartmentName(),billDetail.getDoctorName(),billDetail.getComplyName(),billDetail.getMoney()};
		tableModel.addRow(oj);
	}

	@Override
	public void actionSuccessed(String msg) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "提示信息", msg,JOptionPane.INFORMATION_MESSAGE);
		 
	}

}
