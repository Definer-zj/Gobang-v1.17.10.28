# Gobang-v1.17.10.28

Gobang-v1.17.10.28

简单的五子棋游戏（java application）

1、判断输赢

五子棋是一种两人对弈的纯策略型棋类游戏，通常双方分别使用黑白两色的棋子，轮流下在棋盘直线与横线的交叉点上，先在横线、直线或斜对角线上形成5子连线者获胜。

本游戏通过定义一个变量分别统计黑白两色棋子在其落点横线、直线或斜对角线上的相同棋子的最大数（同色获胜棋子数），该点对应的变量值达到5时，即可判断输赢。

2、AI算法

五子棋人机对战的重点是解决机器落子问题，即让机器自行判断该往什么地方落子。

这次版本采取算法：玩家黑子先手，定义一个变量max统计某点同色获胜棋子最大值，机器穷举每个可落子点，判断该点下黑子或白子时各自的获胜同色棋子最大值的大小，将max与黑白子中较大值比较之后更新max及落子点坐标。计算完毕，机器在该落子点下子。


不过，本次实现的功能比较少，仅能进行简单的人机对战、人人对战，修改棋盘的大小和外观而已。

下次更新：

1、添加多种难度的人机对战

2、添加悔棋功能

3、添加记录步数功能

Gobang-v1.17.10.30

新增入门难度（防御型），记之前的难度为一般难度（攻防型）


