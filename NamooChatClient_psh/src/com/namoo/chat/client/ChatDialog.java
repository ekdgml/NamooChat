package com.namoo.chat.client;

import java.util.List;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.namoo.chat.core.Member;
import com.namoo.chat.core.Message;
import com.namoo.chat.core.MessageType;

public class ChatDialog extends Dialog implements IChatMessage {
	private String nickname;
	private Text inputTxt;
	private Text showTxt;
	private Table table;
	private TableViewer tableViewer;
	private ClientSocket clientSocket;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public ChatDialog(Shell parentShell, String nickname,
			ClientSocket clientSocket) {
		super(parentShell);
		this.nickname = nickname;
		this.clientSocket = clientSocket;
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(4, false));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		Label responseLbl = new Label(container, SWT.CENTER);
		responseLbl.setAlignment(SWT.CENTER);
		responseLbl.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 2, 1));
		responseLbl.setText("\uBAA9\uB85D\uC870\uD68C");

		showTxt = new Text(container, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_showTxt = new GridData(SWT.FILL, SWT.CENTER, true, true, 2,
				17);
		gd_showTxt.heightHint = 298;
		showTxt.setLayoutData(gd_showTxt);
		showTxt.setText("내 닉네임은 " + nickname + "입니다." + "\n");

		Button complainBtn = new Button(container, SWT.NONE);
		complainBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		complainBtn.setText("\uC2E0\uACE0");

		Button renewBtn = new Button(container, SWT.NONE);
		renewBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		renewBtn.setText("\uC0C8\uB85C\uACE0\uCE68");

		tableViewer = new TableViewer(container, SWT.BORDER
				| SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 18);
		gd_table.widthHint = 107;
		gd_table.heightHint = 224;
		table.setLayoutData(gd_table);

		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new MemberListLabelProvider());
		
		renewBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Message message = new Message(MessageType.RequestList, nickname);
				clientSocket.sendMessage(message);
				
			}
		});
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tableColumn = tableViewerColumn.getColumn();
		tableColumn.setAlignment(SWT.CENTER);
		tableColumn.setText("\uB2C9\uB124\uC784");
		tableColumn.setWidth(225);
		

		

		inputTxt = new Text(container, SWT.BORDER);
		GridData gd_inputTxt = new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1);
		gd_inputTxt.heightHint = 66;
		gd_inputTxt.widthHint = 180;
		inputTxt.setLayoutData(gd_inputTxt);

		Button sendBtn = new Button(container, SWT.NONE);
		sendBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 보내기 버튼눌렀을 때
				Message directMsg = new Message(MessageType.DirectMessage,
						nickname);
				String[] text = inputTxt.getText().split(" ");
				String msg = text[0];
				if (msg.startsWith("@")) {
					String[] fullName = msg.split("@");
					String receivernick = fullName[1];
					directMsg.setReceiver(receivernick);
					directMsg.setBody(inputTxt.getText().substring(text[0].length()+1));
					clientSocket.sendMessage(directMsg);
				} else {
					Message simpleMsg = new Message(MessageType.SimpleMessage,
							nickname);
					simpleMsg.setBody(inputTxt.getText());
					clientSocket.sendMessage(simpleMsg);
					inputTxt.setText("");
				}

			}
		});
		sendBtn.setText("\uBCF4\uB0B4\uAE30");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		// 스레드생성
		Thread thread = new Thread(new MessageRieceiver(clientSocket, this));
		thread.start();

		return container;
	}

	@Override
	public void appendMessage(Message message) {
		//
		final String msg = "[" + message.getSender() + "]" + message.getBody();
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				//
				showTxt.append(msg + "\n");
			}
		});
	}
	
	@Override
	public void refreshList(final List<Member> memberList) {
		// 
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				// 
				tableViewer.setInput(memberList.toArray());
			}
		});
		
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("채팅방");
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(629, 437);
	}


}
