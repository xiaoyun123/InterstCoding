package com.zhang.util;

public class StringUtils {

	private static StringUtils utils;
	//content属性名称
	private static final String encode = "encode",type = "type",result = "result";
	
	public static StringUtils getInstance(){
		if(utils == null){
			utils = new StringUtils();
		}
		return utils;
	}

	public JsonContent getJsonContentData(String data){
		data = data.replace("\\", "");
		JsonContent entity = new JsonContent();
		try {
			//首先去掉无用xml内容
			int start = data.indexOf(">");//开始位置
			String reduceXml = data.substring(start + 1, data.length());
			//二步 分析 content 内容 ,用content内容去确定 是否存在 json数据
			int end = reduceXml.indexOf(">");//结束为止
			//String content = StringEscapeUtils.unescapeJava(reduceXml.substring(0, end));//得到content 内容
			String content = reduceXml.substring(0, end).replaceAll("\\\\", "");//得到content 内容   去掉了反斜杠
			//<content encode="0" type="0" result="0"
			//获得encode
			String encodeValue = content.substring(content.indexOf(encode)  + encode.length()+1, content.indexOf(type));
			//获得type值  -- 未启用
			//String typeValue = content.substring(content.indexOf(type) + type.length()+1, content.indexOf(result));
			//获得result值
			String resultValue = content.substring(content.indexOf(result)+result.length()+1, content.length());
			//将其转换成数字
			int resultV = Integer.parseInt(resultValue.replaceAll("\"", "").replaceAll("\\D+","").replaceAll("\r", "").replaceAll("\n", "").trim(),10);
			int encodeV = Integer.parseInt(encodeValue.replaceAll("\"", "").replaceAll("\\D+","").replaceAll("\r", "").replaceAll("\n", "").trim(),10);
			String resultString = "";
			entity.setSucceed(false);
			//三步 根据结果 返回 所要的 json 数据
			switch (resultV) {
			case 200:// 成功
				entity.setSucceed(true);
				switch (encodeV) {
				case 0://未编码
					//String jsonResult = StringEscapeUtils.unescapeJava(getTargetString(data));
					entity.setResult(getTargetString(data));
					return entity;//返回正常的数据
				case 1://BASE64编码
					resultString = "BASE64编码";
					break;
				case 2://GZIP编码
//					resultString = GZipUtil.unGZIPString(data);//获得到解压数据
					entity.setResult(getTargetString(resultString));
					break;
				case 3://BASE64编码+GZIP编码
//				resultString = "BASE64编码+GZIP编码";
					entity.setResult(getTargetString(data));
					return entity;//返回正常的数据
				}
				break;
//			case 1:// 无效的参数
//				resultString = "无效参数";
//				break;
//			case 2:// 访问数据库失败
//				resultString = "访问数据库失败";
//				break;
//			case 3:// 数据库记录重复
//				resultString = "数据库记录重复";
//				break;
//			case 4://没有权限
//				resultString = "没有权限";
//				break;
//			default:// 自定义错误从101开始
//				resultString = "自定义错误"+resultV;
			default:// 自定义错误从101开始
				resultString = getErrTitle(data);
				break;
			}
			entity.setResult(resultString);
		} catch (Exception e) {
			e.printStackTrace();
			entity.setSucceed(false);
			entity.setResult("服务器连接失败");
		}
		return entity;
	}
	
	/**
	 * 获得目标字符串
	 * @param string
	 * @return
	 */
	public static String getTargetString(String string){
		String temp = "";
		try {
			//如果存返回正确则取到正确想要的数据
			int start = string.indexOf("{");//开始位置
			int end = string.lastIndexOf("}");//结束为止
			temp = string.substring(start, end+1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	
	private String getErrTitle(String string){
		String temp = "";
		try {
			//如果存返回正确则取到正确想要的数据
			int start = string.indexOf(":");//开始位置
			int end = string.lastIndexOf("}");//结束为止
			temp = string.substring(start+1, end).replace("\"", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
	
}
