从JDK 6u45、7u21开始，java.rmi.server.useCodebaseOnly 的默认值就是true，将禁用自动加载远程类文件

6u141, JDK 7u131, JDK 8u121
增加了com.sun.jndi.rmi.object.trustURLCodebase选项，默认为false，禁止RMI和CORBA协议使用远程codebase的选项

JDK 6u211、7u201、8u191之后：增加了com.sun.jndi.ldap.object.trustURLCodebase选项，默认为false，禁止LDAP协议使用远程codebase的选项