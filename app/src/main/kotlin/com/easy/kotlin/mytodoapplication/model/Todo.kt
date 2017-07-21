package com.easy.kotlin.mytodoapplication.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by jack on 2017/7/21.
 */
@RealmClass
open class Todo : RealmObject() {
    @PrimaryKey
    open var id: String = "-1"
    open var title: String = "日程"
    open var content: String = "事项"
}
