package com.example.linguaguess.ui.screens.authenticated.quiz

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.example.linguaguess.domain.model.JapaneseWord
import com.example.linguaguess.ui.common.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(QuizState())
    val uiState = _uiState.asStateFlow()


    private val words = listOf(
        JapaneseWord(
            originalWord = "thank you for the meal (before eating)",
            hiraganaKatakana = "いただきます",
            kanjiWord = "頂きます",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "thank you for the meal (after eating)",
            hiraganaKatakana = "ごちそうさまでした",
            kanjiWord = "御馳走様でした",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "I am going and coming back",
            hiraganaKatakana = "いってきます",
            kanjiWord = "行って来ます",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "please go and come back",
            hiraganaKatakana = "いってらっしゃい",
            kanjiWord = "行ってらっしゃい",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "I am home",
            hiraganaKatakana = "ただいま",
            kanjiWord = "只今",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "welcome home",
            hiraganaKatakana = "おかえり",
            kanjiWord = "お帰り",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "how do you do?",
            hiraganaKatakana = "はじめまして",
            kanjiWord = "始めまして",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "nice to meet you",
            hiraganaKatakana = "よろしくおねがいします",
            kanjiWord = "宜しくお願いします",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "good morning",
            hiraganaKatakana = "おはよう",
            kanjiWord = "お早う",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "good morning (polite)",
            hiraganaKatakana = "おはようございます",
            kanjiWord = "お早うございます",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "good afternoon",
            hiraganaKatakana = "こんにちは",
            kanjiWord = "今日は",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "good evening",
            hiraganaKatakana = "こんばんは",
            kanjiWord = "今晩は",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "good night",
            hiraganaKatakana = "おやすみなさい",
            kanjiWord = "お休みなさい",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "goodbye",
            hiraganaKatakana = "さようなら",
            kanjiWord = "左様なら",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "thank you",
            hiraganaKatakana = "ありがとう",
            kanjiWord = "有難う",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "excuse me",
            hiraganaKatakana = "すみません",
            kanjiWord = "済みません",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "No",
            hiraganaKatakana = "いいえ",
            kanjiWord = "いいえ",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "0",
            hiraganaKatakana = "ゼロ",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "1",
            hiraganaKatakana = "いち",
            kanjiWord = "一",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "2",
            hiraganaKatakana = "に",
            kanjiWord = "二",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "3",
            hiraganaKatakana = "さん",
            kanjiWord = "三",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "4",
            hiraganaKatakana = "よん",
            kanjiWord = "四",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "5",
            hiraganaKatakana = "ご",
            kanjiWord = "五",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "6",
            hiraganaKatakana = "ろく",
            kanjiWord = "六",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "7",
            hiraganaKatakana = "なな",
            kanjiWord = "七",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "8",
            hiraganaKatakana = "はち",
            kanjiWord = "八",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "9",
            hiraganaKatakana = "きゅう",
            kanjiWord = "九",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "10",
            hiraganaKatakana = "じゅう",
            kanjiWord = "十",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "11",
            hiraganaKatakana = "じゅういち",
            kanjiWord = "十一",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "12",
            hiraganaKatakana = "じゅうに",
            kanjiWord = "十二",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "13",
            hiraganaKatakana = "じゅうさん",
            kanjiWord = "十三",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "14",
            hiraganaKatakana = "じゅうよん",
            kanjiWord = "十四",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "15",
            hiraganaKatakana = "じゅうご",
            kanjiWord = "十五",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "16",
            hiraganaKatakana = "じゅうろく",
            kanjiWord = "十六",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "17",
            hiraganaKatakana = "じゅうなな",
            kanjiWord = "十七",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "18",
            hiraganaKatakana = "じゅうはち",
            kanjiWord = "十八",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "19",
            hiraganaKatakana = "じゅうきゅう",
            kanjiWord = "十九",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "20",
            hiraganaKatakana = "にじゅう",
            kanjiWord = "二十",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "30",
            hiraganaKatakana = "さんじゅう",
            kanjiWord = "三十",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "40",
            hiraganaKatakana = "よんじゅう",
            kanjiWord = "四十",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "50",
            hiraganaKatakana = "ごじゅう",
            kanjiWord = "五十",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "60",
            hiraganaKatakana = "ろくじゅう",
            kanjiWord = "六十",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "70",
            hiraganaKatakana = "ななじゅう",
            kanjiWord = "七十",
            chapter = 0
        ),
        JapaneseWord(
            originalWord = "80",
            hiraganaKatakana = "はちじゅう",
            kanjiWord = "八十",
            chapter = 0
        )

    )

    private var correctWords = 0

    init {
        _uiState.update {
            it.copy(
                totalWordCount = words.size,
                currentWord = words[0]
            )
        }
    }

    fun setCheckAnswerEnabled() {
        _uiState.update {
            it.copy(isCheckAnswerEnabled = true)
        }
    }

    @SuppressLint("DiscouragedApi")
    private fun getResourceIdForImage(context: Context, imageName: String): Int {
        //put the rsource id in state in viewmodel

        // Resolve the resource ID dynamically using resources.getIdentifier
        val packageName = context.packageName
        return try {
            context.resources.getIdentifier(imageName, "drawable", packageName)
        } catch (e: Exception) {
            e.printStackTrace()
            0 // Return 0 or a default resource ID if not found
        }
    }


    fun reset() {

        _uiState.update {
            it.copy(
                isCheckAnswerEnabled = false,
                gotItStatus = GotItStatus.NOT_PRESSED
            )
        }
        if (_uiState.value.gotItStatus == GotItStatus.GOT_IT_RIGHT) {
            correctWords--

        }
    }


    fun nextWord() {
        _uiState.update {
            it.copy(
                currentWordIndex = it.currentWordIndex + 1,
                currentWord = words[it.currentWordIndex + 1],
                totalProgress = ((it.currentWordIndex + 1) * 100) / it.totalWordCount,
                isCheckAnswerEnabled = false,
                gotItStatus = GotItStatus.NOT_PRESSED
            )
        }
    }

    fun goodAnswer() {
        _uiState.update {
            it.copy(
                gotItStatus = GotItStatus.GOT_IT_RIGHT

            )
        }
        correctWords++
    }

    fun badAnswer() {
        _uiState.update {
            it.copy(
                gotItStatus = GotItStatus.GOT_IT_WRONG
            )
        }
        if (_uiState.value.gotItStatus == GotItStatus.GOT_IT_RIGHT) {
            correctWords--

        }
    }


}


@Immutable
data class QuizState(
    val currentWord: JapaneseWord = JapaneseWord(),
    val totalWordCount: Int = 0,
    val currentWordIndex: Int = 0,
    val totalProgress: Int = 0,
    val isCheckAnswerEnabled: Boolean = false,
    val gotItStatus: GotItStatus = GotItStatus.NOT_PRESSED,
    val errorState: QuizErrorState = QuizErrorState(),
)

@Immutable
data class QuizErrorState(
    val wrongAnswerErrorState: ErrorState = ErrorState(),
)

enum class GotItStatus {
    NOT_PRESSED,
    GOT_IT_RIGHT,
    GOT_IT_WRONG
}


