# chatGPTbot


#配合mirai机器使用
安装 Java （版本必须 >= 11）

下载Mirai 控制台

https://github.com/iTXTech/mirai-console-loader/releases

#手动安装插件mirai-api-http

1.双击mcl.cmd 或./mcl 运行 Mirai Console 生成 plugins 文件夹

2.从 https://github.com/project-mirai/mirai-api-http/releases

下载jar并将其放入plugins文件夹中

3.建议下载v2.5.0版本
```
https://github.com/project-mirai/mirai-api-http/releases/download/v2.5.0/mirai-api-http-v2.5.0.mirai.jar

```

4.关闭再次运行。会在config\net.mamoe.mirai-api-http中生成setting.yml文件

5.修改setting.yml配置以下设置 同时把bot的对应application.yml修改，保持默认就不用动
```properties
adapters: 
  - http
debug: false
enableVerify: true
verifyKey: INITKEYbcupcF2N
singleMode: false
cacheSize: 4096
adapterSettings: 
      ## 详情看 http adapter 使用说明 配置
  http:
    host: 0.0.0.0
    port: 8082
    cors: [*]

```
#OPENAI 创建一个apikey 

https://beta.openai.com/account/api-keys

修改application.yml的apikey



#登录QQ账号
运行 Mirai Console

输入?是帮助  protocol是协议 这里我写ANDROID_PAD

可以为
```
"ANDROID_PHONE" / "ANDROID_PAD" / "ANDROID_WATCH" / "MACOS" / "IPAD"

```
登录命令

```shell
/login <qq> [password] [protocol]


```
如：/login 12345 abc123 ANDROID_PAD

如果出现验证要按提示进行验证,或者需要部署Liunx,

详情了解mirai官方文档，设置自动登录即可。



#启动机器人

可以源码编译 或者 到

https://github.com/yang10560/chatGPTbot/releases

下载 

记得把application.yml和jar放在同一个目录，然后cmd运行

注意：修改yml里的robotQQ机器人账号和相关apikey配置

```shell
#前台运行
jar -jar OpenAIbot-1.0-SNAPSHOT.jar

#linux后台运行

nohup java -jar OpenAIbot-1.0-SNAPSHOT.jar >log.log 2>&1 &


如果读不到配置文件就加上参数
```
```

jar -jar OpenAIbot-1.0-SNAPSHOT.jar --spring.config.location=application.yml的绝对路径
```


！！！注意启动顺序。先配置并成功登录mirai 然后再启动OpenAIbot 
因为它在启动时完成对mirai机器人的绑定。

注意端口不要冲突。如果8082端口占用 请修改配置文件更换端口
！！！

#交流群

249733992
