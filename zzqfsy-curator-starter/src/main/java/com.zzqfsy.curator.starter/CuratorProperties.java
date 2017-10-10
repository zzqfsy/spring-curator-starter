package com.zzqfsy.curator.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.curator")
public class CuratorProperties {

    private String ensembleProviderRef;
    private String connectString;

    private String aclProviderRef;
    private String authInfosRef;
    private String scheme;
    private String authStr;

    private Boolean canBeReadOnly;

    private Boolean useContainerParentsIfAvailable;

    private String compressionProviderRef;

    private String defaultDataBase64Str;

    private String namespace;

    private Integer sessionTimeOutMs;
    private Integer connectionTimeoutMs;
    private Integer maxCloseWaitMs;

    private String threadFactoryRef;
    private String zookeeperFactoryRef;

    private int baseSleepTimeMs = 1 * 1000;
    private int maxRetries = 5;

    public String getEnsembleProviderRef() {
        return ensembleProviderRef;
    }

    public void setEnsembleProviderRef(String ensembleProviderRef) {
        this.ensembleProviderRef = ensembleProviderRef;
    }

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public String getAclProviderRef() {
        return aclProviderRef;
    }

    public void setAclProviderRef(String aclProviderRef) {
        this.aclProviderRef = aclProviderRef;
    }

    public String getAuthInfosRef() {
        return authInfosRef;
    }

    public void setAuthInfosRef(String authInfosRef) {
        this.authInfosRef = authInfosRef;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getAuthStr() {
        return authStr;
    }

    public void setAuthStr(String authStr) {
        this.authStr = authStr;
    }

    public Boolean getCanBeReadOnly() {
        return canBeReadOnly;
    }

    public void setCanBeReadOnly(Boolean canBeReadOnly) {
        this.canBeReadOnly = canBeReadOnly;
    }

    public Boolean getUseContainerParentsIfAvailable() {
        return useContainerParentsIfAvailable;
    }

    public void setUseContainerParentsIfAvailable(Boolean useContainerParentsIfAvailable) {
        this.useContainerParentsIfAvailable = useContainerParentsIfAvailable;
    }

    public String getCompressionProviderRef() {
        return compressionProviderRef;
    }

    public void setCompressionProviderRef(String compressionProviderRef) {
        this.compressionProviderRef = compressionProviderRef;
    }

    public String getDefaultDataBase64Str() {
        return defaultDataBase64Str;
    }

    public void setDefaultDataBase64Str(String defaultDataBase64Str) {
        this.defaultDataBase64Str = defaultDataBase64Str;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Integer getSessionTimeOutMs() {
        return sessionTimeOutMs;
    }

    public void setSessionTimeOutMs(Integer sessionTimeOutMs) {
        this.sessionTimeOutMs = sessionTimeOutMs;
    }

    public Integer getConnectionTimeoutMs() {
        return connectionTimeoutMs;
    }

    public void setConnectionTimeoutMs(Integer connectionTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
    }

    public Integer getMaxCloseWaitMs() {
        return maxCloseWaitMs;
    }

    public void setMaxCloseWaitMs(Integer maxCloseWaitMs) {
        this.maxCloseWaitMs = maxCloseWaitMs;
    }

    public String getThreadFactoryRef() {
        return threadFactoryRef;
    }

    public void setThreadFactoryRef(String threadFactoryRef) {
        this.threadFactoryRef = threadFactoryRef;
    }

    public String getZookeeperFactoryRef() {
        return zookeeperFactoryRef;
    }

    public void setZookeeperFactoryRef(String zookeeperFactoryRef) {
        this.zookeeperFactoryRef = zookeeperFactoryRef;
    }

    public int getBaseSleepTimeMs() {
        return baseSleepTimeMs;
    }

    public void setBaseSleepTimeMs(int baseSleepTimeMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }
}
