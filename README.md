## 项目名称

《ToDo》

## 成员介绍



## 项目简介

### App 简介和构思

这是一个任务规划类的App。当前市场上，诸如番茄闹钟的任务规划类App，基本上功能繁多、UI五彩斑斓，对于视觉障碍、行为障碍的人群不怎么友好，所以我就写了这样的App。</br>

除了正常人群外，App面向的人群还可以有老年人和视觉障碍人群：

- App做了大字模式和对TalkBack的适配 
- 考虑到色弱人群，App基本调色以蓝色和白色为主，因为蓝色色盲人群最少。同时，App也提供了红色和黄色的可视化图表
- 并且，App也对可视化的图表和日历进行了一定适配，可以播报里面的日期和数据

### App 架构

~~虽然写到后面就没有什么架构了~~

整体是分为了四层：三方公共层、基础层、业务层和应用层

三方公共层大体由`Arouter`、`GreenDao`、`androidx`组成

基础层有`libray_base`和`library_common`，分别是mvp的简单抽象、一些共有的工具类和UI

业务层由四个`module`组成，分别对应App的四个大类功能

应用层就是App，采用`BottomNavigation + Viewpager + Toolbar + 悬浮窗`实现，并且补了个添加任务的Activity

### App 功能介绍

- 添加任务
- 完成任务
- 按照日期划分了每一天的任务
- 分别统计了每日、每周、每月的任务完成情况，并且对每周的任务完成情况绘制了折线图和直线图
- 一些主题的替换（大字模式，更换可视化图表的颜色）
- TalkBack的适配

### App废弃或未实现的功能

- 任务的定时提醒
- 任务的删除
- 自定义了AccessibilityService，写了个页面跳转和离开App时候语音播报，确实很困扰



## 项目展示

飞书文档附加了百度网盘 
![image](https://github.com/965023780/ToDoList/blob/main/普通模式.gif)  
![image](https://github.com/965023780/ToDoList/blob/main/大字模式.gif)
![image](https://github.com/965023780/ToDoList/blob/main/TalkBack.gif)   

## 内容链接

https://github.com/965023780/ToDoList
