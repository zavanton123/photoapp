package com.zavanton.photoapp.photos.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zavanton.photoapp.photos.data.MockResponse
import com.zavanton.photoapp.photos.di.PhotosComponentManager
import com.zavanton.photoapp.ui.theme.PhotoAppTheme
import javax.inject.Inject

class PhotosActivity : ComponentActivity() {

    // todo zavanton - delete
    @Inject
    lateinit var mockResponse: MockResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        PhotosComponentManager.component.inject(this)

        super.onCreate(savedInstanceState)

        // todo zavanton - delete
        Log.d("zavanton", "zavanton - mock: ${mockResponse.content}")

        setContent {
            PhotoAppTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhotoAppTheme {
        Greeting("Android")
    }
}
