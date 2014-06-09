package com.namoo.chat.client.actions;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ApplicationWindow;

public class ExitAction extends Action {

	private ApplicationWindow window;
	
	public ExitAction(ApplicationWindow window) {
		//
		super("&Exit");
		this.window = window;
		
		try {
			setImageDescriptor(ImageDescriptor.createFromURL(new URL("file:icons/exit.ico")));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// 
		window.close();
	}
}
