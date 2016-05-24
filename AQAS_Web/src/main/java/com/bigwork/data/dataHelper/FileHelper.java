package com.bigwork.data.dataHelper;

import java.io.*;
import java.util.ArrayList;


public class FileHelper {

    private String charsetName = "UTF-8";
    private File file;

    //	private String path = "";
    public FileHelper(String path) {
        this.file = new File(path);
    }

    public void setpath(String path) {
        this.file = new File(path);
    }

    /**
     * 按行读出
     * 返回序列化的arraylist
     *
     * @return
     */
    public MyList<String> read() {
        MyList<String> re = new MyList<String>();
        try {
            if (!file.exists()) {
                System.out.println(file.getAbsolutePath() + "notExits");
                return re;
            }
            InputStreamReader fr = new InputStreamReader(new FileInputStream(file), this.charsetName);
            BufferedReader br = new BufferedReader(fr);

            String record = null;
            while ((record = br.readLine()) != null) {
                re.add(record);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return re;
    }

    /**
     * 重新写入文件内容
     * 按行写入
     *
     * @param os 传入的每行数据存储于arraylist中
     * @return
     */
    public boolean rewrite(ArrayList<? extends Object> os) {
        try {
            OutputStreamWriter br = new OutputStreamWriter(new FileOutputStream(file), this.charsetName);
            for (Object object : os) {
                br.write(object.toString() + "\n");
            }
            br.flush();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        //System.out.println(path);
        return true;
    }

    public boolean add(Object object) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStreamWriter br = new OutputStreamWriter(new FileOutputStream(file, true), this.charsetName);
            br.write(object.toString() + "\n");
            br.flush();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

