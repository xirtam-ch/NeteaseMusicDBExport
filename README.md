# NeteaseMusicDBExport
网易云音乐本地数据库导出歌单列表

做这个是起因是网易天天都在删我的歌，受不了了怕被删没了，所以想换到Spotify，或者至少单独保存一份自己的列表。
但是试了很多脚本，导出都是基于web的，我的单个歌单有2600首，web版本只支持1000首。
把2600分成3个歌单再保存一遍的话，已经灰色的歌又丢了。
所以只能从本地数据库搞出一份来，已经搞完，顺便分享下代码

# 需要环境

- Java

# 用法
### Mac OS X

- `java -jar NeteaseMusicDBExport.jar 数据库文件路径

- 我的mac上DB文件参考位置
`/Users/xirtam/Library/Containers/com.netease.163music/Data/Documents/storage/sqlite_storage.sqlite3`

### Windows
只需要一个test.py文件
```python test.py```
即可

# 导入到Spotify或者其他平台
- 使用文本导入工具，例如 https://www.tunemymusic.com/zh-cn/
- 开始 -> 从文本 -> 粘贴 -> 目的地 -> Spotify
