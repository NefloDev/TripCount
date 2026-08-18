package neflo.dev.tripcount

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import neflo.dev.tripcount.database.DatabaseManager
import neflo.dev.tripcount.model.UserModel
import neflo.dev.tripcount.ui.theme.TripCountTheme

class MainActivity : ComponentActivity() {

    val db = DatabaseManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TripCountTheme {
                val lifecycleOwner = LocalLifecycleOwner.current
                val resources = LocalResources.current

                Scaffold (
                    modifier = Modifier.fillMaxSize(),
                    topBar = { CustomTopBar(resources) },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                val currentState = lifecycleOwner.lifecycle.currentState

                                if (currentState.isAtLeast(Lifecycle.State.RESUMED)) {
                                    db.addUser(
                                        UserModel("Alejandro", "alneflo27@gmail.com", "Alejo"),
                                        resources
                                    )
                                }
                            },
                            modifier = Modifier
                                .padding(8.dp)
                                .size(68.dp)
                        ) {
                            Icon(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                contentDescription = "addIcon",
                                painter = painterResource(R.drawable.ic_add_short),
                            )
                        }
                    }
                ) { innerPadding ->
                    LazyColumn (modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()) {
                        items(
                            count = 10,
                            itemContent = { index ->
                                CustomGroupCard("Group ${index+1}")
                            }
                        )
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CustomTopBar(resources: Resources) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    stringResource(R.string.app_name),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium
                )
            },
            actions = {
                IconButton(
                    onClick = {db.getUserByEmail("alneflo27@gmail.com", resources)},
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(54.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        contentDescription = "profileIcon",
                        painter = painterResource(R.drawable.ic_account),
                    )
                }
            }
        )
    }

    @Composable
    fun CustomGroupCard(groupName : String) {
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            onClick = {}
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .requiredSize(80.dp)
                ) {
                    Image(
                        contentDescription = "groupIcon",
                        painter = painterResource(R.drawable.ic_launcher_background),
                    )
                }
                Text(
                    modifier = Modifier
                        .weight(4f)
                        .padding(horizontal = 32.dp),
                    text = groupName
                )
                Icon(
                    modifier = Modifier
                        .weight(1f)
                        .requiredSize(36.dp),
                    contentDescription = "goIcon",
                    painter = painterResource(R.drawable.ic_forward),
                )
            }
        }
    }

}