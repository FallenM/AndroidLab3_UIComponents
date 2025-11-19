# 实验三 UIComponents 实验报告

## 实验目的

初步了解ListView、AlertDialog等组件的使用，以及使用XML定义菜单和创建基于ActionMode的上下文菜单。

## 实验环境

Win11 + Android Studio 2025.1.3

## 第一部分 ListView

### ListView主要功能（列表展示并显示对应Toast）

要点：      
1.实验要求使用SimpleAdapter的方式实现ListView，而需要实现的ListView正好每一项都要绑定text，icon（String，Object）的数据，SimpleAdapter正好需要传入的是Map<String，？>类型的参数，所以完美适配。            
2.如果要在Toast显示点击列表项的内容，就要在点击的时候获取相应的值然后用Toast输出。

### 发送通知

要点：     
1.Android8.0之后要求通知显示必须要经过一个已注册的渠道，所以要发送通知首先需要注册一个渠道。渠道便于通知的分类与用户的个性化设置。     
2.渠道和通知都可以设置重要等级，等级越高提醒的效果越明显（但是由于本实验环境是在页面获取通知，所以不管怎么样通知等级都不高，模拟器感觉不到振动，但是可以听到提示音。不会弹出通知，只能通过下拉状态栏看见）。
3.似乎还要在AndroidManifest.xml里面添加发送通知的权限。

## 第二部分 AlertDialog

### AlertDialog主要功能

要点：    
1.制作一个很经典的对话框，其实大头也就是做好界面布局，然后就是搞一个事件调用出来对话框，这里选择的是通过点击一个按钮触发事件。     
2.对话框本身也可以绑定很多事件，这里就写了一个Cancel按钮的事件，点击后退出对话框。

## 第三部分 Menu

### 用XML定义菜单

要点：      
1.可以通过`<Menu><Item><Menu><Item>`（省略另一半）的形式套娃出子菜单，父菜单一般是只负责调出子菜单，具体功能通过绑定子菜单的id触发点击事件实现。     
2.Switch语句在高版本好像不能用来作为子菜单功能分支的整合，因为id不再被视为是一个常量，所以只能使用if-else结构。

### ActionMode形式的上下文菜单

要点：          
1.本质上是由一个ListView主界面和长按主界面会显示的ActionMode形式的上下文菜单组成。ListView的实现类似于第一部分。区别就是数据比较简单（就一个String），所以使用最方便的ArrayAdapter。    
2.因为要实现多选功能，所以需要调用一个多选事件的监听器，这种类型的监听器需要完整覆写所有方法，没用上的直接return false跳过即可，主要使用里面的onCreateActionMode方法、onItemCheckedStateChanged方法和onActionItemClicked方法。         
3.删除所选项的时候，选择从后往前循环可以防止删除导致的下标错乱，同时在删除后要立刻刷新页面。

## 总结

对Android Studio的几种重要组件有了更深入的了解，能更好地开发事件监听功能。
