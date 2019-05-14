package com.exampl.test;

import com.exampl.action.DownFile;
import com.exampl.bean.SiteInfo;

import java.util.Scanner;

public class DownTest {
	
	public static void main(String args[]){												//C:/Users/aiwoy/Desktop
		SiteInfo siteInfo = new SiteInfo("https://mirrors.aliyun.com/centos/7.6.1810/isos/x86_64/CentOS-7-x86_64-DVD-1810.iso", "D:/", "testsuccess.iso", 3);
		
		DownFile downFile = new DownFile(siteInfo);

		downFile.startDown();

		downFile.saveInfo();
	}
}
