package com.jintin.typedbundle.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jintin.typedbundle.key.StringKey
import com.jintin.typedbundle.key.get
import com.jintin.typedbundle.key.put

class MainActivity : ComponentActivity() {

    private val textKey = StringKey("Text")
    private var text by mutableStateOf("")

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                placeholder = { Text("Input Something and rotate") })
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.put(textKey, text)
//        textKey.put(outState, text)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        text = savedInstanceState.get(textKey).orEmpty()
//        text = textKey.get(savedInstanceState).orEmpty()
    }
}
