module com.wordtree.service {
    requires kotlin.stdlib;
    requires java.sql;
    requires spring.core;
    requires spring.aop;
    requires spring.beans;
    requires spring.context;
    requires spring.expression;
    requires spring.jdbc;
    requires sqlite.jdbc;
    requires spring.jcl;
    requires org.aspectj.weaver;
    requires annotations;
    opens com.wordtree.service.service.impl to spring.core,spring.aop,spring.beans,spring.context,spring.expression,spring.jcl,org.aspectj.weaver;
    opens com.wordtree.service.config to spring.core,spring.aop,spring.beans,spring.context,spring.expression,spring.jcl,org.aspectj.weaver;
    opens com.wordtree.service.controller to spring.core,spring.aop,spring.beans,spring.context,spring.expression,spring.jcl,org.aspectj.weaver;
    opens com.wordtree.service.tool to org.aspectj.weaver, spring.aop, spring.beans, spring.context, spring.core, spring.expression, spring.jcl;

    exports com.wordtree.service.controller;
    exports com.wordtree.service.service;
    exports com.wordtree.service.config;
    exports com.wordtree.service.tool;
    exports com.wordtree.service.entity;
}
