package com.example.tictac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictac.ui.theme.TIcTacTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TIcTacTheme {
                var arr by remember { mutableStateOf(Array(3) { Array(3) { "" } }) }
                var currentPlayer by remember { mutableStateOf("O") }
                var winner by remember { mutableStateOf<String?>(null) }

                fun checkWinner(): String? {
                    for (i in 0..2) {
                        if (arr[i][0] == arr[i][1] && arr[i][1] == arr[i][2] && arr[i][0] == currentPlayer) {
                            return currentPlayer
                        }
                        if (arr[0][i] == arr[1][i] && arr[1][i] == arr[2][i] && arr[0][i] == currentPlayer) {
                            return currentPlayer
                        }
                    }
                    if (arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2] && arr[0][0] == currentPlayer) {
                        return currentPlayer
                    }
                    if (arr[0][2] == arr[1][1] && arr[1][1] == arr[2][0] && arr[0][2] == currentPlayer) {
                        return currentPlayer
                    }
                    if (arr.all { row -> row.all { it != "" } }) {
                        return "Draw"
                    }
                    return null
                }
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = "TicTac",
                                    color = Color.Blue,
                                    fontSize = 34.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                            },
                            actions = {
                                Icon(
                                    painter = painterResource(R.drawable.reload),
                                    contentDescription = "Reload Icon",
                                    modifier = Modifier
                                        .padding(end = 10.dp)
                                        .clickable {
                                            arr = Array(3) { arrayOf<String>("","","") }
                                            currentPlayer = "O"
                                            winner = null
                                        }
                                )
                            }
                        )
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                            .background(color = Color.White),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (winner != null) "Winner: $winner" else "Player: $currentPlayer",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(16.dp)
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .background(Color.LightGray)
                                .border(4.dp, color = Color.Black)
                        ) {
                            for (i in 0..2) {
                                Row(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxSize()
                                ) {
                                    for (j in 0..2) {
                                        Box(
                                            modifier = Modifier
                                                .weight(1f)
                                                .fillMaxSize()
                                                .border(2.dp, color = Color.Black)
                                                .background(Color.Cyan)
                                                .clickable {
                                                    arr[i][j] = currentPlayer
                                                    winner = checkWinner()
                                                    if (winner == null) {
                                                        currentPlayer =
                                                            if (currentPlayer == "O") "X" else "O"
                                                    }
                                                },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = arr[i][j],
                                                fontSize = 34.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
