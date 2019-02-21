package com.utiexian.utils.cibfintech.econtract.until;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * 功能：
 * 类名： FileUtil
 *
 *  Ver     变更日期   	 修改人   	修改说明
 * ─────────────────────────
 * 	V1.0  	2017/8/15 0015 	  金超龙         初版
 *
 * Copyright (c) 2015, 2017 CibFinTech All Rights Reserved.
 * LICENSE INFORMATION
 */
public class FileUtil {
    public static byte[] file2byte(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}