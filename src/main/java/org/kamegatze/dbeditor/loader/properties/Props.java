package org.kamegatze.dbeditor.loader.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class Props {
    public final static Properties PROPERTIES = getProperties();

    private final String applicationTitle;
    private final String applicationView;
    private final String namePostgresql;
    private final String nameMysql;
    private final String nameOracle;
    private final String urlTemplate;
    private final String databaseName;
    private final String schemeName;
    private final String tableName;
    private final String viewName;
    private final String loginRoles;

    public Props(@Value("${application.title}") String applicationTitle,
                 @Value("${application.view}") String applicationView,
                 @Value("${connection.databases.name.postgresql}") String namePostgresql,
                 @Value("${connection.databases.name.mysql}") String nameMysql,
                 @Value("${connection.databases.name.oracle}") String nameOracle,
                 @Value("${connection.url.template}") String urlTemplate,
                 @Value("${connection.databases.name}") String databaseName,
                 @Value("${connection.databases.schemas.name}") String schemeName,
                 @Value("${connection.databases.schemas.tables.name}") String tableName,
                 @Value("${connection.databases.schemas.views.name}") String viewName,
                 @Value("${connection.login-and-group-roles}") String loginRoles) {
        this.applicationTitle = applicationTitle;
        this.applicationView = applicationView;
        this.namePostgresql = namePostgresql;
        this.nameMysql = nameMysql;
        this.nameOracle = nameOracle;
        this.urlTemplate = urlTemplate;
        this.databaseName = databaseName;
        this.schemeName = schemeName;
        this.tableName = tableName;
        this.viewName = viewName;
        this.loginRoles = loginRoles;
    }

    private static Properties getProperties() {
        try(FileReader fileReader = new FileReader("./src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(fileReader);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getApplicationTitle() {
        return applicationTitle;
    }

    public String getApplicationView() {
        return applicationView;
    }

    public String getNamePostgresql() {
        return namePostgresql;
    }

    public String getNameMysql() {
        return nameMysql;
    }

    public String getNameOracle() {
        return nameOracle;
    }

    public String getUrlTemplate() {
        return urlTemplate;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getViewName() {
        return viewName;
    }

    public String getLoginRoles() {
        return loginRoles;
    }
}
