package com.namoo.chat.client;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
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
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.namoo.chat.client.actions.ConnectAction;
import com.namoo.chat.client.actions.DisconnectAction;
import com.namoo.chat.client.actions.ExitAction;
import com.namoo.chat.client.socket.ChatClient;
import com.namoo.chat.core.Member;
import com.namoo.chat.core.Message;
import com.namoo.chat.core.MessageType;

public class NamooChatClient extends ApplicationWindow implements IChatRoom {
	//
	private Text inputMsgText;
	private List chatMsgList;
	private List participantList;
	private Button sendBtn;
	
	private ChatClient chatClient;
	
	private ConnectAction connectAction;
	private DisconnectAction disconnectAction;
	private ExitAction exitAction;

	/**
	 * Create the application window.
	 */
	public NamooChatClient() {
		super(null);
		chatClient = new ChatClient();
		createActions();
		
		addMenuBar();
		addToolBar(SWT.FLAT);
		enableConnectionMenu();
	}

	private void createActions() {
		connectAction = new ConnectAction(chatClient, this);
		disconnectAction = new DisconnectAction(chatClient, this);
		exitAction = new ExitAction(this);
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		
		// attach widget
		Label msgLabel = new Label(container, SWT.NONE);
		msgLabel.setText("Messages");
		
		Label participantsLabel = new Label(container, SWT.NONE);
		participantsLabel.setText("Participants");
		
		chatMsgList = new List(container, SWT.BORDER | SWT.MULTI);
		GridData gd_chatMsgList = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_chatMsgList.heightHint = 206;
		gd_chatMsgList.widthHint = 423;
		chatMsgList.setLayoutData(gd_chatMsgList);
		
		participantList = new List(container, SWT.BORDER);
		GridData gd_participantList = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_participantList.widthHint = 97;
		participantList.setLayoutData(gd_participantList);
		
		inputMsgText = new Text(container, SWT.BORDER);
		GridData gd_inputMsgText = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_inputMsgText.widthHint = 409;
		inputMsgText.setLayoutData(gd_inputMsgText);
		
		sendBtn = new Button(container, SWT.NONE);
		sendBtn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		sendBtn.setText("Send");

		// add event listener
		sendBtn.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// 
				sendMessage();
			}
		});
		
		inputMsgText.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// 
				if (e.character == SWT.CR) {
					sendMessage();
				}
			}
		});
		
		participantList.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// 
				int idx = participantList.getSelectionIndex();
				String selectNickname = participantList.getItem(idx);
				
				inputMsgText.setText("@"+selectNickname + " ");
				inputMsgText.setFocus();
				inputMsgText.setSelection(inputMsgText.getText().length());
			}
		});
		
		return container;
	}
	
	@Override
	protected boolean canHandleShellCloseEvent() {
		// 
		chatClient.disconnect();
		return super.canHandleShellCloseEvent();
	}

	private void sendMessage() {
		//
		String inputMsg = inputMsgText.getText();
		if (inputMsg.length() > 0) {
			
			Message message = null;
			
			if (inputMsg.startsWith("@")) { // 귓속말
				String receiver = inputMsg.substring(1, inputMsg.indexOf(" "));
				String body = inputMsg.substring(inputMsg.indexOf(" ") + 1);
				if (body != null && body.length() > 0) {
					message = new Message(MessageType.DirectMessage);
					message.setReceiver(receiver);
					message.setBody(body);
				}
			} else { // 단문메시지
				message = new Message(MessageType.SimpleMessage);
				message.setBody(inputMsgText.getText());
			}
			
			chatClient.sendMessage(message);
			
			inputMsgText.setText("");
		}
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			NamooChatClient window = new NamooChatClient();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected MenuManager createMenuManager() {
		// 
		MenuManager main = new MenuManager();
		MenuManager chatMenu = new MenuManager("&Menu");
		main.add(chatMenu);
		chatMenu.add(connectAction);
		chatMenu.add(disconnectAction);
		chatMenu.add(exitAction);
		return main;
	}

	@Override
	protected ToolBarManager createToolBarManager(int style) {
		// 
		ToolBarManager manager = new ToolBarManager(style);
		manager.add(connectAction);
		manager.add(disconnectAction);
		manager.add(exitAction);
		
		return manager;
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("NamooChat ver 0.1");
	}
	
	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(571, 356);
	}
	
	//--------------------------------------------------------------------------
	// IChatRoom implementation

	@Override
	public void appendMessage(final Message message) {
		//
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				//
				String msg = "";
				if (message.getType() == MessageType.DirectMessage) {
					msg = "DM ";
				}
				if (message.getSender() != null) {
					msg += "["+message.getSender()+"] ";
				}
				msg += message.getBody();
				chatMsgList.add(msg);
			}
		});
	}

	@Override
	public void refreshParticipants(final java.util.List<Member> members) {
		// 
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				// 
				participantList.removeAll();
				
				if (members != null && !members.isEmpty()) {
					for (Member member : members) {
						participantList.add(member.getNickName());
					}
				}
			}
		});
	}

	@Override
	public void enableConnectionMenu() {
		// 
		connectAction.setEnabled(true);
		disconnectAction.setEnabled(false);
		getMenuBarManager().update(true);
	}

	@Override
	public void disableConnectionMenu() {
		// 
		connectAction.setEnabled(false);
		disconnectAction.setEnabled(true);
		getMenuBarManager().update(true);
	}
}
