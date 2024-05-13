package com.grckikino.ui.compose.round

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.grckikino.R
import com.grckikino.data.models.round.Round
import com.grckikino.helpers.DateTimeFormatHelper
import com.grckikino.ui.navigation.ScreensRoute
import com.grckikino.viewModels.round.QuotesViewModel
import com.grckikino.viewModels.round.RoundViewModel
import java.time.Instant

@Composable
fun RoundScreen(navHostController: NavHostController, drawId: Int?) {
    val roundViewModel = viewModel<RoundViewModel>()
    val quotesViewModel = viewModel<QuotesViewModel>()
    val round by roundViewModel.round.collectAsState()

    if (drawId != null) {
        roundViewModel.getRound(drawId)
    }

    val textColor = colorResource(id = R.color.text_color)

    Column(
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background_color)),
    ) {
        Column(
            Modifier.background(colorResource(id = R.color.round_header_color)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopButtonRow(textColor, navHostController) // Reuse this row for different buttons
            DrawTimeRow(round) // For display draw time and other info
            QuotesView(quotesViewModel)
            RandomAndMaxNumbersHeader(roundViewModel)
            NumbersSelector(
                roundViewModel.totalBoxes,
                roundViewModel.selectedNumbers
            ) { index -> roundViewModel.selectNumber(index) } // Now uses ViewModel for state
        }
    }
}

@Composable
fun ButtonWithIconAndText(
    modifier: Modifier,
    buttonColor: Color,
    painterResource: Painter,
    description: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.clickable {
            onClick.invoke()
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painterResource,
            tint = buttonColor,
            contentDescription = description,
            modifier = Modifier
                .size(30.dp)
                .padding(2.dp)
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(2.dp),
            text = description,
            style = TextStyle(fontWeight = FontWeight.Bold),
            color = buttonColor,
            textAlign = TextAlign.Center,
            fontSize = 10.sp
        )
    }
}

@Composable
fun TopButtonRow(buttonColor: Color, navHostController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(60.dp)
    ) {
        ButtonWithIconAndText(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            buttonColor = buttonColor,
            painterResource = painterResource(id = R.drawable.grid),
            description = stringResource(id = R.string.field)
        ) {}
        ButtonWithIconAndText(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            buttonColor = buttonColor,
            painterResource = painterResource(id = R.drawable.add_games),
            description = stringResource(id = R.string.add_games)
        ) {}
        ButtonWithIconAndText(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            buttonColor = buttonColor,
            painterResource = painterResource(id = R.drawable.live_rollout),
            description = stringResource(id = R.string.live_drawing)
        ) { navHostController.navigate(ScreensRoute.WEBVIEW.name) }
        ButtonWithIconAndText(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            buttonColor = buttonColor,
            painterResource = painterResource(id = R.drawable.grid),
            description = stringResource(id = R.string.drawing_results)
        ) {}
        ButtonWithIconAndText(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            buttonColor = buttonColor,
            painterResource = painterResource(id = R.drawable.fast),
            description = stringResource(id = R.string.fast_kino)
        ) {}
    }
}

@Composable
fun DrawTimeRow(round: Round) {
    val time = DateTimeFormatHelper.format(Instant.ofEpochMilli(round.drawTime), "HH:mm")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(30.dp)
            .background(colorResource(id = R.color.rounds_header_color))
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp)
                .align(Alignment.CenterVertically),
            text = "${stringResource(id = R.string.draw_time)}: $time | ${
                stringResource(
                    id = R.string.round
                )
            }: ${round.visualDraw}",
            color = colorResource(id = R.color.text_color),
            textAlign = TextAlign.Start,
            fontSize = 12.sp
        )
    }
}

@Composable
fun RandomAndMaxNumbersHeader(viewModel: RoundViewModel) {
    val maxSelections by viewModel.maxSelections
    var expanded by remember { mutableStateOf(false) }
    val options = listOf(8, 9, 10, 11, 12, 13, 14, 15)

    var selectedMax by remember { mutableIntStateOf(options[0]) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(40.dp)
            .background(colorResource(id = R.color.background_color))
    ) {
        IconButton(
            modifier = Modifier
                .background(colorResource(id = R.color.round_button_color))
                .align(Alignment.CenterVertically)
                .weight(4f)
                .size(35.dp)
                .fillMaxWidth(0.8f),
            onClick = {
                viewModel.randomSelectNumbers()
                Log.i("TAGTEST", "Random selection of $maxSelections numbers")
            }) {
            Text(
                text = stringResource(id = R.string.random_selection),
                color = colorResource(id = R.color.text_color),
                fontSize = 14.sp,
                modifier = Modifier.background(Color.Transparent)
            )
        }
        Spacer(
            modifier = Modifier
                .weight(4f)
                .fillMaxSize()
                .background(Color.Transparent)
        )
        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxSize(),
            contentAlignment = Alignment.CenterStart,
        ) {
            Text(
                text = stringResource(id = R.string.numbers),
                color = colorResource(id = R.color.text_color),
                fontSize = 14.sp
            )
        }
        Row(
            modifier = Modifier
                .weight(2f)
                .fillMaxSize()
                .clickable { expanded = true },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedMax.toString(),
                color = colorResource(id = R.color.text_color),
                fontSize = 14.sp
            )
            Icon(
                Icons.Outlined.ArrowDropDown, null, modifier = Modifier
                    .padding(8.dp), tint = colorResource(id = R.color.round_button_color)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth(0.2f)
            ) {
                options.forEach { selection ->
                    DropdownMenuItem(
                        onClick = {
                            selectedMax = selection
                            viewModel.setMaxSelections(selection) // Update the ViewModel when the user selects a new max
                            expanded = false
                        },
                        text = {
                            Text(text = selection.toString())
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun NumbersSelector(totalBoxes: Int, selectedNumbers: List<Int>, onNumberSelected: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background_color))
    ) {
        for (i in 0 until totalBoxes step 10) {
            BoxRow(
                Modifier.weight(1f),
                start = i + 1,
                end = i + 10,
                selectedNumbers,
                onNumberSelected
            )
        }
    }
}

@Composable
fun BoxRow(
    modifier: Modifier,
    start: Int,
    end: Int,
    selectedNumbers: List<Int>,
    onNumberSelected: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        for (i in start..end) {
            SelectableBox(
                Modifier.weight(1f),
                index = i,
                isSelected = i in selectedNumbers,
                onNumberSelected
            )
        }
    }
}

@Composable
fun SelectableBox(
    modifier: Modifier,
    index: Int,
    isSelected: Boolean,
    onNumberSelected: (Int) -> Unit
) {
    val unpressedColor = colorResource(id = R.color.button_unpressed_color)
    val pressedColor = colorResource(id = R.color.button_pressed_color)
    val boxBackgroundColor = if (isSelected) pressedColor else unpressedColor

    Box(
        modifier = modifier
            .fillMaxHeight()
            .border(BorderStroke(2.dp, colorResource(id = R.color.rounds_header_color)))
            .selectable(
                selected = isSelected,
                onClick = { onNumberSelected(index) },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .background(boxBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = index.toString(),
            color = colorResource(id = R.color.round_text_color),
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
    }
}