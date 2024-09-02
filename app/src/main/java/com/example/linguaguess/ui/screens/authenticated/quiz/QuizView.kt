package com.example.linguaguess.ui.screens.authenticated.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.linguaguess.R
import com.example.linguaguess.ui.composables.ProgressBarRounded
import com.example.linguaguess.ui.composables.AnswerBox
import com.example.linguaguess.ui.composables.GoBackTopBar
import com.example.linguaguess.ui.composables.buttons.GoodBadButtons

import com.example.linguaguess.ui.composables.buttons.RoundGradientButton
import com.example.linguaguess.ui.composables.buttons.RoundedButton
import com.example.linguaguess.ui.composables.textcompoosables.RoundedFractionLabel
import com.example.linguaguess.ui.composables.textcompoosables.WordText
import com.example.linguaguess.ui.theme.RgDarkBlue
import com.example.linguaguess.ui.theme.RgDarkGreen
import com.example.linguaguess.ui.theme.RgDarkYellow
import com.example.linguaguess.ui.theme.RgLightBlue
import com.example.linguaguess.ui.theme.RgLightGreen
import com.example.linguaguess.ui.theme.RgLightYellow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizView(
    onNavigateBack: () -> Unit,
    collectionId: String,
    quizState: QuizState,
    getWordList: (String) -> Unit,
    onNextWord: () -> Unit,
    onReset: () -> Unit,
    onCheckAnswerChange: () -> Unit,
    onGoodAnswer: () -> Unit,
    onBadAnswer: () -> Unit
) {

    LaunchedEffect(key1 = true, block = {
        getWordList(collectionId)
    })

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        GoBackTopBar(
            onClick = onNavigateBack,
        )

        QuizContent(
            quizState = quizState,
            onNextWord = onNextWord,
            onReset = onReset,
            onCheckAnswerChange = onCheckAnswerChange,
            onGoodAnswer = onGoodAnswer,
            onBadAnswer = onBadAnswer
        )
    }
}


@Composable
fun QuizContent(
    quizState: QuizState,
    onNextWord: () -> Unit,
    onReset: () -> Unit,
    onCheckAnswerChange: () -> Unit,
    onGoodAnswer: () -> Unit,
    onBadAnswer: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.padding_normal),
                end = dimensionResource(id = R.dimen.padding_normal),
                bottom = dimensionResource(id = R.dimen.padding_normal)
            )

    ) {
        Column(
            modifier = Modifier
                .weight(.3f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProgressBarRounded(progress = quizState.totalProgress)
                RoundedFractionLabel(
                    numerator = quizState.currentWordIndex.toString(),
                    denominator = quizState.totalWordCount.toString()

                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Middle Section
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WordText(
                text = quizState.currentWord.hiraganaKatakana,
                modifier = Modifier.padding(top = 32.dp),
            )
            Spacer(modifier = Modifier.weight(.5f))

            if (quizState.isCheckAnswerEnabled) {
                AnswerBox(text = quizState.currentWord.originalWord)
            }

            Spacer(modifier = Modifier.weight(1f))


            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_normal)))

            RoundedButton(
                text = stringResource(R.string.show_answer),
                onClick = onCheckAnswerChange,
                modifier = Modifier
                    .height(50.dp)


            )
            Spacer(modifier = Modifier.height(16.dp))


        }

        Column(
            modifier = Modifier
                .weight(.42f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (quizState.isCheckAnswerEnabled) {
                GoodBadButtons(
                    onGoodClick = onGoodAnswer,
                    onBadClick = onBadAnswer,
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Bottom Section
        Column(
            modifier = Modifier
                .weight(.22f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)

            ) {
                Spacer(Modifier.weight(1f))
                RoundGradientButton(
                    onClick = onReset,
                    enabled = true,
                    imageVector = Icons.Filled.RestartAlt,
                    color1 = RgLightBlue,
                    color2 = RgDarkBlue
                )
                Spacer(Modifier.weight(.5f))
                RoundGradientButton(
                    onClick = {},
                    enabled = true,
                    imageVector = Icons.Filled.AutoAwesome,
                    color1 = RgLightYellow,
                    color2 = RgDarkYellow
                )
                Spacer(Modifier.weight(.5f))
                RoundGradientButton(
                    onClick = onNextWord,
                    enabled = true,
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    color1 = RgLightGreen,
                    color2 = RgDarkGreen
                )
                Spacer(Modifier.weight(1f))
            }
        }

    }
}

