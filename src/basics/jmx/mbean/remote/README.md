
> jmx-mbean.rar 为MBean对象的jar包
>
> jmx-server.rar 为远程下载的类的服务端


1、创建一个jar包，包含MBean实例和对应的接口，类可放在包的任意位置

2、创建一个任意名称的文件（以下称作mlet文件），无后缀，写入

```html
<HTML>
<mlet code=com.hyf.Payload archive=jmx_mbean.jar name=basics.jmx.mbean.remote:name=Payload></mlet>
</HTML>
```

- code为MBean对象的全类名

- archive为mlet文件相对的jar包路径

- name为注册MBean的对象名称

> 推荐mlet文件和jar包放在同级目录下

3、两个文件放入服务器中，可通过http获取mlet文件即可。

4、启动注册服务器

5、界面调用`getMBeansFromURL()`方法注册即可注册成功
