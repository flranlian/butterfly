package com.chain.ftp;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * Created by lian.ran on 2018/3/26.
 */
public abstract class AbstractObjectPool<T> {

    private final GenericObjectPool<T> internalPool;

    public AbstractObjectPool(GenericObjectPool.Config poolConfig, PoolableObjectFactory factory) {
        this.internalPool = new GenericObjectPool(factory, poolConfig);
    }

    /**
     * 获取资源
     * @return
     * @throws Exception
     */
    public T getResource() throws Exception {
        return this.internalPool.borrowObject();
    }

    /**
     * 归还资源
     * @param resource
     * @throws Exception
     */
    public void returnResource(T resource) throws Exception {
        this.internalPool.returnObject(resource);
    }

    /**
     * 注销资源
     * @throws Exception
     */
    public void destroy() throws Exception {
        this.internalPool.close();
    }

    /**
     * 池大小
     * @return
     */
    public int inPoolSize(){
        try{
            return this.internalPool.getNumIdle();
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 存活个数
     * @return
     */
    public int borrowSize() {
        try {
            return this.internalPool.getNumActive();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}