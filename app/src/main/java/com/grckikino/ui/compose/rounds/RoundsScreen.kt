package com.grckikino.ui.compose.rounds

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.grckikino.R
import com.grckikino.ui.navigation.ScreensRoute
import com.grckikino.ui.compose.cards.RoundCard
import com.grckikino.viewModels.rounds.RoundsViewModel

@Composable
fun RoundsScreen(navHostController: NavHostController) {

    val roundsViewModel = viewModel<RoundsViewModel>()
    val rounds by roundsViewModel.rounds.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background_color)),
    ) {
        Column(
            Modifier.background(colorResource(id = R.color.rounds_header_color)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier
                    .fillMaxWidth(0.95f)
                    .size(60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(id = R.drawable.greek_flag),
                    contentDescription = "Greece flag"
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp),
                    text = stringResource(id = R.string.app_name),
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.text_color)
                )
            }
            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth(0.95f)
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.spacer_color))
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp)
                        .align(Alignment.CenterStart),
                    text = stringResource(id = R.string.draw_time),
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.text_color)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 12.dp)
                        .align(Alignment.CenterEnd),
                    text = stringResource(id = R.string.remaining_time),
                    fontSize = 16.sp,
                    textAlign = TextAlign.End,
                    color = colorResource(id = R.color.text_color)
                )
            }
        }
        LazyColumn {
            items(rounds.size) { position ->
                val round = rounds[position]
                RoundCard(round = round, onClick = { drawId ->
                    navHostController.navigate(
                        ScreensRoute.ROUND.name + "?drawId=${drawId}"
                    )
                })
            }
        }
    }
}