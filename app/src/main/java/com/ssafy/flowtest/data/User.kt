package com.ssafy.flowtest.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name ="name") val name:String?,
    @ColumnInfo(name ="age") val age:String?,
    @ColumnInfo(name ="introduce") val introduce:String?,
    @ColumnInfo(name ="avatar") val avatar:String?
    ){

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

    constructor(_id:Int, name:String, age:String, introduce: String, avatar: String):this(name, age, introduce, avatar){
        id=_id
    }
}