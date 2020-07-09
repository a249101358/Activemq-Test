package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadTxt {

    public static void main(String args[]) {
        String buffer = readFile();
        String regex = "([\u4e00-\u9fa5]+)"; 
        String str = ""; 
        Matcher matcher = Pattern.compile(regex).matcher(buffer); 
        while (matcher.find()) { 
        str+= matcher.group(0); 
        str=str+"\n";
        } 
        System.err.println(str);
    }

    /**
     * 读入TXT文件
     */
    public static String readFile() {
        String pathname = "C:/Users/Administrator/Desktop/test.txt"; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
        //不关闭文件会导致资源的泄露，读写文件都同理
        //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
        StringBuffer buffer= new StringBuffer();
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            //更加简洁的写法
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
               //System.out.println(line);
            	buffer.append(line);
            	buffer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
