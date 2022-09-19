package com.victor.neuro.router

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.victor.neuro.router.core.SimpleNeuro
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private val TAG = "MainActivity"

    val router = SimpleNeuro()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    fun initialize () {
        registerRoute()

        mBtnProceed.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mBtnProceed -> {
//                val routeUrl = "cherry://com.cherry.router/login"
//                val routeUrl = "cherry://com.cherry.router/messages/1234"
                val routeUrl = "cherry://com.cherry.router/promo?source=banner"

                router.proceed(routeUrl, this)
            }
        }
    }

    fun registerRoute() {
        router.setBase(Uri.parse("cherry://com.cherry.router"))

        // cherry://com.cherry.router/login
        router.addPath("/login",LoginActivity::class.java) {
            Log.e(TAG,"connect-it.args = ${it.args.toString()}")
            Log.e(TAG,"connect-it.fragment = ${it.fragment}")
            Log.e(TAG,"connect-it.queries = ${it.queries.toString()}")
            Log.e(TAG,"connect-it.variables = ${it.variables.toString()}")
            Log.e(TAG,"connect-it.url = ${it.url}")
            Log.e(TAG,"connect-it.uri = ${it.uri}")
            toast(it.context, "Login")
            navigation(router?.routes?.get(it.url))
        }

        // cherry://com.cherry.router/messages/1234
        router.addPath("/messages/<message_id>",MessageActivity::class.java) {
            val messageId = it.variables.optString("message_id")
            Log.e(TAG,"connect-it.args = ${it.args.toString()}")
            Log.e(TAG,"connect-it.fragment = ${it.fragment}")
            Log.e(TAG,"connect-it.queries = ${it.queries.toString()}")
            Log.e(TAG,"connect-it.variables = ${it.variables.toString()}")
            Log.e(TAG,"connect-it.url = ${it.url}")
            Log.e(TAG,"connect-it.uri = ${it.uri}")
            toast(it.context, "Message with $messageId")

            var key = "${it.uri.scheme}://${it.uri.host}${it.uri.path}"
            Log.e(TAG,"connect-key = $key")

            var subLength = messageId.length + 1

            navigation(router?.routes?.get(it.url.substring(0,it.url.length - subLength)))
        }

        // cherry://com.cherry.router/promo?source=banner
        router.addPath("/promo",PromoActivity::class.java) {
            val source = it.queries.optString("source")
            Log.e(TAG,"connect-it.args = ${it.args.toString()}")
            Log.e(TAG,"connect-it.fragment = ${it.fragment}")
            Log.e(TAG,"connect-it.queries = ${it.queries.toString()}")
            Log.e(TAG,"connect-it.variables = ${it.variables.toString()}")
            Log.e(TAG,"connect-it.url = ${it.url}")
            Log.e(TAG,"connect-it.uri = ${it.uri}")
            Log.e(TAG,"connect-it.uri.scheme = ${it.uri.scheme}")
            Log.e(TAG,"connect-it.uri.host = ${it.uri.host}")
            Log.e(TAG,"connect-it.uri.authority = ${it.uri.authority}")
            Log.e(TAG,"connect-it.uri.path = ${it.uri.path}")
            toast(it.context, "Promo with $source")

            var key = "${it.uri.scheme}://${it.uri.host}${it.uri.path}"
            Log.e(TAG,"connect-key = $key")

            navigation(router?.routes?.get(key))
        }
    }

    fun toast(context: Context?, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun navigation (clazz: Class<*>?) {
        if (clazz == null) return
        var intent = Intent(this,clazz)
        startActivity(intent)
    }
}