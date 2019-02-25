package syncinfo.persistence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ExportCertXLS {
	
	public static HSSFWorkbook CreateCertExcel(List<?> dataset,Map<String,String> map) throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet(map.get("title"));
			// 设置单元格的宽度(0:表示第一行的第一个单元格，1：第一行的第二个单元格)
			sheet.setColumnWidth((short) 0, 3000);
			sheet.setColumnWidth((short) 1, 10000);
			sheet.setColumnWidth((short) 2, 3000);
			sheet.setColumnWidth((short) 3, 3000);
			sheet.setColumnWidth((short) 4, 4000);
			sheet.setColumnWidth((short) 5, 5000);
			sheet.setColumnWidth((short) 6, 5000);
			sheet.setColumnWidth((short) 7, 5000);
	
			// 创建一个单元格，从0开始
			HSSFRow row = sheet.createRow((short) 0);
			// 构造一个数组设置第一行之后的单元格
			HSSFCell cell[] = new HSSFCell[10];
			// { "编号","操作员证书SN","用户ID","证书ID","创建时间","日志类型", "申请类型", "操作者类型",
			// "操作系统", "浏览器","IP", "执行时间(mm)","详细描述","请求参数","日志结果" };
			for (int i = 0; i < 10; i++) {
				cell[i] = row.createCell(i);
			}
			cell[0].setCellValue(map.get("name"));
			cell[1].setCellValue(map.get("dn"));
			cell[2].setCellValue(map.get("status"));
			cell[3].setCellValue(map.get("type"));
			cell[4].setCellValue(map.get("valdaylen"));
			cell[5].setCellValue(map.get("starttime"));
			cell[6].setCellValue(map.get("endtime"));
				if (dataset != null && dataset.size() > 0) {
		
//					for (int i = 0; i < dataset.size(); i++) {
//						if((dataset.get(i)) instanceof AdminCert){
//							AdminCert admincert = (AdminCert) dataset.get(i);
//							HSSFRow dataRow = sheet.createRow(i + 1);
//							HSSFCell data[] = new HSSFCell[10];
//							for (int j = 0; j < 10; j++) {
//								data[j] = dataRow.createCell(j);
//							}
//							data[0].setCellValue(admincert.getAdmin().getAdminname());
//							data[1].setCellValue(admincert.getSubjectdn());
//							data[2].setCellValue(certStatus(admincert.getCertstatus()));
//							data[3].setCellValue(certType(admincert.getCerttype()));
//							data[4].setCellValue(admincert.getValdaylen());
//							if(null!=admincert.getStarttime()&& !"".equals(admincert.getStarttime())){
//								data[5].setCellValue(sdf.format((Date)admincert.getStarttime()));
//							}else{
//								data[5].setCellValue("--");
//							}
//							if(null!=admincert.getEndtime()&& !"".equals(admincert.getEndtime())){
//								data[6].setCellValue(sdf.format((Date)admincert.getEndtime()));
//							}else{
//								data[6].setCellValue("--");
//							}
//							
//						}else if((dataset.get(i)) instanceof Cert){
//							Cert cert = (Cert) dataset.get(i);
//							HSSFRow dataRow = sheet.createRow(i + 1);
//							HSSFCell data[] = new HSSFCell[10];
//							for (int j = 0; j < 10; j++) {
//								data[j] = dataRow.createCell(j);
//							}
//							data[0].setCellValue(cert.getPerson().getPersonname());
//							data[1].setCellValue(cert.getSubjectdn());
//							data[2].setCellValue(certStatus(cert.getCertstatus()));
//							data[3].setCellValue(certType(cert.getCerttype()));
//							data[4].setCellValue(cert.getValdaylen());
//							if(null!=cert.getStarttime()&& !"".equals(cert.getStarttime())){
//								data[5].setCellValue(sdf.format(cert.getStarttime()));
//							}else{
//								data[5].setCellValue("--");
//							}
//							if(null!=cert.getEndtime()&& !"".equals(cert.getEndtime())){
//								data[6].setCellValue(sdf.format(cert.getEndtime()));
//							}else{
//								data[6].setCellValue("--");
//							}
							
//						}
//					}
				}
	
		return workBook;
	}
	
}