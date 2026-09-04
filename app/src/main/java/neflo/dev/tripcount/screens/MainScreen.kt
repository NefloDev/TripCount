package neflo.dev.tripcount.screens

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import neflo.dev.tripcount.R
import neflo.dev.tripcount.api.model.helper.ApiResponse
import neflo.dev.tripcount.api.model.helper.BaseViewModel
import neflo.dev.tripcount.api.viewModel.UserViewModel
import neflo.dev.tripcount.components.CustomTopBar
import neflo.dev.tripcount.util.base64ToBitmap

@Composable
fun MainScreen(sharedPreferences: SharedPreferences, navController: NavController){
    val userViewModel: UserViewModel = hiltViewModel()
    val userGroupsResponse = userViewModel.userGroups.collectAsState()

    val isGroupsResponseError = remember {
        mutableStateOf(false)
    }

    if (userGroupsResponse.value == null){
        userViewModel.getUserGroups(object : BaseViewModel.CoroutinesErrorHandler {
            override fun onError(message: String) {
                isGroupsResponseError.value = true
            }
        })
    }

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

        PullToRefreshBox(
            isRefreshing = userGroupsResponse.value is ApiResponse.Loading,
            onRefresh = {
                userViewModel.getUserGroups(object : BaseViewModel.CoroutinesErrorHandler {
                    override fun onError(message: String) {
                        isGroupsResponseError.value = true
                    }
                })
            },
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when(val response = userGroupsResponse.value) {
                null,
                is ApiResponse.Success -> {
                    if (response == null) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Error loading groups")
                        }
                    } else{
                        LazyColumn (modifier = Modifier
                            .fillMaxSize()) {
                            items(response.data) { group ->
                                CustomGroupCard(group.name, group.pfp)
                            }
                        }
                    }
                }
                is ApiResponse.Failure -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Error loading groups")
                    }
                }
                is ApiResponse.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(150.dp),
                            strokeWidth = 10.dp
                        )
                    }
                }
            }
        }
    }

}

@Composable
private fun CustomGroupCard(groupName : String, pfp: String?) {
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
                pfp?.let { base64ToBitmap(pfp)?.asImageBitmap()?.let {
                    Image(
                        contentDescription = "groupIcon",
                        bitmap = it
                    )
                }} ?: Image(
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