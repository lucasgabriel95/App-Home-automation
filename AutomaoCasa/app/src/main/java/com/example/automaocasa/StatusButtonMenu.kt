package com.example.automaocasa

import android.graphics.Color
import android.media.DrmInitData
import android.media.Image

class StatusButtonMenu {

    var Status : String = ""

    constructor() {
    }

    constructor(status: String) {

        this.Status = status
    }

    fun  StatusButton () :String
    {
        if (Status == "off")
        {
            Status = "on";
        }
        else if (Status == "on")
        {
            Status = "off";
        }
        else if (Status == "Fechado")
        {
            Status = "Aberto"
        }
        else
        {
            Status = "Fechado"
        }
        return  Status;
    }

    fun ColorButton () : Int
    {
        if (Status == "off" || Status == "Fechado" )
        {
            return android.graphics.Color.GREEN
        }
        else
        {
            return return android.graphics.Color.RED
        }

    }

    fun ImbgButton () : Int
    {
        if (Status == "off" )
        {
            return R.drawable.off_white
        }
        else
        {
            return R.drawable.off_red
        }
    }




}