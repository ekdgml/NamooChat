package com.namoo.chat.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.namoo.chat.core.Message;
import com.namoo.chat.core.MessageType;

public class ChatMainWindow extends ApplicationWindow {

	private Text nickNameText;
	private ClientSocket clientSocket;

	public ChatMainWindow() {
		super(null);
	}

	@Override
	protected Control createContents(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(3, false));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		{
			Label label = new Label(container, SWT.NONE);
			label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
					false, 1, 1));
			label.setText("\uB2C9\uB124\uC784\uC744 \uC785\uB825\uD558\uC138\uC694.");
		}
		{
			nickNameText = new Text(container, SWT.BORDER);
			GridData gd_nickInBtn = new GridData(SWT.FILL, SWT.CENTER, true,
					false, 2, 1);
			gd_nickInBtn.widthHint = 128;
			nickNameText.setLayoutData(gd_nickInBtn);
		}
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		{
			Button loginBtn = new Button(container, SWT.NONE);
			loginBtn.addSelectionListener(new SelectionAdapter() {
				// 로그인 눌렀을 때 그 때 접속하는 거
				@Override
				public void widgetSelected(SelectionEvent event) {

					try {
						Socket socket = new Socket("192.168.0.16", 9090);
						clientSocket = new ClientSocket(socket);
						String nickname = nickNameText.getText();
						Message joinMsg = new Message(MessageType.RequestJoin,
								nickname);
						clientSocket.sendMessage(joinMsg);

						Message receivedMsg = clientSocket.readMessage();
						if (receivedMsg.getType() == MessageType.AcceptJoin) {
							System.out.println("AcceptJoin");
							ChatDialog dialog = new ChatDialog(container
									.getShell(), nickname, clientSocket);
							dialog.open();
						} else if (receivedMsg.getType() == MessageType.RejectJoin) {
							System.out.println("Reject!");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			});
			GridData gd_loginBtn = new GridData(SWT.LEFT, SWT.CENTER, false,
					false, 1, 1);
			gd_loginBtn.widthHint = 62;
			loginBtn.setLayoutData(gd_loginBtn);
			loginBtn.setText("\uC811\uC18D\uD558\uAE30");
		}
		{
			Button exitBtn = new Button(container, SWT.NONE);
			exitBtn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					System.exit(0);
				}
			});
			GridData gd_exitBtn = new GridData(SWT.LEFT, SWT.CENTER, false,
					false, 1, 1);
			gd_exitBtn.widthHint = 70;
			exitBtn.setLayoutData(gd_exitBtn);
			exitBtn.setText("\uB098\uAC00\uAE30");
		}

		return container;
	}

	public static void main(String args[]) throws UnknownHostException,
			IOException {
		try {
			ChatMainWindow window = new ChatMainWindow();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("채팅방들어가기");
	}

	@Override
	protected Point getInitialSize() {
		return new Point(301, 216);
	}

}
