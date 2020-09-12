package com.jetbrains.handson.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jetbrains.handson.kmm.shared.SpaceXSDK
import com.jetbrains.handson.kmm.shared.cache.DatabaseDriverFactory
import com.jetbrains.handson.kmm.shared.entity.RocketLaunch


class MainActivity : AppCompatActivity() {
    private val sdk = SpaceXSDK(DatabaseDriverFactory(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "SpaceX Launches"

        setContent {
            MaterialTheme {
                MainLayout(sdk)
            }
        }
    }
}

@Composable
fun MainLayout(sdk: SpaceXSDK) {
    var launches by remember { mutableStateOf<List<RocketLaunch>>(emptyList()) }

    launchInComposition() {
        launches = sdk.getLaunches(false)
    }

    LazyColumnFor(items = launches) { launch ->
        LaunchView(launch)
    }
}

@Composable
fun LaunchView(launch: RocketLaunch) {
    Card(shape = RoundedCornerShape(8.dp), modifier = Modifier.padding(8.dp).fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(stringResource(R.string.mission_name_field, launch.missionName), modifier = Modifier.padding(8.dp))
            LaunchSuccessView(launch)
            Text(stringResource(R.string.launch_year_field, launch.launchYear), modifier = Modifier.padding(8.dp))
            Text(stringResource(R.string.details_field, launch.details ?: ""), modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun LaunchSuccessView(launch: RocketLaunch) {
    val launchSuccess = launch.launchSuccess
    if (launchSuccess != null ) {
        if (launchSuccess) {
            Text(stringResource(R.string.successful), color = colorResource(R.color.colorSuccessful), modifier = Modifier.padding(8.dp))
        } else {
            Text(stringResource(R.string.unsuccessful), color = colorResource(R.color.colorUnsuccessful), modifier = Modifier.padding(8.dp))
        }
    } else {
        Text(stringResource(R.string.no_data), color = colorResource(R.color.colorNoData), modifier = Modifier.padding(8.dp))
    }
}