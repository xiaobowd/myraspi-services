package com.xiaobowd.mysite.service.impl;

import com.xiaobowd.mysite.Constants.TransactionConstant;
import com.xiaobowd.mysite.bean.AlipayTransactionRecordVO;
import com.xiaobowd.mysite.exception.MyException;
import com.xiaobowd.mysite.mapper.AlipayTransactionRecordVOMapper;
import com.xiaobowd.mysite.service.ITransactionService;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private AlipayTransactionRecordVOMapper alipayTransactionRecordVOMapper;

    @Override
    @Transactional(readOnly = false, rollbackFor = MyException.class)
    public long importAlipayTransactionRecords(String fileName, MultipartFile file) throws MyException {

        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new MyException("上传文件格式不正确");
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");

        try {
            boolean isExcel2003 = true;
            if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
                isExcel2003 = false;
            }

            InputStream is = file.getInputStream();


            Workbook workbook = null;
            if (isExcel2003) {
                workbook = new HSSFWorkbook(is);
            } else {
                workbook = new XSSFWorkbook(is);
            }
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                throw new MyException("Excel文件中不存在sheet");
            }
            int len = sheet.getLastRowNum();

            List<AlipayTransactionRecordVO> recordVOS = new ArrayList<>();

            for (int r = 1; r <= len; r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                AlipayTransactionRecordVO recordVO = new AlipayTransactionRecordVO();
                if (null != row.getCell(0)) {
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    recordVO.setTransactionId(row.getCell(0).getStringCellValue().trim());
                }
                log.info(1);
                if (null != row.getCell(1)) {
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    recordVO.setBusinessId(row.getCell(1).getStringCellValue().trim());
                }
                if (null != row.getCell(2)) {
                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                    recordVO.setTransactionCreateDate(dateFormat.parse(row.getCell(2).getStringCellValue()));
                }
                log.info(2);
                if (null != row.getCell(3)) {
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                    recordVO.setPayedDate(dateFormat.parse(row.getCell(3).getStringCellValue()));
                }
                if (null != row.getCell(4)) {
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                    recordVO.setLastUpdateDate(dateFormat.parse(row.getCell(4).getStringCellValue()));
                }
                if (null != row.getCell(5)) {
                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                    recordVO.setTransactionSource(row.getCell(5).getStringCellValue().trim());
                }
                log.info(5);
                if (null != row.getCell(6)) {
                    row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                    recordVO.setTransactionType(row.getCell(6).getStringCellValue().trim());
                }
                if (null != row.getCell(7)) {
                    row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                    recordVO.setTransactionTarget(row.getCell(7).getStringCellValue().trim());
                }
                if (null != row.getCell(8)) {
                    row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                    recordVO.setGoodName(row.getCell(8).getStringCellValue().trim());
                }
                log.info(8);
                if (null != row.getCell(9)) {
                    row.getCell(9).setCellType(Cell.CELL_TYPE_NUMERIC);
                    recordVO.setAmount(row.getCell(9).getNumericCellValue());
                }

                Integer transactionDirection = null;
                String stringCellValue = null;
                if (null != row.getCell(10)) {
                    row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                    stringCellValue = row.getCell(10).getStringCellValue().trim();
                }
                if (TransactionConstant.TRANSACTION_DIRECTION_IN.equals(stringCellValue)) {
                    transactionDirection = 1;
                } else if (TransactionConstant.TRANSACTION_DIRECTION_OUT.equals(stringCellValue)) {
                    transactionDirection = 0;
                }
                recordVO.setTransactionDirection(transactionDirection);
                if (null != row.getCell(11)) {
                    row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                    recordVO.setTransactionStatus(row.getCell(11).getStringCellValue().trim());
                }
                if (null != row.getCell(12)) {
                    row.getCell(12).setCellType(Cell.CELL_TYPE_NUMERIC);
                    recordVO.setServiceFee(row.getCell(12).getNumericCellValue());
                }
                if (null != row.getCell(13)) {
                    row.getCell(13).setCellType(Cell.CELL_TYPE_NUMERIC);
                    recordVO.setSuccessfulRefund(row.getCell(13).getNumericCellValue());
                }
                if (null != row.getCell(14)) {
                    row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                    recordVO.setRemark(row.getCell(14).getStringCellValue().trim());
                }
                String fundStatusStr = null;
                if (null != row.getCell(15)) {
                    row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
                    fundStatusStr = row.getCell(15).getStringCellValue().trim();
                }

                Integer fundStatus = null;
                if ("已收入".equals(fundStatusStr)) {
                    fundStatus = 1;
                } else if ("已支出".equals(fundStatusStr)) {
                    fundStatus = 0;
                } else if ("资金转移".equals(fundStatusStr)) {
                    fundStatus = 2;
                }
                recordVO.setFundStatus(fundStatus);

                recordVOS.add(recordVO);
                log.info("---R-------------{}--------------------", r);
            }
            log.info("---------------------开始保存到数据库中---------------");
           for (AlipayTransactionRecordVO transactionRecordVO : recordVOS) {
                alipayTransactionRecordVOMapper.insert(transactionRecordVO);
            }
//            alipayTransactionRecordVOMapper.insertBatch(recordVOS);
            log.info("---------------------保存到数据库成功---------------");
            return recordVOS.size();

        } catch (Exception e) {
            log.error(e);
            throw new MyException(e.getMessage());
        }
    }
}
