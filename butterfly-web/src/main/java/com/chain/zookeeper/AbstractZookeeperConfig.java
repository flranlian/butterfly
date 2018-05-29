package com.chain.zookeeper;

//import kz.greetgo.conf.hot.AbstractConfigFactory;
//import kz.greetgo.conf.hot.ConfigStorage;
//import kz.greetgo.gcory.register.configs.UtilConfigs;
//
//public abstract class AbstractZookeeperConfig extends AbstractConfigFactory {
//
//  protected abstract ZookeeperAccess zookeeperAccess();
//
//  private final ConfigStorage configStorage = new ConfigStorage() {
//    @Override
//    public String loadConfigContent(String configLocation) throws Exception {
//      return zookeeperAccess().get(configLocation);
//    }
//
//    @Override
//    public boolean isConfigContentExists(String configLocation) throws Exception {
//      return zookeeperAccess().exists(configLocation);
//    }
//
//    @Override
//    public void saveConfigContent(String configLocation, String configContent) throws Exception {
//      zookeeperAccess().set(configLocation, configContent);
//    }
//  };
//
//  @Override
//  protected ConfigStorage getConfigStorage() {
//    return configStorage;
//  }
//
//  @Override
//  protected <T> String configLocationFor(Class<T> configInterface) {
//    return UtilConfigs.configLocation(configInterface);
//  }
//}
