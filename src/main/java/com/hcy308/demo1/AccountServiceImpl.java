package com.hcy308.demo1;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class AccountServiceImpl implements AccountService {
	//ע��DAO��
	private AccountDao accountDao;
	

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	//ע���������ģ��
	private TransactionTemplate transactionTemplate;
	
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 *  ע��java8���Ѿ�û�������ڲ���;ֲ��ڲ���ֻ�ܷ���final�����������ˣ� 
	 *  �����ڲ�������ⲿ�ľֲ�����ʱ�ñ���Ϊʲô������final�ģ�
	 *  �ܽ�һ�£����ǻᵼ�¾ֲ���������Ϊһ����ʽ�ĳ�Ա������
	 *  ������������Ҫ���ݽ���final�ؼ��֣�javaϣ�����Ǳ�֤�ڲ�ʵ�ֺ����ڱ��ֵ�һ����
	 *  
	 */
	@Override
	public void transfer( String out,  String in,  Double money) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				accountDao.outMoney(out, money);
				int i = 1 / 0;
				accountDao.inMoney(in, money);
			}
		});
	}

}
