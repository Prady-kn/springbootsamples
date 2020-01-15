package com.demo.springjpa.helper;

import org.hibernate.EmptyInterceptor;
import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Component
public class HibernateInterceptor 
implements StatementInspector {

    Pattern schema_pattern = Pattern.compile("\\$\\{(?<sc>schema_[a-z0-9]+)\\}");

    @Override
    public String inspect(String sql) {
        // TODO Auto-generated method stub

        Environment envs = SpringContextUtil.getApplicationContext().getBean(Environment.class);
        String newSql = sql;
        Matcher m = schema_pattern.matcher(sql);
        while (m.find()) {
            String schemaPattern = m.group(0);
            String schemaKey = m.group(1).replace('.', '_');
            schemaPattern = schemaPattern.replace("$", "\\$").replace("{", "\\{");
            schemaKey=schemaKey.replace('_', '.');
            newSql = newSql.replaceAll(schemaPattern, envs.getProperty(schemaKey));
        }

        return newSql;
    }
}