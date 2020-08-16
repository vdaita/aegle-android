package com.daita.aegle

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_user_homepage.*
import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import javax.xml.transform.ErrorListener


class UserHomepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_homepage)

        health_check.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start{ result_code, data ->
                    if(result_code == Activity.RESULT_OK){
                        var encoded = encodeImage(ImagePicker.getFilePath(data)!!)
                        makeJsonObjReq(encoded!!)
                        Toast.makeText(this@UserHomepage, "Health Check Sent To Doctor", Toast.LENGTH_SHORT).show();
                        Log.v("encoded image: ", encoded)
                    }

                }
        }

        health_report.setOnClickListener {
            startActivity(Intent(this@UserHomepage, HealthReport::class.java))
        }

        call_hotline.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Do you want to call the hotline?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes,
                    DialogInterface.OnClickListener { dialog, whichButton ->
                        val callIntent = Intent(Intent.ACTION_CALL)
                        callIntent.data = Uri.parse("tel:" + "13136704087")
                        startActivity(callIntent)
                    })
                .setNegativeButton(android.R.string.no, null).show()
        }
    }

    private fun encodeImage(path: String): String? {
        val imagefile = File(path)
        var fis: FileInputStream? = null
        try {
            fis = FileInputStream(imagefile)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        val bm = BitmapFactory.decodeStream(fis)
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        //Base64.de
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    private fun makeJsonObjReq(b64: String) {
        val queue: RequestQueue = Volley.newRequestQueue(this)
        val postParam: MutableMap<String?, String?> =
            HashMap()
        postParam["text"] = b64
        val request = JsonObjectRequest(Request.Method.POST, "http://099e4f849a7a.ngrok.io/predicteyes", JSONObject(
            postParam as Map<*, *>
        ), Response.Listener {
                response ->try {
           Log.v("Response: ", response.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, Response.ErrorListener { error -> error.printStackTrace() })
        queue?.add(request)
    }
}