package com.helloweenvsfei.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class ApplicationConstants {

	// �Ҧ��� Session
	public static Map<String, HttpSession> SESSION_MAP = new HashMap<String, HttpSession>();

	// �ثe�n�J���ϥΪ��`��
	public static int CURRENT_LOGIN_COUNT = 0;

	// ���v�X���`��
	public static int TOTAL_HISTORY_COUNT = 0;

	// �A�Ⱦ��Ұʮɶ�
	public static Date START_DATE = new Date();

	// �̰��b�u�ɶ�
	public static Date MAX_ONLINE_COUNT_DATE = new Date();

	// �̰��b�u�H��
	public static int MAX_ONLINE_COUNT = 0;
}
