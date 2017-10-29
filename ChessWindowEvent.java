package com.Definer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/*
 * 响应退出窗口
 */
class ChessWindowEvent extends WindowAdapter{
	public void windowClosing(WindowEvent e){
		System.exit(0);
	}
	public ChessWindowEvent() {
	}
}
