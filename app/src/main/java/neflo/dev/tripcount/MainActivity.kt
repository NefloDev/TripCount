package neflo.dev.tripcount

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import neflo.dev.tripcount.ui.theme.TripCountTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TripCountTheme {
                Scaffold (
                    modifier = Modifier.fillMaxSize(),
                    topBar = { CustomTopBar() },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {},
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(36.dp),
                                contentDescription = "addIcon",
                                painter = painterResource(R.drawable.ic_add_short),
                            )
                        }
                    }
                ) { _ ->

                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CustomTopBar() {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    "TripCount",
                    textAlign = TextAlign.Center
                )
            },
            actions = {
                IconButton(
                    onClick = {},
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(36.dp),
                        contentDescription = "profileIcon",
                        painter = painterResource(R.drawable.ic_account),
                    )
                }
            }
        )
    }

}