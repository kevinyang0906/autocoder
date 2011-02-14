package com.baidu.autocoder.component;

import java.util.Map;

/**
 * 文件组件.
 * TODO 尚未完成
 * 
 * @author GuoLin
 *
 */
public class FileComponent extends Component {
	
	private final String targetFilename;
	
	private final String targetPath;

	public FileComponent(String type, String template, String targetFilename, String targetPath) {
		super(type, template);
		this.targetFilename = targetFilename;
		this.targetPath = targetPath;
	}

	@Override
	public String getTargetFilename() {
		return targetFilename;
	}

	@Override
	public String getTargetPath() {
		return targetPath;
	}

	@Override
	public FileComponent buildCalculatedComponent(Map<String, ?> model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, ?> toMap() {
		// TODO Auto-generated method stub
		return null;
	}

}
