package com.easy.kotlin.mytodoapplication

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by jack on 2017/7/21.
 */
class MyTodoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val config = RealmConfiguration.Builder(this)
                .name("realm.my_todos")// 库文件名
                .encryptionKey(getKey())  // 加密
                .schemaVersion(1)  // 版本号
                .deleteRealmIfMigrationNeeded()
                .build()

        // 非持久化的、存在于内存中的 Realm 实例
        //        RealmConfiguration myConfig = new RealmConfiguration.Builder()
        //                .name("realm.my_todos")
        //                .inMemory()
        //                .build();

        Realm.setDefaultConfiguration(config)// 设置默认 RealmConfiguration
        // 配合 Configuration 使用
        // Realm.deleteRealm(config);  // 每次执行清除数据
    }

    /**
     * 64 bits
     * @return
     */
    private fun getKey(): ByteArray {
        return byteArrayOf(0, 1, 2, 3, 4, 3, 2, 1, 0, 1, 2, 3, 4, 3, 2, 1, 0, 1, 2, 3, 4, 3, 2, 1, 0, 1, 2, 3, 4, 3, 2, 1, 0, 1, 2, 3, 4, 3, 2, 1, 0, 1, 2, 3, 4, 3, 2, 1, 0, 1, 2, 3, 4, 3, 2, 1, 0, 1, 2, 3, 4, 3, 2, 1)
    }
}
