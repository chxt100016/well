package org.wella.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

public class DocUtil {
    public DocUtil() {
    }

    public static InputStream returnBitMap(String urlPath) {
        URL url = null;
        InputStream is = null;

        try {
            url = new URL(urlPath);
        } catch (MalformedURLException var5) {
            var5.printStackTrace();
        }

        try {
            HttpURLConnection e = (HttpURLConnection)url.openConnection();
            e.setDoInput(true);
            e.connect();
            is = e.getInputStream();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return is;
    }

    public static InputStream returnBitMapInFile(String path) {
        File file = new File(path);
        FileInputStream is = null;

        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException var4) {
            var4.printStackTrace();
        }

        return is;
    }

    public static void replaceTextInDocx(XWPFDocument document, Map<String, String> map) {
    }

    private static Map<String, Object> getMyXWPFRunObj(String oneparaString, int pos, List<XWPFRun> runs) {
        HashMap myXWPFRunObj = new HashMap();
        myXWPFRunObj.put("pos", "-1");
        String str = "";
        String substr = "";
        int kk = pos + 1;
        if(oneparaString != null && pos < runs.size() - 1 && (oneparaString.length() <= 3 || !oneparaString.substring(0, 2).equals("${") || !oneparaString.substring(oneparaString.length() - 1).equals("}"))) {
            if(oneparaString.equals("$")) {
                str = ((XWPFRun)runs.get(kk)).getText(((XWPFRun)runs.get(kk)).getTextPosition());
                if(!str.equals("") && str.substring(0, 1).equals("{")) {
                    for(; kk < runs.size(); ++kk) {
                        str = ((XWPFRun)runs.get(kk)).getText(((XWPFRun)runs.get(kk)).getTextPosition());
                        if(str.indexOf("$") > 0) {
                            kk = runs.size() + 1;
                        } else if(str.substring(oneparaString.length() - 1).equals("}")) {
                            substr = substr + str.substring(0, str.length() - 1);
                            myXWPFRunObj.put("pos", Integer.valueOf(kk));
                            myXWPFRunObj.put("word", substr);
                            kk = runs.size() + 1;
                        } else if(kk == pos + 1) {
                            substr = substr + str.substring(1);
                        } else {
                            substr = substr + str;
                        }
                    }
                }
            } else if(oneparaString.length() > 1 && oneparaString.substring(0, 2).equals("${")) {
                for(; kk < runs.size(); ++kk) {
                    str = ((XWPFRun)runs.get(kk)).getText(((XWPFRun)runs.get(kk)).getTextPosition());
                    if(str.indexOf("$") > 0) {
                        kk = runs.size() + 1;
                    } else if(str.substring(str.length() - 1).equals("}")) {
                        substr = substr + str.substring(0, str.length() - 1);
                        myXWPFRunObj.put("pos", Integer.valueOf(kk));
                        myXWPFRunObj.put("word", substr);
                        kk = runs.size() + 1;
                    } else {
                        substr = substr + str;
                    }
                }
            }
        }

        return myXWPFRunObj;
    }

    public static XWPFTable getTableInDocx(XWPFDocument document, String listKey) {
        return null;
    }

    public static void setTableBorder(XWPFTable table) {
    }

    public static void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for(int rowIndex = fromRow; rowIndex <= toRow; ++rowIndex) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if(rowIndex == fromRow) {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }

    }

    public static void mergeCellsHorizentally(XWPFTable table, int row, int fromCol, int toCol) {
        for(int colIndex = fromCol; colIndex <= toCol; ++colIndex) {
            XWPFTableCell cell = table.getRow(row).getCell(colIndex);
            if(colIndex == fromCol) {
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }

    }

    public static XmlCursor getCursorPos(XWPFDocument document, String listKey) {
        return null;
    }

    public static void setCellText(XWPFTableCell cell, String text, String bgcolor, int width) {
        CTTc cttc = cell.getCTTc();
        CTTcPr cellPr = cttc.addNewTcPr();
        cellPr.addNewTcW().setW(BigInteger.valueOf((long)width));
        CTTcPr ctPr = cttc.addNewTcPr();
        CTShd ctshd = ctPr.addNewShd();
        ctshd.setFill(bgcolor);
        ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
        ((CTP)cttc.getPList().get(0)).addNewPPr().addNewJc().setVal(STJc.CENTER);
        cell.setText(text);
    }

    public static void setCellText(XWPFTableCell cell, String text, int width) {
        CTTc cttc = cell.getCTTc();
        CTTcPr cellPr = cttc.addNewTcPr();
        cellPr.addNewTcW().setW(BigInteger.valueOf((long)width));
        CTTcPr ctPr = cttc.addNewTcPr();
        ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
        ((CTP)cttc.getPList().get(0)).addNewPPr().addNewJc().setVal(STJc.CENTER);
        cell.setText(text);
    }

    public static void setCellText(XWPFTableCell cell, String text) {
        CTTc cttc = cell.getCTTc();
        CTTcPr ctPr = cttc.addNewTcPr();
        ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
        ((CTP)cttc.getPList().get(0)).addNewPPr().addNewJc().setVal(STJc.CENTER);
        cell.setText(text);
    }
}