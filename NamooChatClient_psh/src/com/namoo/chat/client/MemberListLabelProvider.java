package com.namoo.chat.client;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import com.namoo.chat.core.Member;


public class MemberListLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		//
		return null;
	}

	//������ ����ŭ columnIndex�� �����ǰ�!
	@Override
	public String getColumnText(Object element, int columnIndex) {
		//
		Member member = (Member) element;
		switch (columnIndex) {
		case 0:
			return member.getNickName();
		default:
			return "unknown " + columnIndex;
		}
	}

}
