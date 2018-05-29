package com.chain.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.SocketException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by lian.ran on 2018/3/26.
 */
public class FTPPoolableObjectFactory extends BasePoolableObjectFactory<FTPClient> {
    private static final Logger LOGGER = getLogger(FTPPoolableObjectFactory.class);
    private String host;
    private int port;
    private String user;
    private String password;
    private String passiveModeConf;
    public String msg;

    public String encoding = "UTF-8";

    public boolean binaryTransfer =true;

    public boolean passiveMode = true;
    public int CLIENT_TIMEOUT =2000;

    public FTPPoolableObjectFactory (String host,int port,String user,String password,String passiveModeConf){
        this.host=host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.passiveModeConf = passiveModeConf;
    }
    @Override
    public FTPClient makeObject() throws Exception {
        FTPClient ftpClient = getFTPClient();
        return ftpClient;
    }

    @Override
    public void destroyObject(FTPClient client) throws Exception {
        if (client != null) {
            disconnect(client);
        }
    }

    @Override
    public boolean validateObject(FTPClient obj) {
        if (obj != null) {
            FTPClient ftpClient = obj;
            try {
                return ftpClient.isConnected();
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                return false;
            }
        }
        return false;
    }

    private void setFileType(FTPClient ftpClient) throws FTPClientException {
        try {
            if (binaryTransfer) {
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            } else {
                ftpClient.setFileType(FTPClient.ASCII_FILE_TYPE);
            }
        } catch (IOException e) {
            throw new FTPClientException("Could not to set file type.", e);
        }
        msg = " setFileType(FTPClient " + ftpClient + ") 结束";
        LOGGER.error(msg);
    }

    private FTPClient getFTPClient() throws FTPClientException {
        FTPClient ftpClient = new FTPClient(); // 构造一个FtpClient实例
        ftpClient.setControlEncoding(encoding); // 设置字符集
        connect(ftpClient); // 连接到ftp服务器
        // 设置为passive模式
        if (passiveMode) {
            ftpClient.enterLocalPassiveMode();
        }
        setFileType(ftpClient); // 设置文件传输类型
        try {
            ftpClient.setSoTimeout(CLIENT_TIMEOUT);
        } catch (SocketException e) {
            throw new FTPClientException("Set timeout error.", e);
        }
        return ftpClient;
    }

    private boolean connect(FTPClient ftpClient) throws FTPClientException {
        try {
            ftpClient.connect(host, port);
            // 连接后检测返回码来校验连接是否成功
            int reply = ftpClient.getReplyCode();

            if (FTPReply.isPositiveCompletion(reply)) {
                // 登陆到ftp服务器
                msg = "login(" + user + ", " + password + ")";
                if (ftpClient.login(user, password)) {
                    setFileType(ftpClient);
                    return true;
                }
            } else {
                ftpClient.disconnect();
                throw new FTPClientException("FTP server refused connection.");
            }
        } catch (IOException e) {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect(); // 断开连接
                } catch (IOException e1) {
                    throw new FTPClientException("Could not disconnect from server.", e);
                }

            }
            throw new FTPClientException("Could not connect to server.", e);
        }
        return false;
    }

    private void disconnect(FTPClient ftpClient) throws FTPClientException {
        try {
            ftpClient.logout();
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            throw new FTPClientException("Could not disconnect from server.", e);
        }
    }
}
