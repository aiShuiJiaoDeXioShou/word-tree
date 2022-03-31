## 一.前期准备：
> swing基础，jdbc基础，javaSE基础，javafx基础

## 二.历代版本变更情况： 

  ### 项目开发,写一个关于写作的项目
  0.项目名称叫做奥特曼写作,主要写一些有关于赞美奥特曼星球的作文  
  1.他的一些功能:在里面写作,对数据进行备份,储存到txt文件当中  
  2.注册账号,管理员实现对账号的增删改查,注册账号登入之后能看到自己写的一些信息  
  3.对自己的文章进行修改,对自己文章的字符进行统计计算  
  4.项目分层,有工具包,实现包,UI用户交流包  
  5.工具包的实现思路:工具包里基本的开发思路为实现一个文件夹的创建,实现对数据的储存,实现对数据的增删改查  

  ## 6.版本迭代:  
 
  ### 1.1：
  0.1.修复了一些小bug  
  0.2.自五月二十七日开始每一周对一个小版本更新（2021）
 
  ### 奥特曼故事本2.0:
  0.1.新增了数据库的连接，新增对控制台信息的美化包UIBeautify
 
  ## 2.1：
  0.1.新增全局变量var优化代码的使用问题，新增加管理员查询修改删除，修改底层代码逻辑，使得对文件的备份能用了,增加多种文件夹复制模式
  增加游戏引擎工具包（com.wordtree.wTmodule.writing.tBossGameEngine），更改项目名称改为WordTree（世界树的意思）
 
  ## 2.2：
  0.1.新增文件上传与下载，重构项目将项目分为不同模块，新增聊天室业务模块，添加接口类，将项目独立出ytidea
  
  ## 2.3--更新时间2022年3月1日
  0.1.大版本更新，将改版本改为maven项目，进行包的重组，项目优化，改为使用javafx书写其余的模块
  
  --更新世界2022年3月19日
  将项目版本升级为jdk17
  0.2.大版本更新，项目可以打包独立发布，添加计算机模块，新增加登入模块，增加python解析器模块，增加图片查看模块，增加笔记本编辑器模块
  
  --更新于2022年3月22日
  0.3.大版本更新：写作编辑器完成
  0.3.1 版本更新：将项目迁移到Gradle修复了一些bug，美化了项目代码  --3月29号
  0.3.2 版本更新：第三方编辑器新增文件树系统
  0.3.3 版本更新：第三方编辑器基本功能实现 --3月31号
  0.3.4 版本更新：重构整个笔记本项目，将项目模块化，动态化，解决一些历史遗留问题、 bug  



## 二O一.版本更新图表
+ 2.3.2版本
![img.png](src/main/resources/static/日记img/img2.png)
![img.png](src/main/resources/static/日记img/img.png)
+ 2.3.2.1版本
![img.png](src/main/resources/static/日记img/img3.png)
## 三.项目介绍:
Main作为整个程序的主类,运行程序

 二.实现对象
 physicalPackage作为父类包,分为两个父类,一个作为对用户管理的类
 User:
  private String biming;//笔名
     private String user;//用户名
     private String password;//用户密码
     private int zishu;//
     private int xiugaicishu;
     private Date date;
  实现里面的主要功能的工具类:
  UserShiXian,OperatingString下面的 public static void tonJi(String fl)方法

  写作类writerPj:
  工具包WritingToolkit下面实现方法有:CreateFlie对文件进行管理,OperatingString对txt文档进行操作

三.经验总结
1.面向对象里面的参数是不具备直接的值的,比如在调用User类的笔名项时,遭遇到了错误的情况.
2.IO流传送与传输文件一定要记得释放内存不然,文字会一直卡在里面.
3.开发项目一定要检查一个方法测试一次,不然项目多了要付出更大的代价.项目开发成功结束

四.时间类
时间戳(timestamp)：距离特定时间的间隔.
计算机中的时间戳是指距离历元（1970-01-01 00：00：00：000）的时间间隔（ms）.
格林尼治时间(GMT)：是一个标准时间，用于全球时间的标准化，也称世界协调时(UT)。各个国家通过时区偏移来定义各国的标准时间。
计算机中时间2019-04-29 20:14:00 是该时间距离历元经过的毫秒数，用long类型存储。
计算机中的时间表示为，当前时间距离历元经过的毫秒数，即时间戳。在计算机中，知道时间戳，就知道时间。
时间本质上是一个整型。
中国位于东八区
中国的标准时china standard time = UTC + 08:00
日本的标准时 = UTC + 09:00
**********************************************************************
大小写字母的含义:
字母    	日期或时间元素	表示	示例
G	Era 标志符	Text	AD
y	年	Year	1996 ; 96
M	年中的月份	Month	July ; Jul ; 07
w	年中的周数	Number	27
W	月份中的周数	Number	2
D	年中的天数	Number	189
d	月份中的天数	Number	10
F	月份中的星期	Number	2
E	星期中的天数	Text	Tuesday ; Tue
a	Am/pm 标记	Text	PM
H	一天中的小时数（0-23）	Number	0
k	一天中的小时数（1-24）	Number	24
K	am/pm 中的小时数（0-11）	Number	0
h	am/pm 中的小时数（1-12）	Number	12
m	小时中的分钟数	Number	30
s	分钟中的秒数	Number	55
S	毫秒数	Number	978
z	时区	General time zone	Pacific Standard Time ; PST ; GMT-08:00
Z	时区	RFC 822 time zone	-0800
************************************************************************


文本区获取光标当前位置
~~~kotlin
            var i = codeArea.caretPosition
            val substring = readerText.substring(0, i)
//            当前鼠标所在的行数和列数
            row = substring.split("\n").size
            col = codeArea.caretColumn
            println(substring.split("\n").size)
            println(substring)
            codeArea.showParagraphAtCenter()
            println( codeArea.caretColumn.toString()+"\t"+codeArea.caretPosition.toString())
~~~
