package neflo.dev.tripcount.screens

import android.content.SharedPreferences
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import neflo.dev.tripcount.R
import neflo.dev.tripcount.components.CustomTopBar

@Composable
fun MainScreen(sharedPreferences: SharedPreferences, navController: NavController){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CustomTopBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
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

@Composable
private fun CustomGroupCard(groupName : String) {
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