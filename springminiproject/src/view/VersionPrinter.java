package view;

import org.springframework.beans.factory.annotation.Autowired;

public class VersionPrinter {
	private int majorVersion;
	private int minorVersion;

	@Autowired
	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}

	@Autowired
	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}

	public void print() {
		System.out.printf("�� ���α׷��� ������ %d.%d�Դϴ�.\n\n", majorVersion, minorVersion);
	}
}
