package fuck

import com.google.gson.Gson
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage: java -jar xxx.jar dbFilePath")
        return
    }

    val dbFilePath = args[0]
    val savePath = "./playlist"
    File(savePath).mkdirs()

    initDB(dbFilePath)

    getPlayLists()?.forEach { playLists ->
        println(playLists.name)
        var allSongs = ""
        getSonsByPid("${playLists.id}")?.forEach {
            val fromJson = Gson().fromJson<JsonRootBean>(it, JsonRootBean::class.java)
            var arts = ""
            for (line in fromJson.artists) {
                arts += line.name + ","
            }
            allSongs += (fromJson.name + " - " + arts + "\n")
        }
        val path = "$savePath/${playLists.name.replace("/", "-")}.txt"
        print(path)
        File(path).appendText(allSongs)
    }

    closeDB()
    println("导出完毕")
}

var c: Connection? = null
var stmt: Statement? = null

fun initDB(dbFilePath: String) {
    Class.forName("org.sqlite.JDBC")
    c = DriverManager.getConnection("jdbc:sqlite:$dbFilePath")
    stmt = c?.createStatement()
    println("Opened database successfully")
}

fun getPlayLists(): List<PlayListBean>? {
    val rs = stmt?.executeQuery("SELECT playlist FROM web_playlist;") ?: return null
    val pids = mutableListOf<PlayListBean>()
    while (rs.next()) {
        pids.add(Gson().fromJson(rs.getString(1), PlayListBean::class.java))
    }
    return pids
}

fun getSonsByPid(pid: String): List<String>? {
    val sql = "SELECT track from web_track WHERE tid in " +
            "(SELECT tid from web_playlist_track WHERE pid = '$pid');"
    val songs = mutableListOf<String>()
    val rs = stmt?.executeQuery(sql) ?: return null
    while (rs.next()) {
        songs.add(rs.getString(1))
    }
    return songs
}

fun closeDB() {
    stmt?.close()
    c?.close()
}
