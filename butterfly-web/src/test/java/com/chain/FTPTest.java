package com.chain;

import com.chain.ftp.FTPPool;
import com.chain.ftp.WriteFTPFile;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.sonar.runner.commonsio.FileUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.Optional;

/**
 * Created by lian.ran on 2018/3/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ButterflyWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FTPTest {

    @Test
    public void testPool(){
//设置连接池的配置参数
        GenericObjectPool.Config config = new GenericObjectPool.Config();
        //最大池容量
        config.maxActive=5;

        //从池中取对象达到最大时,继续创建新对象.
        config.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_GROW;

        //池为空时取对象等待的最大毫秒数
        config.maxWait=60*1000;

        //取出对象时验证(此处设置成验证ftp是否处于连接状态)
        config.testOnBorrow=true;

        //还回对象时验证(此处设置成验证ftp是否处于连接状态)
        config.testOnReturn=true;

        FTPPool pool = new FTPPool(config,"ftp.my.test",21,"user","password","true");

        System.out.println("borrowSize1:"+pool.borrowSize());
       System.out.println("inPoolSize1:"+pool.inPoolSize());
        long begin=System.currentTimeMillis();

        try {
            String localPath = "d:/";
            String fileName = "30_20210401_11522131903894_15221319284055.data-done";
            final File localFile = new File(localPath, fileName);
            FileUtils.touch(localFile);
            String ftpPath = "BatchDepute";
            String fileNameFtp="30_"+ "\\\\d{8}\\\\" + "_\\d{6}\\."+"data-done";
            System.out.println("开始下载");
            for(int i=0;i<1;i++){
                FTPClient ftpClient = (FTPClient)pool.getResource();
                System.out.println("开始下载"+i);
                final FTPFile[] ftpFiles = ftpClient
                      //  .listFiles(ftpPath, f -> f.isFile() && f.getName().matches(fileNameFtp));
                     .listFiles(ftpPath, f -> f.isFile());
                System.out.println("开始下载"+i+",匹配到数据"+ftpFiles.length);
                for(int j=0;j<ftpFiles.length;j++){

                    System.out.println("ftpClient"+(i+1)+" isConnected:"+ftpClient.isConnected());
                    System.out.println("开始下载ftp");
                    String retrieveFile = ftpPath + "/" + ftpFiles[j].getName();
                    retrieveFile(null,ftpFiles[j].getName(),ftpClient,false,retrieveFile,localPath);
                }
                ftpClient.changeWorkingDirectory("/");
                pool.returnResource(ftpClient);
            }

            System.out.println("time:"+(System.currentTimeMillis()-begin));
            System.out.println("borrowSize2:"+pool.borrowSize());
            System.out.println("inPoolSize2:"+pool.inPoolSize());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pool.destroy();  //消除连接
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
  //  @Test
    public void testUpload(){
        //设置连接池的配置参数
        GenericObjectPool.Config config = new GenericObjectPool.Config();
        //最大池容量
        config.maxActive=5;

        //从池中取对象达到最大时,继续创建新对象.
        config.whenExhaustedAction =    GenericObjectPool.WHEN_EXHAUSTED_GROW;

        //池为空时取对象等待的最大毫秒数
        config.maxWait=60*1000;

        //取出对象时验证(此处设置成验证ftp是否处于连接状态)
        config.testOnBorrow=true;

        //还回对象时验证(此处设置成验证ftp是否处于连接状态)
        config.testOnReturn=true;

        FTPPool pool = new FTPPool(config,"ftp.my.test",21,"user","password","true");

        System.out.println("borrowSize1:"+pool.borrowSize());
        System.out.println("inPoolSize1:"+pool.inPoolSize());
        long begin=System.currentTimeMillis();

        try {
            String localPath = "d:/";
            String fileName = "30_20210401_11522131903894_15221319284055.data-done";
            final File localFile = new File(localPath, fileName);
            FileUtils.touch(localFile);
            String ftpPath = "BatchDeputeReply";
            String fileNameFtp="30_"+ "\\\\d{8}\\\\" + "_\\d{6}\\."+"data-done";

            FTPClient ftpClient = (FTPClient)pool.getResource();

            WriteFTPFile writeFTPFile = new WriteFTPFile();
           // writeFTPFile.upload(ftpClient,);

            System.out.println("time:"+(System.currentTimeMillis()-begin));
            System.out.println("borrowSize2:"+pool.borrowSize());
            System.out.println("inPoolSize2:"+pool.inPoolSize());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pool.destroy();  //消除连接
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean retrieveFile(Logger ftplogger, String fileName, FTPClient ftpClient,
                                       boolean success, String retrieveFile,String localDir) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream2 = null;
        try {
            System.out.println("retrieveFile开始下载ftp");
            inputStream = ftpClient.retrieveFileStream(retrieveFile);
            if (inputStream != null) {
                System.out.println("download file ["+retrieveFile+"] from FTP");
                //    String dir = System.getProperty("user.home") + "/gcory.d/_big_batch_input_/";
                //20171013 chenlei modify
               // String localDir = System.getProperty("user.home") + "/Data/"+Gcory.domainName()+"/_big_batch_input_/";
                File downloadFile = new File(localDir + fileName);
                if(!downloadFile.exists()){
                  //  System.out.println("下载文件到本地目录:{"+(localDir + fileName)+"}重新创建");
                    downloadFile.createNewFile();
                }
                System.out.println("下载文件到本地目录:{"+(localDir + fileName)+"},文件大小:{}");
                outputStream2 = new BufferedOutputStream(new FileOutputStream(downloadFile));
                byte[] bytesArray = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                    outputStream2.write(bytesArray, 0, bytesRead);
                }
                success = ftpClient.completePendingCommand();
                if (!success) {
                    System.out.println("Some errors in downloading file:{"+retrieveFile+"} to file:{"+fileName+"}");
                }
                // System.out.println("Downloaded successfully");
            }else{
                System.out.println("从ftp下载文件:{"+retrieveFile+"}文件时,返回的信息是空的");
            }
        } finally {
            if(outputStream2!=null){
                outputStream2.flush();
                outputStream2.close();
            }
            if(inputStream !=null){
                inputStream.close();
            }
        }
        return success;
    }
}
