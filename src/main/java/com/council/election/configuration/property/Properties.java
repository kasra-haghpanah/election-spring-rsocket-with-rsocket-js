package com.council.election.configuration.property;

import com.council.election.ddd.client.rest.SMSClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

@Component("properties")
/*@PropertySources(
        {
                @PropertySource(
                        value = "classpath:application.properties",
                        ignoreResourceNotFound = true,
                        encoding = "UTF-8"
                )
        }
)*/
public class Properties {

    private static final Map<String, Object> config = new HashMap<String, Object>();

/*    @ConditionalOnMissingBean
    @Bean
    public BuildProperties buildProperties() {
        java.util.Properties properties = new java.util.Properties();
        properties.put("group", "com.example");
        properties.put("artifact", "election");
        properties.put("version", "not-jarred");
        return new BuildProperties(properties);
    }*/

    public Properties(
            ApplicationContext applicationContext,
            ConfigurableEnvironment configurableEnvironment,
            Environment environment,
            BuildProperties buildProperties
    ) throws IOException {

        System.out.println(buildProperties.getArtifact());

        Resource[] resources = applicationContext.getResources("classpath:*.properties");

        for (Resource resource : resources) {
            EncodedResource encodedResource = new EncodedResource(resource, Charset.forName("UTF-8"));
            ResourcePropertySource propertySource = new ResourcePropertySource(encodedResource);
            configurableEnvironment.getPropertySources().remove(resource.getFile().getName());
            configurableEnvironment.getPropertySources().addFirst(propertySource);
        }

        String applicationName = buildProperties.getArtifact();//applicationContext.getId();
        //System.out.println(applicationName + "@@@@");

        config.put("mongo.user", environment.getProperty("mongo.user"));
        config.put("mongo.password", environment.getProperty("mongo.password"));
        config.put("mongo.host", environment.getProperty("mongo.host"));
        config.put("mongo.port", Integer.valueOf(environment.getProperty("mongo.port")));
        config.put("mongo.database", environment.getProperty("mongo.database"));
        config.put("server.host", environment.getProperty("server.host"));
        config.put("server.port", Integer.valueOf(environment.getProperty("server.port")));
        config.put("contextPath", environment.getProperty("contextPath"));
        config.put("server.domain.baseUrl", environment.getProperty("server.domain.baseUrl"));
        config.put("show.connected.client", Boolean.valueOf(environment.getProperty("show.connected.client", String.class)));

        config.put("serverName", environment.getProperty("serverName"));
        config.put("poweredBy", environment.getProperty("poweredBy"));
        config.put("jwt.expirationTime", Long.valueOf(environment.getProperty("jwt.expirationTime")));
        config.put("jwt.secret", environment.getProperty("jwt.secret"));
        config.put("adminUsers", new HashSet<String>(Arrays.asList(environment.getProperty("adminUsers").split(","))));

        config.put("pannel.sms.apiKey", environment.getProperty("pannel.sms.apiKey"));
        config.put("pannel.sms.baseUrl", environment.getProperty("pannel.sms.baseUrl"));
        config.put("pannel.sms.sender", environment.getProperty("pannel.sms.sender"));
        config.put("pannel.sms.active", Boolean.valueOf(environment.getProperty("pannel.sms.active", String.class)));

        SMSClient.setApiKey(get("pannel.sms.apiKey", String.class));
        SMSClient.setBaseUrl(get("pannel.sms.baseUrl", String.class));
        SMSClient.setSender(get("pannel.sms.sender", String.class));
        SMSClient.setServerDomainBaseUrl(get("server.domain.baseUrl", String.class));
        SMSClient.setPannelSmsActive(get("pannel.sms.active", Boolean.class));

        Mono.just(config).block();
    }

    private static Object get(String key) {
        return config.get(key);
    }

    private static <T> T get(String key, Class<T> T) {
        return (T) config.get(key);
    }

    public static String getMongoUser() {
        return get("mongo.user", String.class);
    }

    public static String getMongoPassword() {
        return get("mongo.password", String.class);
    }

    public static String getMongoHost() {
        return get("mongo.host", String.class);
    }

    public static Integer getMongoPort() {
        return get("mongo.port", Integer.class);
    }

    public static String getMongoDatabase() {
        return get("mongo.database", String.class);
    }

    public static String getServerHost() {
        return get("server.host", String.class);
    }

    public static Integer getServerPort() {
        return get("server.port", Integer.class);
    }

    public static String getContextPath() {
        return get("contextPath", String.class);
    }

    public static String getServerDomainBaseUrl() {
        return get("server.domain.baseUrl", String.class);
    }

    public static Boolean getShowConnectedClient() {
        return get("show.connected.client", Boolean.class);
    }

    public static String getServerName() {
        return get("serverName", String.class);
    }

    public static String getPoweredBy() {
        return get("poweredBy", String.class);
    }

    public static Long getJwtExpirationTime() {
        return get("jwt.expirationTime", Long.class);
    }

    public static String getJwtSecret() {
        return get("jwt.secret", String.class);
    }

    public static Set getAdminUsers() {
        return get("adminUsers", HashSet.class);
    }


    public static String getPannelSmsApiKey() {
        return get("pannel.sms.apiKey", String.class);
    }

    public static String getPannelSmsBaseUrl() {
        return get("pannel.sms.baseUrl", String.class);
    }

    public static String getPannelSmsSender() {
        return get("pannel.sms.sender", String.class);
    }

    public static Boolean getPannelSmsActive() {
        return get("pannel.sms.active", Boolean.class);
    }

}
