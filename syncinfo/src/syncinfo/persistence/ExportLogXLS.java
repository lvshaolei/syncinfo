package syncinfo.persistence;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import syncinfo.model.SysLog;

public class ExportLogXLS {
	
	public static HSSFWorkbook CreateExcel(List<SysLog> dataset) throws Exception {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet("日志导出表");
			// 设置单元格的宽度(0:表示第一行的第一个单元格，1：第一行的第二个单元格)
			sheet.setColumnWidth((short) 0, 3500);
			sheet.setColumnWidth((short) 1, 3000);
			sheet.setColumnWidth((short) 2, 3000);
			sheet.setColumnWidth((short) 3, 3000);
			sheet.setColumnWidth((short) 4, 3000);
			sheet.setColumnWidth((short) 5, 3000);
			sheet.setColumnWidth((short) 6, 3000);
			sheet.setColumnWidth((short) 7, 6000);
			sheet.setColumnWidth((short) 8, 5000);
			sheet.setColumnWidth((short) 9, 5000);
	
			// 创建一个单元格，从0开始
			HSSFRow row = sheet.createRow((short) 0);
			// 构造一个数组设置第一行之后的单元格
			HSSFCell cell[] = new HSSFCell[10];
			// { "编号","操作员证书SN","用户ID","证书ID","创建时间","日志类型", "申请类型", "操作者类型",
			// "操作系统", "浏览器","IP", "执行时间(mm)","详细描述","请求参数","日志结果" };
			for (int i = 0; i < 10; i++) {
				cell[i] = row.createCell(i);
			}
			cell[0].setCellValue("操作员证书SN");
			cell[1].setCellValue("操作者角色");
			cell[2].setCellValue("日志类型");
			cell[3].setCellValue("申请类型");
			cell[4].setCellValue("IP地址");
			cell[5].setCellValue("操作系统");
			cell[6].setCellValue("浏览器");
			cell[7].setCellValue("详细描述");
			cell[8].setCellValue("创建时间");
	
			if (dataset != null && dataset.size() > 0) {
	
//				for (int i = 0; i < dataset.size(); i++) {
//					SysLog log = (SysLog) dataset.get(i);
//					HSSFRow dataRow = sheet.createRow(i + 1);
//					HSSFCell data[] = new HSSFCell[10];
//					for (int j = 0; j < 10; j++) {
//						data[j] = dataRow.createCell(j);
//					}
//					data[0].setCellValue(log.getOperationcertsn());
//					data[1].setCellValue(operationtype(log.getOperationtype()));
//					data[2].setCellValue(logType((log.getLogtype()).doubleValue()));
//					if (log.getApplytype() == null) {
//						data[3].setCellValue("其他类型");
//					} else {
//						data[3].setCellValue(applyType((log.getApplytype()).doubleValue()));
//					}
//					
//					data[4].setCellValue(log.getIp());
//					data[5].setCellValue(log.getOs());
//					data[6].setCellValue(log.getBrowser());
//					data[7].setCellValue(log.getDescription());
//					data[8].setCellValue(sdf.format(log.getCreatedate()));
//				}
			}
		
		return workBook;
	}
	//操作者角色
	private static String operationtype(String type){
		String operationtype="";
		if("SA".equals(type))
			operationtype="管理员";
		else if("BO".equals(type))
			operationtype="操作员";
		else if("AA".equals(type))
			operationtype="审计管理员";
		else if("AO".equals(type))
			operationtype="审计操作员";
		else
			operationtype="其他角色";
		return operationtype;
	}
	//日志类型
	private static String logType(double type){
		String logType="";
		if(1==type)
			logType="登录日志";
		else if(2==type)
			logType="证书日志";
		else if(3==type)
			logType="操作日志";
		else if(9==type)
			logType="未知错误";
		else
			logType="其他类型";
		return logType;
	}
	//申请类型
	private static String applyType(double type){
		String applyType="";
		if(1==type)
			applyType="申请用户";
		else if(2==type)
			applyType="更新用户";
		else if(3==type)
			applyType= "申请证书";
		else if(4==type)
			applyType= "证书更新申请";
		else if(5==type)
			applyType= "证书作废申请";
		else if(6==type)
			applyType= "证书下载申请";
		else if(8==type)
			applyType= "证书审核";
		else if(9==type)
			applyType= "删除用户";
		else if(10==type)
			applyType= "日志操作";
		else
			applyType="其他类型";
		return applyType;
	}
}