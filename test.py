import sqlite3
import json
import os

# 获取当前用户的路径
user_path = os.path.expanduser('~')

# 构建数据库文件路径
db_path = os.path.join(user_path, 'AppData', 'Local', 'NetEase', 'CloudMusic', 'Library', 'webdb.dat')

# 连接数据库
conn = sqlite3.connect(db_path)
cursor = conn.cursor()

# 执行SQL查询
cursor.execute("SELECT id, jsonStr FROM playlistTrackIds")

# 获取结果
playlists = cursor.fetchall()

for playlist in playlists:
    playlist_id = playlist[0]
    playlist_json = playlist[1]
    
    # 解析JSON字符串
    playlist_data = json.loads(playlist_json)
    
    # 获取歌单ID和歌曲ID列表
    playlist_name = playlist_data["id"]
    track_ids = [track["id"] for track in playlist_data["trackIds"]]
    
    # 查询歌曲详细信息
    track_details = []
    for track_id in track_ids:
        cursor.execute("SELECT jsonStr FROM dbTrack WHERE id = ?", (track_id,))
        track_info = cursor.fetchone()
        if track_info:
            track_json = track_info[0]
            track_data = json.loads(track_json)
            track_details.append(track_data)
    
    # 将歌单信息和歌曲详细信息写入文件
    file_name = f"playlist{playlist_name}.txt"
    with open(file_name, "w", encoding="utf-8") as file:
        file.write(f"歌单ID: {playlist_name}\n")
        file.write("歌曲列表:\n")
        for track in track_details:
            artists = [artist['name'] for artist in track['artists']]
            artists_str = ', '.join(artists)
            file.write(f"  {track['name']} - {artists_str}\n")

# 关闭连接
conn.close()

print("歌单列表已导出到单独的文件")
