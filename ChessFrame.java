package com.Definer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/*
 * 用类ChessFrame 创建五子棋游戏主窗体和菜单
 */
class ChessFrame extends JFrame implements ActionListener{
	public static boolean isComputer =true;
	private boolean checkComputer =true;
	private  int width,height;
	private ChessModel cm;
	private MainPanel mp;
	/*
	 * 构造五子棋游戏的主窗体
	 */
	public ChessFrame(){
		this.setTitle("五子棋游戏 v1.17.10.28");
		cm = new ChessModel(1);
		mp = new MainPanel(cm);
		Container con = this.getContentPane();
		con.add(mp,"Center");
		this.setResizable(false);
		this.addWindowListener(new ChessWindowEvent());
		MapSize(14,14);
		JMenuBar mbar = new JMenuBar();
		this.setJMenuBar(mbar);
		JMenu gameMenu = new JMenu("开始游戏");
		mbar.add(makeMenu(gameMenu,new Object[]{"开局",null,
				"退出",},this));
		JMenu modeMenu = new JMenu("模式");
		mbar.add(makeMenu(modeMenu,new Object[]{"人机对战",null,
				"人人对战"},this));
		JMenu sizeMenu = new JMenu("棋盘");
		mbar.add(makeMenu(sizeMenu,new Object[]{"标准棋盘",null,
				"扩大棋盘"},this));
		JMenu lookMenu = new JMenu("外观");
		mbar.add(makeMenu(lookMenu,new Object[]{"类型一",null,
				"类型二"},this));
		JMenu helpMenu = new JMenu("帮助");
		mbar.add(makeMenu(helpMenu,new Object[]{"游戏说明",null,"版本更新"},this));
	}
	/*
	 * 构造五子棋游戏的主菜单
	 */
	public JMenu makeMenu(Object parent,Object items[],Object target){
		JMenu m = null;
		if(parent instanceof JMenu){
			m = (JMenu)parent;
		}else if(parent instanceof String){
			m = new JMenu((String)parent);
		}else{
			return null;
		}
		for(int i=0;i<items.length;i++){
			if(items[i]==null){
				m.addSeparator();
			}else{
				m.add(makeMenuItem(items[i],target));
			}
		}
		return m;
	}
	/*
	 * 构造五子棋游戏的菜单项
	 */
	public JMenuItem makeMenuItem(Object item,Object target){
		JMenuItem r = null;
		if(item instanceof String){
			r = new JMenuItem((String)item);
		}else if(item instanceof JMenuItem){
			r = (JMenuItem)item;
		}else{
			return null;
		}
		if(target instanceof ActionListener){
			r.addActionListener((ActionListener)target);
		}
		return r;
	}
	/*
	 * 构造五子棋游戏的单选按钮式菜单项
	 */
	public JRadioButtonMenuItem makeRadioButtonMenuItem(Object item,
			Object target) {
		JRadioButtonMenuItem r = null;
		if(item instanceof String){
			r = new JRadioButtonMenuItem((String)item);
		}else if(item instanceof JRadioButtonMenuItem){
			r = (JRadioButtonMenuItem)item;
		}else{
			return null;
		}
		if(target instanceof ActionListener){
			r.addActionListener((ActionListener)target);
		}
		return r;
	}
	public void MapSize(int w,int h){
		setSize(w*24,h*27);
		if(this.checkComputer){
			ChessFrame.isComputer = true;
		}else{
			ChessFrame.isComputer = false;
		}
		mp.setModel(cm);
		mp.repaint();
	}
	public boolean getiscomputer(){
		return ChessFrame.isComputer;
	}
	public void restart(){
		int modeChess = cm.getModeChess();
		if(modeChess<=2 && modeChess>=0){
			cm = new ChessModel(modeChess);
			MapSize(cm.getWidth(),cm.getHeight());
		}
	}
	public void actionPerformed(ActionEvent e){
		String arg = e.getActionCommand();
		try{
			
			if(arg.equals("类型二")){
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			}else {
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			}
			SwingUtilities.updateComponentTreeUI(this);
		}catch(Exception ee){ }
		if(arg.equals("标准棋盘")){
			this.width=14;
			this.height=14;
			cm=new ChessModel(1);
			MapSize(this.width, this.height);
			SwingUtilities.updateComponentTreeUI(this);
		}
		if(arg.equals("扩大棋盘")){
			this.width=22;
			this.height=22;
			cm=new ChessModel(2);
			MapSize(this.width, this.height);
			SwingUtilities.updateComponentTreeUI(this);
		}
		if(arg.equals("人机对战")){
			this.checkComputer=true;
			ChessFrame.isComputer=true;
			cm=new ChessModel(cm.getModeChess());
			MapSize(cm.getWidth(), cm.getHeight());
			SwingUtilities.updateComponentTreeUI(this);
		}
		if(arg.equals("人人对战")){
			this.checkComputer=false;
			ChessFrame.isComputer=false;
			cm=new ChessModel(cm.getModeChess());
			MapSize(cm.getWidth(), cm.getHeight());
			SwingUtilities.updateComponentTreeUI(this);
		}
		if(arg.equals("开局")){
			restart();
		}
		if(arg.equals("游戏说明")){
			JOptionPane.showMessageDialog(null, "五子棋游戏：\n"
			+ "\t\t1、黑子先手\n \t\t2、最先在棋盘横向、竖向、斜向形成"
			+ "连续的相同色五个棋子的一方为胜。","游戏说明",JOptionPane.PLAIN_MESSAGE);
		}
		if(arg.equals("版本更新")){
			JOptionPane.showMessageDialog(null, "现有版本： 第一版，参考网上资源并加以修改。\n"
					+ "实现功能： 人机对战、人人对战，可修改棋盘大小、棋盘外观，但是AI算法较为简单。\n"
					+ "下次更新： 时间不定。如有兴趣，请关注。。。\n",
					"版本更新",JOptionPane.PLAIN_MESSAGE);
		}
		if(arg.equals("退出")){
			System.exit(0);
		}
	}
}
