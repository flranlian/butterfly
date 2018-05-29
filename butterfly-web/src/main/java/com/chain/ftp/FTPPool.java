package com.chain.ftp;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * Created by lian.ran on 2018/3/26.
 */
public class FTPPool extends AbstractObjectPool {
    /*
    public FTPPool(GenericObjectPool.Config poolConfig, PoolableObjectFactory factory) {

        super(poolConfig, new FTPPoolableObjectFactory());
    }*/

    public FTPPool(GenericObjectPool.Config poolConfig,String host,int port,String user,String password,String passiveModeConf){
        super(poolConfig,new FTPPoolableObjectFactory(host, port, user, password, passiveModeConf));
    }
}
